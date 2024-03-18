package br.unipar.programacaointernet.serviceccep.servicecep.dao;

import br.unipar.programacaointernet.serviceccep.servicecep.model.Endereco;
import br.unipar.programacaointernet.serviceccep.servicecep.model.Usuario;
import br.unipar.programacaointernet.serviceccep.servicecep.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class EnderecoDAOImpl implements EnderecoDAO{

  private EntityManager em = EntityManagerUtil.getManager();

  public EnderecoDAOImpl(EntityManager em) {
    this.em = em;
  }

  @Override
  public void save(Endereco endereco) {
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    em.persist(endereco);
    transaction.commit();
    System.out.println("Endereço " + endereco.toString() + " salvo com sucesso!");
  }

  @Override
  public void update(Endereco endereco) {
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    em.merge(endereco);
    transaction.commit();
    System.out.println("Endereço " + endereco.toString() + " atualizado com sucesso!");
  }

  @Override
  public void delete(Endereco endereco) {
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    em.remove(endereco);
    transaction.commit();
    System.out.println("Endereço " + endereco.toString() + " removido com sucesso!");
  }

  @Override
  public Endereco findById(Long id) {
    return em.find(Endereco.class, id);
  }

  @Override
  public Endereco findByCep(String cep) {
    return em.createQuery("SELECT e FROM Endereco e WHERE cep = :cep", Endereco.class).setParameter("cep", cep).getSingleResult();
  }

  @Override
  public List<Endereco> findAll() {
    return em.createQuery("SELECT u FROM Endereco u", Endereco.class).getResultList();
  }
}
