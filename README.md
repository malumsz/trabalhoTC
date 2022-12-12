# Simulação de Máquinas de Estado
Implementação em linguagem **JAVA**.

- Máquinas de Estado Finito
- Máquinas de Pilha
- Máquinas de Turing.


Build
----------
Os executáveis estão localizados na pasta `\exemplos\content` onde estarão separados em três pastas principais sendo elas `\finite`,`\pushdown` e `\turing`, a primeira representa um autômato finito com dois exemplos (`termina com` e `multiplos de três`), a segunda um autômato de pilha também com dois exemplos (`deterministico` e `não deterministico`), e por fim um simulador de Máquina de Turing.

Conteúdo
----------
#### Classes de Estado ####
* ` FState `: Estado Finito
* ` PState `: Estado de Pilha
* ` TState `: Estado da Máquina de Turing

#### Classes de Execução ####
* ` DFARunner `:  Autômato Finito Determinístico
* ` NFARunner `:  Autômato Finito Não-Determinístico
* ` DPDARunner `: Autômato de Pilha Determinístico
* ` NPDARunner `: Autômato de Pilha Não-Determinístico
* ` DTMRunner  `: Máquina de Turing Determinística

Exemplos
----------
### Autômato de estado finito "`termina com`" ###
<h1 align="center">
    <br>
    <img width="300" src="https://github.com/malumsz/trabalhoTC/blob/main/trabalho/trabalho/exemplos/content/finite/endwith.png" alt="imagem">
    <br>
</h1>

1) Definir estados.

```java
 FState q0 = new FState("q0");
 FState p1 = new FState("p1");
 FState p2 = new FState("p2", true);
 FState p3 = new FState("p3");
 FState p4 = new FState("p4", true);
 FState p5 = new FState("p5");
 FState p6 = new FState("p6", true);
```

2) Definir transições.

```java
 q0.trans().when(a).when(b).when(c).goTo(q0);
 q0.trans().when(a).goTo(p3);
 q0.trans().when(b).goTo(p1);
 q0.trans().when(c).goTo(p5);

 p1.trans().when(c).goTo(p2);
 p3.trans().when(b).goTo(p4);
 p5.trans().when(a).goTo(p6);
```

3) Criar palavras teste.
```java
 Word word0 = new Word(a, b, c, b, c);
 Word word1 = new Word(a, b, c, c, a);
 Word word2 = new Word(b, b, c, a, b);
 Word word3 = new Word(a, b, c, c, b);
 Word word4 = new Word(a, b, c, a, c);
 Word word5 = new Word(b, b, c, b, a); 
```

4) Computar sua String.

```java
 NFARunner runner0 = new NFARunner(word0, q0);
 NFARunner runner1 = new NFARunner(word1, q0);
 NFARunner runner2 = new NFARunner(word2, q0);
 NFARunner runner3 = new NFARunner(word3, q0);
 NFARunner runner4 = new NFARunner(word4, q0);
 NFARunner runner5 = new NFARunner(word5, q0);
 
 runner0.compute();
 runner1.compute();
 runner2.compute();
 runner3.compute();
 runner4.compute();
 runner5.compute();
```

5) Resultado no terminal: 
```java
Palavra: [{a}, {b}, {c}, {b}, {c}]      foi ACEITO
Palavra: [{a}, {b}, {c}, {c}, {a}]      foi ACEITO
Palavra: [{b}, {b}, {c}, {a}, {b}]      foi ACEITO
Palavra: [{a}, {b}, {c}, {c}, {b}]      foi REJEITADO
Palavra: [{a}, {b}, {c}, {a}, {c}]      foi REJEITADO
Palavra: [{b}, {b}, {c}, {b}, {a}]      foi REJEITADO
```

### Autômato de Pilha Determinístico ###
Exemplo do funcionamento do autômato de pilha determinístico:
<h1 align="center">
    <br>
    <img width="500" src="https://github.com/malumsz/trabalhoTC/blob/main/trabalho/trabalho/exemplos/content/pushdown/dpda_classic.png" alt="imagem">
    <br>
</h1>


