
package content.common;

import java.util.Objects;

public abstract class Symbol {
    
    private final String label;

    public Symbol(String label) {
        if (label == null || label.isEmpty()) {
            throw new IllegalArgumentException("Um objeto Simbolo não pode ser uma String vazia.");
        }
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }

    public abstract boolean compute();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.label);
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
        final Symbol other = (Symbol) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return '{' + label + '}';
    }
    
}
