
package content.pushdown;

import content.common.Symbol;
import content.common.Word;
import content.pushdown.common.PState;
import content.pushdown.runner.NPDARunner;
import content.utils.DefaultSymbol;
import content.utils.Epsilon;

public class NPDAClassic {
    public static void main(String[] args) {
        // cria todos os simbolos
        Symbol zero    = new DefaultSymbol("0");
        Symbol one     = new DefaultSymbol("1");
        Symbol dollar  = new DefaultSymbol("$");
        Symbol epsilon = new Epsilon();
        
        // cria todos os estados
        PState q1 = new PState("q1", true);
        PState q2 = new PState("q2");
        PState q3 = new PState("q3");
        PState q4 = new PState("q4", true);
        
        // cria todas as transicoes
        q1.trans().when(epsilon).push(dollar).goTo(q2);
        
        q2.trans().when(zero).push(zero).goTo(q2);
        q2.trans().when(one).pop(zero).goTo(q3);
        
        q3.trans().when(epsilon).pop(dollar).goTo(q4);
        q3.trans().when(one).pop(zero).goTo(q3);
        
        // define todas as palavras
        //binario
        Word word0 = new Word(zero, one);
        Word word1 = new Word(zero, one, one);
        Word word2 = new Word(zero, zero, one);
        Word word3 = new Word(zero, one, zero);
        Word word4 = new Word(zero, zero, one, one);
        
        // executa
        NPDARunner runner0 = new NPDARunner(word0, q1);
        NPDARunner runner1 = new NPDARunner(word1, q1);
        NPDARunner runner2 = new NPDARunner(word2, q1);
        NPDARunner runner3 = new NPDARunner(word3, q1);
        NPDARunner runner4 = new NPDARunner(word4, q1);
        
        runner0.compute();
        runner1.compute();
        runner2.compute();
        runner3.compute();
        runner4.compute();
        
        // interpreta o resultado
        System.out.println("Palavra: " + word0 + "\tfoi " + runner0.getResult().getResultType());
        System.out.println("Palavra: " + word1 + "\tfoi " + runner1.getResult().getResultType());
        System.out.println("Palavra: " + word2 + "\tfoi " + runner2.getResult().getResultType());
        System.out.println("Palavra: " + word3 + "\tfoi " + runner3.getResult().getResultType());
        System.out.println("Palavra: " + word4 + "\tfoi " + runner4.getResult().getResultType());
    }
}
