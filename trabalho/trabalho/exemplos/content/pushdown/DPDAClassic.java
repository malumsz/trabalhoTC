
package content.pushdown;

import content.common.Symbol;
import content.common.Word;
import content.pushdown.common.PState;
import content.pushdown.runner.DPDARunner;
import content.utils.DefaultSymbol;
import content.utils.Epsilon;

public class DPDAClassic {
    public static void main(String[] args) {
        // cria todos os simbolos
        Symbol zero    = new DefaultSymbol("0");
        Symbol one     = new DefaultSymbol("1");
        Symbol dollar  = new DefaultSymbol("$");
        Symbol epsilon = new Epsilon();
        
        // cria todos os estados
        PState q0 = new PState("q0", true);
        PState q1 = new PState("q1");
        PState q2 = new PState("q2");
        PState q3 = new PState("q3", true);
        
        // cria todas as transicoes
        q0.trans().when(zero).push(dollar).push(zero).goTo(q1);
        
        q1.trans().when(zero).pop(zero).push(zero).push(zero).goTo(q1);
        q1.trans().when(one).pop(zero).goTo(q2);
        
        q2.trans().when(epsilon).pop(dollar).goTo(q3);
        q2.trans().when(one).pop(zero).goTo(q2);
        
        // define todas as palavras
        //binario
        Word word0 = new Word(zero, zero, zero, one, one, one);
        Word word1 = new Word(zero, zero, zero, one, one);
        Word word2 = new Word(one, one, one, zero, zero, zero);
        
        // executa
        DPDARunner runner0 = new DPDARunner(word0, q0);
        DPDARunner runner1 = new DPDARunner(word1, q0);
        DPDARunner runner2 = new DPDARunner(word2, q0);
        
        runner0.compute();
        runner1.compute();
        runner2.compute();
        
        // interpreta o resultado
        System.out.println("Palavra: " + word0 + " \tfoi " + runner0.getResult().getResultType());
        System.out.println("Palavra: " + word1 + " \tfoi " + runner1.getResult().getResultType());
        System.out.println("Palavra: " + word2 + " \tfoi " + runner2.getResult().getResultType());
    }
}
