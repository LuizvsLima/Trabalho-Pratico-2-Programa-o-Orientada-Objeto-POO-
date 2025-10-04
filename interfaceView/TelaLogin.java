import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private Jogo jogo;

    public TelaLogin(final AuthController authController, final Jogo jogo) {
        this.jogo = jogo;

        setTitle("TERMO - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        painel.add(new JLabel("Nome de Usuário:"));
        campoUsuario = new JTextField();
        painel.add(campoUsuario);

        painel.add(new JLabel("Senha:"));
        campoSenha = new JPasswordField();
        painel.add(campoSenha);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton botaoEntrar = new JButton("Entrar");
        JButton botaoCadastro = new JButton("Cadastrar");

        painelBotoes.add(botaoEntrar);
        painelBotoes.add(botaoCadastro);

        painel.add(painelBotoes);

        add(painel);

        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());
                Usuario user = authController.handleLogin(usuario, senha);
                if (user != null) {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Login bem-sucedido!");
                    abrirTelaPrincipalDoJogo();
                } else {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Usuário ou senha inválidos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastro(authController, TelaLogin.this);
                setVisible(false);
            }
        });

        setVisible(true);
    }

    private void abrirTelaPrincipalDoJogo() {
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal(jogo, new GameController(jogo));
        });
    }
}