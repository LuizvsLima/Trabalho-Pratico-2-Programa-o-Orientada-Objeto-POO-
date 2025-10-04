import java.util.ArrayList;
import java.util.List;

public class Jogo {

    private BancoPalavras bancoDePalavras;
    private Partida partidaAtual;
    private Usuario usuarioAtual;
    private GerenciadorUsuario gerenciadorDeUsuarios;
    private List<String> palavrasUtilizadas;

    public Jogo(String caminhoArquivoPalavras) throws Exception {
        this.bancoDePalavras = new BancoPalavras();
        this.bancoDePalavras.carregarPalavras(caminhoArquivoPalavras);

        this.gerenciadorDeUsuarios = new GerenciadorUsuario();
        this.gerenciadorDeUsuarios.carregarUsuarios();
        this.palavrasUtilizadas = new ArrayList<>();
    }
    
    public Usuario login(String username, String password) {
        this.usuarioAtual = gerenciadorDeUsuarios.autenticarUsuario(username, password);
        return this.usuarioAtual;
    }

    public boolean cadastrarUsuario(String username, String password) {
        return gerenciadorDeUsuarios.cadastrarUsuario(username, password);
    }
    
    public Usuario getUsuarioAtual() {
        return this.usuarioAtual;
    }

    public void iniciarNovaPartida() {
        String palavraSecreta;
        do {
            palavraSecreta = bancoDePalavras.getPalavraAleatoria();
        } while (palavrasUtilizadas.contains(palavraSecreta));
        
        this.partidaAtual = new Partida(palavraSecreta);
        this.palavrasUtilizadas.add(palavraSecreta);
    }

    public Partida getPartidaAtual() {
        return this.partidaAtual;
    }
    
    public void finalizarPartida(boolean vitoria) {
        if (usuarioAtual != null) {
            if (vitoria) {
                usuarioAtual.registrarVitoria();
            } else {
                usuarioAtual.registrarDerrota();
            }
            gerenciadorDeUsuarios.salvarUsuarios();
        }
    }

    public void salvarDados() {
        gerenciadorDeUsuarios.salvarUsuarios();
    }
}