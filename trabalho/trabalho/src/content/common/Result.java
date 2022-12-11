
package content.common;

public final class Result<S extends AbstractState> {

    private final S state;
    
    public Result(S state) {
        this.state = state;
    }

    public ResultType getResultType() {
        return state.isFinalState() ? ResultType.ACEITO : ResultType.REJEITADO;
    }

    public S getState() {
        return state;
    }
    
}
