
package content.finite.common;

import java.util.HashSet;
import java.util.Objects;

import content.common.Symbol;
import content.common.Transition;
import content.utils.DefaultSymbol;

import java.util.LinkedHashSet;

public class FTransition implements Transition<FState> {
    
    private final LinkedHashSet<Symbol> knownSymbols;
    
    private FState next;

    public FTransition() {
        this.knownSymbols = new LinkedHashSet<>();
        this.next = new FState("Dead State", false);
    }
    
    
    public FTransition when(String symbol) {
        return when(new DefaultSymbol(symbol));
    }
    
    
    public FTransition when(Symbol symbol) {
        knownSymbols.add(symbol);
        return this;
    }
    
    public HashSet<Symbol> getKnownSymbols() {
        return knownSymbols;
    }
    
    
    @Override
    public void goTo(FState state) {
        next = state;
    }
    

    @Override
    public FState getNext() {
        return next;
    }
    
    
    @Override
    public boolean isValid() {
        return next != null && !knownSymbols.isEmpty();
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.knownSymbols);
        hash = 53 * hash + Objects.hashCode(this.next);
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
        final FTransition other = (FTransition) obj;
        if (!Objects.equals(this.knownSymbols, other.knownSymbols)) {
            return false;
        }
        if (!Objects.equals(this.next, other.next)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "FTransition{" + "knwonSymbols=" + knownSymbols + ", next=" + next + '}';
    }

}
