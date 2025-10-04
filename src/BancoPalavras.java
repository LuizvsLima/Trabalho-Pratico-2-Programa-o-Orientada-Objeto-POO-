import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.Normalizer;

public class BancoPalavras {

    private List<String> palavras;

    public BancoPalavras() {
        this.palavras = new ArrayList<>();
    }

    /**
     * Carrega as palavras de um arquivo de texto.
     * @param caminhoArquivo O caminho do arquivo que contém as palavras.
     * @throws IOException se houver um erro de leitura do arquivo.
     */
    public void carregarPalavras(String caminhoArquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Remove acentos e caracteres especiais, depois converte para maiúsculas
                String palavraNormalizada = Normalizer.normalize(linha, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                this.palavras.add(palavraNormalizada.trim().toUpperCase());
            }
        }
        if (this.palavras.isEmpty()) {
            throw new IOException("O arquivo de palavras está vazio.");
        }
    }

    /**
     * Seleciona uma palavra aleatória da lista.
     * @return Uma palavra aleatória de 5 letras.
     */
    public String getPalavraAleatoria() {
        if (this.palavras.isEmpty()) {
            throw new IllegalStateException("O banco de palavras não foi carregado ou está vazio.");
        }
        Random random = new Random();
        int indice = random.nextInt(this.palavras.size());
        return this.palavras.get(indice);
    }
}