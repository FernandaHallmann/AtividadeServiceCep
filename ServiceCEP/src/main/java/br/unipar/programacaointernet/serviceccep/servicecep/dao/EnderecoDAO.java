package br.unipar.programacaointernet.serviceccep.servicecep.dao;

import br.unipar.programacaointernet.serviceccep.servicecep.model.Endereco;
import br.unipar.programacaointernet.serviceccep.servicecep.model.Usuario;

import java.util.List;

public interface EnderecoDAO {
  void save(Endereco endereco);
  void update(Endereco endereco);
  void delete (Endereco endereco);

  Endereco findById(Long id);

  Endereco findByCep(String cep);

  List<Endereco> findAll();
}
