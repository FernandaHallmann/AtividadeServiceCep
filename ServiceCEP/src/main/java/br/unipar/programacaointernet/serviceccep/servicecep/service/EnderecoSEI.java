package br.unipar.programacaointernet.serviceccep.servicecep.service;

import br.unipar.programacaointernet.serviceccep.servicecep.model.Endereco;
import br.unipar.programacaointernet.serviceccep.servicecep.model.Usuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface EnderecoSEI {

  @WebMethod
  Endereco consultaEndereco(@WebParam(name = "idEndereco") Long idEndereco);

  @WebMethod
  List<Endereco> consultaTodosEnderecos();

  @WebMethod
  Endereco consultaCep(@WebParam(name = "cep") String cep);

  @WebMethod
  String salvaEndereco(@WebParam(name = "cep") String cep);

  @WebMethod
  String editaEndereco(
          @WebParam(name = "idEndereco") Long idEndereco,
          @WebParam(name = "cep") String cep,
          @WebParam(name = "logradouro") String logradouro,
          @WebParam(name = "complemento") String complemento,
          @WebParam(name = "bairro") String bairro,
          @WebParam(name = "localidade") String localidade,
          @WebParam(name = "uf") String uf,
          @WebParam(name = "ibge") String ibge,
          @WebParam(name = "gia") String gia,
          @WebParam(name = "ddd") String ddd,
          @WebParam(name = "siafi") String siafi
  );

  @WebMethod
  String deletaEnderecoByCep(@WebParam(name = "cep") String cep);

  @WebMethod
  String deletaEndereco(@WebParam(name = "idEndereco") Long idEndereco);
}
