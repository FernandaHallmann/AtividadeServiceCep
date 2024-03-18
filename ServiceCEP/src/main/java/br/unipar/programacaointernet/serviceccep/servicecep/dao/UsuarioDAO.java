package br.unipar.programacaointernet.serviceccep.servicecep.dao;

import br.unipar.programacaointernet.serviceccep.servicecep.model.Usuario;

import java.util.List;

public interface UsuarioDAO {
  void save(Usuario usuario);
  void update(Usuario usuario);
  void delete (Usuario usuario);

  Usuario findById(Long id);
  List<Usuario> findAll();
}
