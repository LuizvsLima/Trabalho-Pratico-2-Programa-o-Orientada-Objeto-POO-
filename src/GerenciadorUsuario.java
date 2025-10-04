import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorUsuario {

    private static final String ARQUIVO_USUARIOS = "resources/usuarios.dat";
    private Map<String, Usuario> usuarios;

    public GerenciadorUsuario() {
        this.usuarios = new HashMap<>();
        carregarUsuarios();
    }

    @SuppressWarnings("unchecked")
    public void carregarUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_USUARIOS))) {
            this.usuarios = (Map<String, Usuario>) ois.readObject();
            System.out.println("Usuários carregados com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de usuários não encontrado. Será criado um novo.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void salvarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_USUARIOS))) {
            oos.writeObject(this.usuarios);
            System.out.println("Usuários salvos com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean cadastrarUsuario(String username, String password) {
        if (usuarios.containsKey(username)) {
            System.out.println("Usuário " + username + " já existe.");
            return false;
        }
        Usuario novoUsuario = new Usuario(username, password);
        usuarios.put(username, novoUsuario);
        salvarUsuarios();
        return true;
    }

    public Usuario autenticarUsuario(String username, String password) {
        Usuario usuario = usuarios.get(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }

    public Usuario getUsuario(String username) {
        return usuarios.get(username);
    }
}