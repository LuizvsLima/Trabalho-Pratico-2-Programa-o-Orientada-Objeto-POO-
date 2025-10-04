import java.text.Normalizer;

public class Partida {

    private String palavraSecreta;
    private int tentativasRestantes;
    private static final int MAX_TENTATIVAS = 6;

    public Partida(String palavraSecreta) {
        String palavraNormalizada = Normalizer.normalize(palavraSecreta, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        this.palavraSecreta = palavraNormalizada.toUpperCase();
        this.tentativasRestantes = MAX_TENTATIVAS;
    }

    public String getPalavraSecreta() {
        return palavraSecreta;
    }

    public int getTentativasRestantes() {
        return tentativasRestantes;
    }

    /**
     * Verifica uma tentativa do jogador contra a palavra secreta.
     * Retorna um array de EstadoLetra indicando a cor de cada letra.
     * @param tentativa a palavra de 5 letras do jogador.
     * @return um array de EstadoLetra com o resultado.
     */
    public EstadoLetra[] verificarTentativa(String tentativa) {
        String tentativaNormalizada = Normalizer.normalize(tentativa, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        tentativaNormalizada = tentativaNormalizada.toUpperCase();
        
        EstadoLetra[] resultado = new EstadoLetra[5];

        char[] palavraSecretaArrayTemp = this.palavraSecreta.toCharArray();
        char[] tentativaArrayTemp = tentativaNormalizada.toCharArray();

        // 1. Primeiro, verificar as letras corretas (CORRETO - cor verde)
        for (int i = 0; i < 5; i++) {
            if (tentativaArrayTemp[i] == palavraSecretaArrayTemp[i]) {
                resultado[i] = EstadoLetra.CORRETO;
                palavraSecretaArrayTemp[i] = '_'; 
                tentativaArrayTemp[i] = '_';
            }
        }

        // 2. Depois, verificar as letras na posição errada (POSICAO_ERRADA - cor amarela)
        for (int i = 0; i < 5; i++) {
            if (tentativaArrayTemp[i] != '_') {
                boolean encontrou = false;
                for (int j = 0; j < 5; j++) {
                    if (tentativaArrayTemp[i] == palavraSecretaArrayTemp[j]) {
                        resultado[i] = EstadoLetra.POSICAO_ERRADA;
                        palavraSecretaArrayTemp[j] = '_';
                        encontrou = true;
                        break;
                    }
                }
                if (!encontrou) {
                    resultado[i] = EstadoLetra.NAO_EXISTE;
                }
            }
        }
        
        for (int i = 0; i < 5; i++) {
             if (resultado[i] == null) {
                resultado[i] = EstadoLetra.NAO_EXISTE;
            }
        }

        tentativasRestantes--;
        return resultado;
    }

    public boolean isVencedor(String tentativa) {
        String tentativaNormalizada = Normalizer.normalize(tentativa, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return tentativaNormalizada.equalsIgnoreCase(this.palavraSecreta);
    }

    public boolean isFimDeJogo() {
        return tentativasRestantes <= 0;
    }
}