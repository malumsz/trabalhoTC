
package content.finite.common;

import java.util.List;
import java.util.Objects;

import content.common.AbstractState;
import content.common.Symbol;
import content.utils.Epsilon;

import java.util.LinkedList;

public class FState extends AbstractState {

    private final LinkedList<FTransition> transitions;

    public FState(String label) {
        this(label, false);
    }

    
    public FState(String label, boolean finalState) {
        super(label, finalState);
        this.transitions = new LinkedList<>();
    }
    
    
    public FTransition trans() {
        transitions.addLast(new FTransition());
        return transitions.getLast();
    }
    

    @Override
    public List<FTransition> getTransitions() {
        return transitions;
    }
    
    
    @Override
    public FTransition getTransition(Symbol symbol) {
        for (FTransition t : transitions) {
            if (t.getKnownSymbols().contains(symbol)) {
                return t;
            }
        }
        return new FTransition();
    }
    
    
    public List<FTransition> getEpsilonTransitions() {
        return getTransitions(new Epsilon());
    }
    
    
    @Override
    public List<FTransition> getTransitions(Symbol symbol) {
        List<FTransition> trans = new LinkedList<>();
        
        transitions.stream().filter(
            (t) -> t.getKnownSymbols().contains(symbol)
        ).forEach(
            (t) -> { trans.add(t); }
        );

        return trans;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.isFinalState ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.label);
        hash = 89 * hash + Objects.hashCode(this.transitions);
        return hash;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FState other = (FState) obj;
        if (this.isFinalState != other.isFinalState) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (!Objects.equals(this.transitions, other.transitions)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "FState{" + "label=" + label + ", finalState=" + isFinalState + '}';
    }

}
