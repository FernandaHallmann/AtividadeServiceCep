package br.unipar.programacaointernet.serviceccep.servicecep;

import br.unipar.programacaointernet.serviceccep.servicecep.dao.EnderecoDAO;
import br.unipar.programacaointernet.serviceccep.servicecep.dao.EnderecoDAOImpl;
import br.unipar.programacaointernet.serviceccep.servicecep.dao.UsuarioDAO;
import br.unipar.programacaointernet.serviceccep.servicecep.dao.UsuarioDAOImpl;
import br.unipar.programacaointernet.serviceccep.servicecep.model.Endereco;
import br.unipar.programacaointernet.serviceccep.servicecep.model.Usuario;
import br.unipar.programacaointernet.serviceccep.servicecep.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    try {
      EntityManagerUtil.getEntityManagerFactory();

      salvarUsuario();
//      editarUsuario();
//      deletarUsuario();
//      buscarUsuarioPorId();
//      buscarTodosUsuarios();

//      salvarEndereco();
//      editarEndereco();
//      deletarEndereco();
//      buscarEnderecoPorId();
//      buscarTodosEnderecos();

      EntityManagerUtil.closeEntityManagerFactory();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static void salvarEndereco() {
    try {
      EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());
      enderecoDAO.save(getViaCep("85812181"));
    } catch (Exception e) {
      System.out.println();
    }
  }

  private static void editarEndereco() {
    EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

    Endereco endereco = enderecoDAO.findById(1L);

    endereco.setCep("85802190");

    enderecoDAO.update(endereco);
  }

  private static void deletarEndereco() {
    EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

    Endereco endereco = enderecoDAO.findById(1L);
    enderecoDAO.delete(endereco);
  }

  private static void buscarEnderecoPorId() {
    EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

    Endereco endereco = enderecoDAO.findById(1L);

    System.out.println("Endereço: " + endereco + " encontrado com sucesso!");
  }

  private static void buscarTodosEnderecos() {
    EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

    List<Endereco> enderecos = enderecoDAO.findAll();

    for (Endereco endereco : enderecos) {
      System.out.println("Usuário " + endereco.getCep() + " encontrado com sucesso!");
    }
  }

  private static Endereco getViaCep(String cep) throws Exception{
    URL url = new URL("http://viacep.com.br/ws/" +  cep.replace("-",
            "").replace(".", "") + "/xml/");

    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

    String inputLine;
    String result = "";

    while ((inputLine = in.readLine()) != null)
      result += inputLine;

    in.close();
    return Endereco.unmarshalFromString(result);
  }

  private static void salvarUsuario() {
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

    Usuario usuario = new Usuario();

    usuario.setNome("Bart");
    usuario.setLogin("Bart");
    usuario.setSenha("1234");

    usuarioDAO.save(usuario);
  }

  private static void editarUsuario() {
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

    Usuario usuario = usuarioDAO.findById(1L);

    usuario.setSenha("4321");

    usuarioDAO.update(usuario);
  }

  private static void deletarUsuario() {
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

    Usuario usuario = usuarioDAO.findById(1L);
    usuarioDAO.delete(usuario);
  }

  private static void buscarUsuarioPorId() {
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

    Usuario usuario = usuarioDAO.findById(1L);

    System.out.println("Usuário " + usuario.getNome() + " encontrado com sucesso!");
  }

  private static void buscarTodosUsuarios() {
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

    List<Usuario> usuarios = usuarioDAO.findAll();

    for (Usuario usuario : usuarios) {
      System.out.println("Usuário " + usuario.getNome() + " encontrado com sucesso!");
    }
  }
}
