
package content.common;

import java.util.List;

public abstract class AbstractState {

    protected final String label;
    
    protected final boolean isFinalState;
    
    //Constroi um estado com o label especificado.
    public AbstractState(String label) {
        this(label, false);
    }
    
    public AbstractState(String label, boolean finalState) {
        this.label = label;
        this.isFinalState = finalState;
    }

    public boolean isFinalState() {
        return isFinalState;
    }
    
    public String getLabel() {
        return label;
    }
    
    public abstract List getTransitions();
    
    public abstract Transition getTransition(Symbol symbol);
    
    public abstract List getTransitions(Symbol symbol);
}
