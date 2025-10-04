import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JPasswordField campoConfirmaSenha;

    public TelaCadastro(final AuthController authController, final JFrame telaAnterior) {

        setTitle("TERMO - Cadastro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        painel.add(new JLabel("Nome de Usuário:"));
        campoUsuario = new JTextField();
        painel.add(campoUsuario);

        painel.add(new JLabel("Senha:"));
        campoSenha = new JPasswordField();
        painel.add(campoSenha);

        painel.add(new JLabel("Confirmar Senha:"));
        campoConfirmaSenha = new JPasswordField();
        painel.add(campoConfirmaSenha);

        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoVoltar = new JButton("Voltar");

        painel.add(botaoCadastrar);
        painel.add(botaoVoltar);

        add(painel);

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());
                String confirmaSenha = new String(campoConfirmaSenha.getPassword());

                if (!senha.equals(confirmaSenha)) {
                    JOptionPane.showMessageDialog(TelaCadastro.this, "As senhas não coincidem.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (authController.handleCadastro(usuario, senha)) {
                    JOptionPane.showMessageDialog(TelaCadastro.this, "Cadastro realizado com sucesso!");
                    dispose();
                    telaAnterior.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(TelaCadastro.this, "Nome de usuário já existe.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                telaAnterior.setVisible(true);
            }
        });

        setVisible(true);
    }
}