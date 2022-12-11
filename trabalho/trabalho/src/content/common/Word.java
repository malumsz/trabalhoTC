
package content.common;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import content.utils.DefaultSymbol;

public class Word extends ArrayList<Symbol> {
    
    public Word(String string) {
        super();
        addAll(toSymbols(string));
    }
    
    public Word(Symbol... symbols) {
        super(Arrays.asList(symbols));
    }
    
    private List<Symbol> toSymbols(String string) {
        ArrayList<Symbol> symbols = new ArrayList<>();
        for (char c : string.toCharArray()) {
            symbols.add(new DefaultSymbol(c + ""));
        }
        return symbols;
    }
}
