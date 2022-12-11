
package content.finite.runner;

import content.common.IRunner;
import content.common.Result;
import content.common.Word;
import content.finite.common.FState;

public class DFARunner implements IRunner<FState> {
    
    private final Word word;
    private FState current;
    
    public DFARunner(Word word, FState initial) {
        this.word = word;
        this.current = initial;
    }
    
    @Override
    public void compute() {
        word.stream().map(
            (symbol) -> current.getTransition(symbol)
        ).forEach(
            (trans) -> { current = trans.getNext(); }
        );
    }
    
    @Override
    public Result<FState> getResult() {
        return new Result<>(current);
    }

}
