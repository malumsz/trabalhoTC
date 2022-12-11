
package content.utils;

import content.common.Symbol;

public class DefaultSymbol extends Symbol {

    public DefaultSymbol(String label) {
        super(label);
    }
    
    @Override
    public boolean compute() {
        return true;
    }
    
}
