
package content.turing;

import content.common.Symbol;
import content.common.Tape;
import content.turing.common.TState;
import content.turing.runner.DTMRunner;
import content.utils.DefaultSymbol;

public class Main {
    public static void main(String[] args) {
        Symbol one  = new DefaultSymbol("1");
        Symbol zero = new DefaultSymbol("0");
        
        TState q0 = new TState("q0");
        TState q1 = new TState("q1");
        TState q2 = new TState("q2");
        TState qh = new TState("qH", true);
        
        q0.trans().reads(zero).write(zero).stay().goTo(qh);
        q0.trans().reads(one).write(zero).left().goTo(q1);
        
        q1.trans().reads(one).write(zero).left().goTo(q1);
        q1.trans().reads(zero).write(one).stay().goTo(q2);
        
        q2.trans().reads(zero).write(zero).left().goTo(q0);
        q2.trans().reads(one).write(one).right().goTo(q0);
        
        Tape word0 = new Tape(zero, one, zero, zero, one, one, one, zero, one);
        
        DTMRunner runner0 = new DTMRunner(word0, 4, q0);
        runner0.compute();
        
        System.out.println("Palavra: " + word0 + "\tfoi " + runner0.getResult().getResultType());
    }
}
