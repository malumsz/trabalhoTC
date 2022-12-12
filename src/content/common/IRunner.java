
package content.common;

import content.finite.runner.DFARunner;
import content.finite.runner.NFARunner;
import content.pushdown.runner.DPDARunner;
import content.pushdown.runner.NPDARunner;
import content.turing.runner.DTMRunner;

public interface IRunner<S extends AbstractState> {
    
    public void compute();
    
    public Result<S> getResult();
}
