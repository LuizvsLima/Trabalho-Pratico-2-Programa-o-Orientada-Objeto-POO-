public class GameController {

    private Jogo jogo;

    public GameController(Jogo jogo) {
        this.jogo = jogo;
    }

    public void iniciarNovaPartida() {
        jogo.iniciarNovaPartida();
    }

    public EstadoLetra[] processarTentativa(String tentativa) {
        Partida partida = jogo.getPartidaAtual();
        if (partida == null) {
            System.out.println("Nenhuma partida em andamento.");
            return null;
        }

        EstadoLetra[] resultado = partida.verificarTentativa(tentativa);
        
        if (partida.isVencedor(tentativa)) {
            jogo.finalizarPartida(true);
        } else if (partida.isFimDeJogo()) {
            jogo.finalizarPartida(false);
        }

        return resultado;
    }
    
    public Usuario getUsuarioAtual() {
        return jogo.getUsuarioAtual();
    }
    
    public Partida getPartidaAtual() {
        return jogo.getPartidaAtual();
    }
    
    public void sairDoJogo() {
        jogo.salvarDados();
        System.exit(0);
    }
}