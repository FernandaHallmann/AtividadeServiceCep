package br.unipar.programacaointernet.serviceccep.servicecep.service;

import br.unipar.programacaointernet.serviceccep.servicecep.model.Endereco;
import br.unipar.programacaointernet.serviceccep.servicecep.model.Usuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface EnderecoSEI {

  @WebMethod
  Endereco consultaEndereco(@WebParam(name = "idEndereco") Long idEndereco);

  @WebMethod
  Endereco consultaCep(@WebParam(name = "cep") String cep);

  @WebMethod
  String salvaEndereco(@WebParam(name = "cep") String cep);

  @WebMethod
  String editaEndereco(@WebParam(name = "idEndereco") Long idEndereco, @WebParam(name = "complemento") String complemento);

  @WebMethod
  String deletaEndereco(@WebParam(name = "cep") String cep);
}
