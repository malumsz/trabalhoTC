
package content.pushdown.runner;

import java.util.Stack;

import content.common.IRunner;
import content.common.Result;
import content.common.Symbol;
import content.common.Word;
import content.pushdown.common.PState;
import content.pushdown.common.PTransition;


public class DPDARunner implements IRunner<PState> {

    private final Stack<Symbol> stack;
    private PState current;
    private final Word word;
    
    public DPDARunner(Word word, PState initial) {
        this.word = word;
        this.current = initial;
        this.stack = new Stack();
    }

    @Override
    public void compute() {
        word.stream().forEach( (symbol) -> {

            computeTransition(current.getTransition(symbol));

            
            current.getEpsilonTransitions().stream().forEach((t) -> {
                computeTransition(t);
            });
        });

    }

    private void computeTransition(PTransition trans) {
        boolean changed = false;
        
        for (Symbol s : trans.toPop()) {
            if (!stack.isEmpty() && s.equals(stack.peek())) {
                stack.pop();
                changed = true;
            }
        }
        

        for (Symbol s : trans.toPush()) {
            stack.push(s);
            changed = true;
        }
        
       
        if (changed) {
            current = trans.getNext();
        }
    }
    
    @Override
    public Result<PState> getResult() {
        return new Result<>(current);
    }
    
}
