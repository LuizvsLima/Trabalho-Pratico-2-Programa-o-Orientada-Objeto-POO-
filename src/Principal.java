import java.io.File;
import javax.swing.SwingUtilities;

public class Principal {

    public static void main(String[] args) {
        
        if (args.length < 1) {
            System.err.println("Erro: O caminho do arquivo de palavras não foi fornecido.");
            System.err.println("Uso: java -jar seu_jogo.jar <caminho_para_palavras.txt>");
            return;
        }

        String caminhoArquivoPalavras = args[0];

        try {
            File palavrasFile = new File(caminhoArquivoPalavras);
            if (!palavrasFile.exists()) {
                System.err.println("Erro: O arquivo de palavras não foi encontrado no caminho: " + caminhoArquivoPalavras);
                return;
            }
            
            final Jogo jogo = new Jogo(caminhoArquivoPalavras);
            final AuthController authController = new AuthController(jogo);

            SwingUtilities.invokeLater(() -> {
                new TelaLogin(authController, jogo);
            });

        } catch (Exception e) {
            System.err.println("Ocorreu um erro fatal ao iniciar o jogo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}