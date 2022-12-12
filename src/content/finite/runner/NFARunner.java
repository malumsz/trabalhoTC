package content.finite.runner;

import java.util.Queue;

import content.common.IRunner;
import content.common.Result;
import content.common.Symbol;
import content.common.Word;
import content.finite.common.FState;
import content.finite.common.FTransition;

import java.util.LinkedList;

public class NFARunner implements IRunner<FState> {
    
    private class Tuple<A,B> {
        public final A state;
        public final B index;
        public Tuple(A a, B b) {
            this.state = a;
            this.index = b;
        }
    }
    
    private final Word word;
    private final FState[] initialStates;
    private Tuple<FState, Integer> current;
    private final Queue<Tuple<FState, Integer>> queue;
    
    public NFARunner(Word word, FState... initial) {
        this.word = word;
        this.queue = new LinkedList<>();
        this.initialStates = initial;
    }

    @Override
    public void compute() {
        
        for (FState state : initialStates) {
            queue.offer(new Tuple<>(state, 0));
        }
        
        do {
            current = queue.poll();
            
            if (isEndOfWord()) {
                if (current.state.isFinalState()) {
                    break;
                } else {
                    continue;
                }
            }

            Symbol symbol  = word.get(current.index);
            
            current.state.getEpsilonTransitions().stream().forEach((trans) -> {
                enqueueTransition(trans, true);
            });
            
            current.state.getTransitions(symbol).stream().forEach((trans) -> {
                enqueueTransition(trans, false);
            });
            
        } while (!queue.isEmpty());

    }
    
    private void enqueueTransition(FTransition trans, boolean isEpsilon) {
        FState state = trans.getNext();
        int index = current.index + ((isEpsilon) ? 0 : 1);
        queue.offer(new Tuple<>(state, index));
    }
    
    private boolean isEndOfWord() {
        return current.index >= word.size();
    }

    @Override
    public Result<FState> getResult() {
        return new Result<>(current.state);
    }
}
