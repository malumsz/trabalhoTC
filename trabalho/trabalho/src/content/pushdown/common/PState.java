
package content.pushdown.common;

import java.util.List;
import java.util.Objects;

import content.common.AbstractState;
import content.common.Symbol;
import content.utils.Epsilon;

import java.util.LinkedList;

public class PState extends AbstractState {

    private final LinkedList<PTransition> transition;
    
    public PState(String label) {
        this(label, false);
    }
    
    public PState(String label, boolean finalState) {
        super(label, finalState);
        this.transition = new LinkedList<>();
    }
    

    public PTransition trans() {
        transition.addLast(new PTransition());
        return transition.getLast();
    }
    
    
    @Override
    public List<PTransition> getTransitions() {
        return transition;
    }
    
    
    @Override
    public PTransition getTransition(Symbol symbol) {
        for (PTransition t : transition) {
            if (t.getKnownSymbols().contains(symbol)) {
                return t;
            }
        }
        return new PTransition();
    }
    
    
    public List<PTransition> getEpsilonTransitions() {
        return getTransitions(new Epsilon());
    }
    
    
    @Override
    public List<PTransition> getTransitions(Symbol symbol) {
        List<PTransition> trans = new LinkedList<>();
        
        transition.stream().filter(
            (t) -> (t.getKnownSymbols().contains(symbol))
        ).forEach(
            (t) -> { trans.add(t);}
        );
        
        return trans;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.label);
        hash = 23 * hash + (this.isFinalState ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.transition);
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
        final PState other = (PState) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (this.isFinalState != other.isFinalState) {
            return false;
        }
        if (!Objects.equals(this.transition, other.transition)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "PState{" + "label=" + label + ", finalState=" + isFinalState + '}';
    }

}
