import java.io.Serializable;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private int partidasJogadas;
    private int partidasGanhas;
    private int partidasPerdidas;
    private int sequenciaVitorias;
    private int melhorSequencia;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        this.partidasJogadas = 0;
        this.partidasGanhas = 0;
        this.partidasPerdidas = 0;
        this.sequenciaVitorias = 0;
        this.melhorSequencia = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPartidasJogadas() {
        return partidasJogadas;
    }

    public int getPartidasGanhas() {
        return partidasGanhas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public int getSequenciaVitorias() {
        return sequenciaVitorias;
    }

    public int getMelhorSequencia() {
        return melhorSequencia;
    }

    public void registrarVitoria() {
        this.partidasJogadas++;
        this.partidasGanhas++;
        this.sequenciaVitorias++;
        if (this.sequenciaVitorias > this.melhorSequencia) {
            this.melhorSequencia = this.sequenciaVitorias;
        }
    }

    public void registrarDerrota() {
        this.partidasJogadas++;
        this.partidasPerdidas++;
        this.sequenciaVitorias = 0;
    }
}