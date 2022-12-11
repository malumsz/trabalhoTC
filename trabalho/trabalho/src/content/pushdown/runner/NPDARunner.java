package content.pushdown.runner;

import java.util.Queue;
import java.util.Stack;

import content.common.IRunner;
import content.common.Result;
import content.common.Symbol;
import content.common.Word;
import content.pushdown.common.PState;
import content.pushdown.common.PTransition;

import java.util.LinkedList;


public class NPDARunner implements IRunner<PState> {

    private class ThreeUple<A, B, C> {
        public final A state;
        public final B stack;
        public final C index;
        public ThreeUple(A a, B b, C c) {
            this.state = a;
            this.stack = b;
            this.index = c;
        }
    }
    
    private final Word word;
    private final PState[] initial;
    private ThreeUple<PState, Stack<Symbol>, Integer> current;
    private final Queue<ThreeUple<PState, Stack<Symbol>, Integer>> queue;

    public NPDARunner(Word word, PState... initial) {
        this.word = word;
        this.queue = new LinkedList();
        this.initial = initial;
    }

    @Override
    public void compute() {

        for (PState s : initial) {
            queue.offer(new ThreeUple<>(s, new Stack<Symbol>(), 0));
        }

        do {
            current = queue.poll();
            current.state.getEpsilonTransitions().stream().forEach((trans) -> {
                computeTransition(trans, true);
            });
            
            if (isEndOfWord()) { continue; }
            
            Symbol symbol = word.get(current.index);
            current.state.getTransitions(symbol).stream().forEach((t) -> {
                computeTransition(t, false);
            });

            if (current.state.getTransitions().isEmpty()) {
                PState deadState = new PState("Dead State", false);
                current = new ThreeUple<>(deadState, null, -1);
                break;                    
            }
            
        } while (!queue.isEmpty());
        
    }

    private boolean isEndOfWord() {
        return current.index >= word.size();
    }

    private void computeTransition(PTransition t, boolean isEpsilon) {
        boolean changed = false;
        
        for (Symbol s : t.toPop()) {
            if (!current.stack.isEmpty() && s.equals(current.stack.peek())) {
                current.stack.pop();
                changed = true;
            }
        }
        
        for (Symbol s : t.toPush()) {
            current.stack.push(s);
            changed = true;
        }
        
        if (changed) {
            PState next = t.getNext();
            Stack<Symbol> stack = (Stack<Symbol>) current.stack.clone();
            int index = current.index + ((isEpsilon) ? 0 : 1);
            
            queue.offer(new ThreeUple<>(next, stack, index));
        }
    }
    
    @Override
    public Result<PState> getResult() {
        return new Result<>(current.state);
    }
}
