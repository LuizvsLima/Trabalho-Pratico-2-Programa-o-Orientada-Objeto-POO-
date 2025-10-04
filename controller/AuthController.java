public class AuthController {

    private Jogo jogo;

    public AuthController(Jogo jogo) {
        this.jogo = jogo;
    }

    public Usuario handleLogin(String username, String password) {
        return jogo.login(username, password);
    }

    public boolean handleCadastro(String username, String password) {
        return jogo.cadastrarUsuario(username, password);
    }
}