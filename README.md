# Trabalho Prático II - Programação Orientada a Objetos (POO)
## Jogo TERMO em Java com Interface Gráfica (Swing)


### **1. Requisitos e Funcionalidades**

* **Interface Gráfica:** O jogo possui uma interface simples e intuitiva, com botões para iniciar uma nova partida e sair do jogo.
* **Banco de Palavras:** O sistema lê um arquivo de texto com palavras de 5 letras. O caminho para este arquivo deve ser fornecido como argumento na linha de comando.
* **Lógica do Jogo:** O jogador tem 6 tentativas para adivinhar a palavra secreta. As dicas são fornecidas através de cores:
    * **Verde:** Letra correta na posição correta.
    * **Amarelo:** Letra correta na posição errada.
    * **Cinza:** Letra não compõe a palavra.
* **Estatísticas:** O jogo armazena e exibe as estatísticas do jogador, incluindo o número de partidas jogadas, ganhas e perdidas.
* **Tarefa Extra:** Foi implementado um sistema de controle de usuários, com tela de login e cadastro, permitindo o armazenamento do placar de vários jogadores e visualização de estatísticas individuais.

### **2. Como Compilar**
Para executar o projeto, siga estes passos no terminal.

1.  **Pré-requisitos:** Certifique-se de que o **JDK (versão 17 ou superior)** e a ferramenta **`make`** estejam instalados em seu sistema.

2.  **Navegação:** Abra o terminal e navegue até a pasta principal do projeto, onde o arquivo `Makefile` está localizado:
    ```bash
    cd [caminho_para_sua_pasta]/TP2POO/termoGame/
    ```

3.  **Compilação:** Execute o comando `make compile` para compilar o código.
    ```bash
    make compile
    ```
    Este comando compilará todos os arquivos `.java` e colocará os `.class` na pasta `out/`.

4.  **Execução:** Execute o jogo usando o comando `make run`. O programa espera o caminho para o arquivo `palavras.txt` como argumento.
    ```bash
    make run
    ```

* **Primeiro Acesso:** Na primeira tela, clique em "Cadastrar" e crie seu usuário. O sistema salva seus dados para que você possa continuar o jogo posteriormente.
* **Jogabilidade:**
    * Digite uma palavra de 5 letras no campo de entrada e submeta.
    * Observe as cores dos quadrados para receber as dicas.
    * O jogo termina ao adivinhar a palavra ou esgotar as 6 tentativas.
* **Estatísticas:** As estatísticas de jogos, vitórias e derrotas são atualizadas e salvas automaticamente.
* **Sair:** O botão "Sair" encerra a aplicação, salvando todas as suas estatísticas antes de fechar.
