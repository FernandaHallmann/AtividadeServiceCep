package br.unipar.programacaointernet.serviceccep.servicecep.service;

import br.unipar.programacaointernet.serviceccep.servicecep.dao.UsuarioDAO;
import br.unipar.programacaointernet.serviceccep.servicecep.dao.UsuarioDAOImpl;
import br.unipar.programacaointernet.serviceccep.servicecep.model.Usuario;
import br.unipar.programacaointernet.serviceccep.servicecep.util.EntityManagerUtil;
import jakarta.jws.WebService;

@WebService(endpointInterface = "br.unipar.programacaointernet.serviceccep.servicecep.service.UsuarioSEI")
public class UsuarioSIB implements UsuarioSEI{
  @Override
  public String boasVindas(String nome) {
    return "Bem-Vindo(a) " + nome + "!";
  }

  @Override
  public Usuario consultaUsuario(Long idUsuario) {
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

    Usuario usuario = usuarioDAO.findById(idUsuario);

    return usuario;
  }

  @Override
  public String salvaUsuario(String nome, String login, String senha) {
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

    Usuario usuario = new Usuario();

    usuario.setNome(nome);
    usuario.setLogin(login);
    usuario.setSenha(senha);

    usuarioDAO.save(usuario);

    return "Usuário " + nome + " salvo com sucesso!";
  }

  @Override
  public String editaSenhaUsuario(Long idUsuario, String novaSenha) {
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

    Usuario usuario = usuarioDAO.findById(idUsuario);

    usuario.setSenha(novaSenha);

    usuarioDAO.update(usuario);

    return "Usuário " + usuario.getNome() + " editado com sucesso!";
  }

  @Override
  public String deletaUsuario(Long idUsuario) {
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

    Usuario usuario = usuarioDAO.findById(idUsuario);

    usuarioDAO.delete(usuario);

    return "Usuário " + usuario.getNome() + " deletado com sucesso!";
  }
}
