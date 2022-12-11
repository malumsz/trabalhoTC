
package content.finite;

import content.common.Symbol;
import content.common.Word;
import content.finite.common.FState;
import content.finite.runner.DFARunner;
import content.utils.DefaultSymbol;

public class MultiplesOfThree {
    public static void main(String[] args) {
        // cria todos os simbolos
        Symbol one  = new DefaultSymbol("1");
        Symbol zero = new DefaultSymbol("0");
        
        // cria todos os estados
        FState s0 = new FState("S0", true);
        FState s1 = new FState("S1");
        FState s2 = new FState("S2");
        
        // cria todas as transicoes
        s0.trans().when(zero).goTo(s0);
        s0.trans().when(one).goTo(s1);
        s1.trans().when(zero).goTo(s2);
        s1.trans().when(one).goTo(s0);
        s2.trans().when(zero).goTo(s1);
        s2.trans().when(one).goTo(s2);
        
        // define todas as palavras
        // binario 
        Word word0 = new Word(zero, one, one); // tres
        Word word1 = new Word(one, one, zero); // seis
        Word word2 = new Word(one, zero, zero, one); // nove
        Word word3 = new Word(zero); // zero (tem que ser aceito)
        Word word4 = new Word(one); // um
        
        // executa
        DFARunner runner0 = new DFARunner(word0, s0);
        DFARunner runner1 = new DFARunner(word1, s0);
        DFARunner runner2 = new DFARunner(word2, s0);
        DFARunner runner3 = new DFARunner(word3, s0);
        DFARunner runner4 = new DFARunner(word4, s0);
        
        // interpreta o resultado
        runner0.compute();
        runner1.compute();
        runner2.compute();
        runner3.compute();
        runner4.compute();
        
        System.out.println("Palavra: " + word0 + "\t\t foi " + runner0.getResult().getResultType());
        System.out.println("Palavra: " + word1 + "\t\t foi " + runner1.getResult().getResultType());
        System.out.println("Palavra: " + word2 + "\t foi "   + runner2.getResult().getResultType());
        System.out.println("Palavra: " + word3 + "\t\t\t foi " + runner3.getResult().getResultType());
        System.out.println("Palavra: " + word4 + "\t\t\t foi " + runner4.getResult().getResultType());
    }
}
