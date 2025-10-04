import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.text.Normalizer;

public class TelaPrincipal extends JFrame {

    private Jogo jogo;
    private GameController gameController;

    private JLabel labelEstatisticas;
    private JLabel labelTentativasRestantes;
    private JPanel painelTentativas;
    private JTextField campoEntrada;
    private JButton botaoSubmeter;

    private List<JPanel> paineisLinhaTentativa;

    public TelaPrincipal(Jogo jogo, GameController gameController) {
        this.jogo = jogo;
        this.gameController = gameController;

        setTitle("TERMO - " + jogo.getUsuarioAtual().getUsername());
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                gameController.sairDoJogo();
            }
        });

        setLayout(new BorderLayout(10, 10));

        JPanel painelSuperior = new JPanel(new BorderLayout());
        JPanel painelStats = new JPanel(new GridLayout(2, 1));
        
        labelEstatisticas = new JLabel();
        labelEstatisticas.setHorizontalAlignment(SwingConstants.CENTER);
        
        labelTentativasRestantes = new JLabel();
        labelTentativasRestantes.setHorizontalAlignment(SwingConstants.CENTER);
        
        painelStats.add(labelEstatisticas);
        painelStats.add(labelTentativasRestantes);
        
        painelSuperior.add(painelStats, BorderLayout.CENTER);
        
        JPanel painelBotoesAcao = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton botaoNovaPartida = new JButton("Nova Partida");
        JButton botaoSair = new JButton("Sair");
        
        painelBotoesAcao.add(botaoNovaPartida);
        painelBotoesAcao.add(botaoSair);
        
        painelSuperior.add(painelBotoesAcao, BorderLayout.SOUTH);
        
        add(painelSuperior, BorderLayout.NORTH);

        painelTentativas = new JPanel(new GridLayout(6, 5, 5, 5));
        painelTentativas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        paineisLinhaTentativa = new ArrayList<>();
        criarGradeTentativas();
        add(painelTentativas, BorderLayout.CENTER);

        JPanel painelEntrada = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        campoEntrada = new JTextField(10);
        botaoSubmeter = new JButton("Submeter");
        painelEntrada.add(new JLabel("Sua palavra:"));
        painelEntrada.add(campoEntrada);
        painelEntrada.add(botaoSubmeter);
        
        add(painelEntrada, BorderLayout.SOUTH);

        botaoNovaPartida.addActionListener(e -> iniciarNovoJogo());
        botaoSair.addActionListener(e -> gameController.sairDoJogo());

        botaoSubmeter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tentativa = campoEntrada.getText().trim();
                if (tentativa.length() == 5) {
                    processarTentativa(tentativa);
                    campoEntrada.setText("");
                } else {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, "Por favor, insira uma palavra de 5 letras.", "Entrada Inválida", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        iniciarNovoJogo();
        setVisible(true);
    }
    
    private void criarGradeTentativas() {
        painelTentativas.removeAll();
        paineisLinhaTentativa.clear();
        for (int i = 0; i < 6; i++) {
            JPanel linha = new JPanel(new GridLayout(1, 5, 5, 5));
            for (int j = 0; j < 5; j++) {
                JLabel labelLetra = new JLabel(" ");
                labelLetra.setPreferredSize(new Dimension(50, 50));
                labelLetra.setOpaque(true);
                labelLetra.setBackground(Color.LIGHT_GRAY);
                labelLetra.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labelLetra.setHorizontalAlignment(SwingConstants.CENTER);
                labelLetra.setFont(new Font("Arial", Font.BOLD, 24));
                linha.add(labelLetra);
            }
            paineisLinhaTentativa.add(linha);
            painelTentativas.add(linha);
        }
        painelTentativas.revalidate();
        painelTentativas.repaint();
    }

    private void iniciarNovoJogo() {
        gameController.iniciarNovaPartida();
        atualizarInformacoesDoJogo();
        campoEntrada.setEnabled(true);
        botaoSubmeter.setEnabled(true);
        criarGradeTentativas();
    }
    
    private void atualizarInformacoesDoJogo() {
        Usuario usuario = jogo.getUsuarioAtual();
        Partida partida = jogo.getPartidaAtual();
        
        labelEstatisticas.setText(
            "Jogos: " + usuario.getPartidasJogadas() + 
            " | Vitórias: " + usuario.getPartidasGanhas() + 
            " | Derrotas: " + usuario.getPartidasPerdidas()
        );
        
        labelTentativasRestantes.setText("Tentativas restantes: " + partida.getTentativasRestantes());
    }
    
   private void processarTentativa(String tentativa) {
        Partida partida = jogo.getPartidaAtual();
        
        // Verifica se ainda há tentativas. A 6ª tentativa é processada quando tentativasRestantes é 1.
        if (partida.getTentativasRestantes() > 0) {
            
            // O índice da linha a ser atualizada é baseado no número de tentativas feitas
            // A primeira tentativa é 0, a segunda é 1, e a sexta é 5
            int indiceTentativa = 6 - partida.getTentativasRestantes();
            
            EstadoLetra[] resultado = gameController.processarTentativa(tentativa);

            if (resultado != null) {
                // Para exibir na interface sem o acento, normalizamos aqui.
                String tentativaNormalizada = Normalizer.normalize(tentativa, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

                // Atualiza a linha de tentativas na interface.
                JPanel linha = paineisLinhaTentativa.get(indiceTentativa);
                for (int i = 0; i < 5; i++) {
                    JLabel labelLetra = (JLabel) linha.getComponent(i);
                    labelLetra.setText(String.valueOf(tentativaNormalizada.charAt(i)));

                    // Define a cor de fundo com base no estado da letra.
                    switch (resultado[i]) {
                        case CORRETO:
                            labelLetra.setBackground(Color.GREEN);
                            break;
                        case POSICAO_ERRADA:
                            labelLetra.setBackground(Color.YELLOW);
                            break;
                        case NAO_EXISTE:
                            labelLetra.setBackground(Color.GRAY);
                            break;
                    }
                }

                atualizarInformacoesDoJogo();
                
                // *** IMPORTANTE: A verificação de fim de jogo é a ÚLTIMA coisa a acontecer. ***
                // Só desativa a entrada após a última tentativa ser exibida.
                if (partida.isVencedor(tentativa)) {
                    JOptionPane.showMessageDialog(this, "Parabéns! Você venceu!", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
                    campoEntrada.setEnabled(false);
                    botaoSubmeter.setEnabled(false);
                } else if (partida.getTentativasRestantes() == 0) { // Verifica se as tentativas acabaram
                    JOptionPane.showMessageDialog(this, "Fim de jogo. A palavra secreta era: " + partida.getPalavraSecreta(), "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
                    campoEntrada.setEnabled(false);
                    botaoSubmeter.setEnabled(false);
                }
            }
        } else {
            // Se já não houver tentativas restantes, não faz nada
            JOptionPane.showMessageDialog(this, "O jogo já terminou. Inicie uma nova partida.", "Fim de Jogo", JOptionPane.WARNING_MESSAGE);
        }
    }
}