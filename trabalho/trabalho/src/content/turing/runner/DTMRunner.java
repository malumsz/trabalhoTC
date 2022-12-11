
package content.turing.runner;

import content.common.IRunner;
import content.common.Result;
import content.common.Symbol;
import content.common.Word;
import content.turing.common.TState;
import content.turing.common.TTransition;


public class DTMRunner implements IRunner<TState> {

    private final TState initial;
    private final Word word;
    private final int offset;
    private Result result;
    
    public DTMRunner(Word word, TState initial) {
        this(word, 0, initial);
    }
    
    public DTMRunner(Word word, int offset, TState initial) {
        this.word = word;
        this.offset = offset;
        this.initial = initial;
        this.result = new Result(new TState("Dead State"));
    }
    
    @Override
    public void compute() {
        int i = offset;
        TState current = initial;
        
        while (i < word.size() && !current.isFinalState()) {
            
            Symbol symbol = word.get(i);
            
            TTransition t = current.getTransition(symbol);

            if (t.getRead().equals(symbol)) {
                
                
                word.set(i, t.getWrite());
                
                
                current = t.getNext();
                
                
                i += t.getMove();
            }
            
        }
        
        result = new Result(current);
    }
    
    @Override
    public Result getResult() {
        return result;
    }
    
}
