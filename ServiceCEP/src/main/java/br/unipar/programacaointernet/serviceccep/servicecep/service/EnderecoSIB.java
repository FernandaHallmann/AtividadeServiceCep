package br.unipar.programacaointernet.serviceccep.servicecep.service;

import br.unipar.programacaointernet.serviceccep.servicecep.dao.EnderecoDAO;
import br.unipar.programacaointernet.serviceccep.servicecep.dao.EnderecoDAOImpl;
import br.unipar.programacaointernet.serviceccep.servicecep.model.Endereco;
import br.unipar.programacaointernet.serviceccep.servicecep.util.EntityManagerUtil;
import jakarta.jws.WebService;
import jakarta.persistence.NoResultException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.List;

@WebService(endpointInterface = "br.unipar.programacaointernet.serviceccep.servicecep.service.EnderecoSEI")
public class EnderecoSIB implements EnderecoSEI {
    @Override
    public Endereco consultaEndereco(Long idEndereco) {
        EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

        Endereco endereco = enderecoDAO.findById(idEndereco);

        return endereco;
    }

    @Override
    public List<Endereco> consultaTodosEnderecos() {
        EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

        List<Endereco> enderecos = enderecoDAO.findAll();

        return enderecos;
    }

    @Override
    public Endereco consultaCep(String cep) {
        EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

        try {
            Endereco endereco = enderecoDAO.findByCep(cep);
            return endereco;
        } catch (NoResultException n) {
            try {
                URL url = new URL("http://viacep.com.br/ws/" +  cep.replace("-",
                        "").replace(".", "") + "/xml/");

                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

                String inputLine;
                String result = "";

                while ((inputLine = in.readLine()) != null)
                    result += inputLine;

                in.close();
                Endereco endereco = new Endereco();
                endereco = Endereco.unmarshalFromString(result);

                enderecoDAO.save(endereco);
                return endereco;
            } catch (Exception a) {
                return null;
            }
        }


    }

    @Override
    public String salvaEndereco(String cep) {
        EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

        try {
            URL url = new URL("http://viacep.com.br/ws/" +  cep.replace("-",
                    "").replace(".", "") + "/xml/");

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            String result = "";

            while ((inputLine = in.readLine()) != null)
                result += inputLine;

            in.close();
            Endereco endereco = new Endereco();
            endereco = Endereco.unmarshalFromString(result);

            System.out.println(endereco);
            enderecoDAO.save(endereco);

            return "Endereço " + endereco.getCep() + " salvo com sucesso!";
        } catch (Exception e) {
            return "Erro ao salvar endereço";
        }
    }

    @Override
    public String editaEndereco(Long idEndereco, String cep, String logradouro, String complemento, String bairro, String localidade, String uf, String ibge, String gia, String ddd, String siafi) {
        EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

        Endereco endereco = enderecoDAO.findById(idEndereco);

        endereco.setComplemento(complemento);
        endereco.setCep(cep);
        endereco.setLogradouro(logradouro);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setLocalidade(localidade);
        endereco.setUf(uf);
        endereco.setIbge(ibge);
        endereco.setGia(gia);
        endereco.setDdd(ddd);
        endereco.setSiafi(siafi);

        enderecoDAO.update(endereco);

        return "Endereço " + endereco.getCep() + " editado com sucesso!";
    }

    @Override
    public String deletaEnderecoByCep(String cep) {
        EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

        Endereco endereco = enderecoDAO.findByCep(cep);

        enderecoDAO.delete(endereco);

        return "Endereço " + endereco.getCep() + " deletado com sucesso!";
    }

    @Override
    public String deletaEndereco(Long idEndereco) {
        EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

        Endereco endereco = enderecoDAO.findById(idEndereco);

        enderecoDAO.delete(endereco);

        return "Endereço " + endereco.getCep() + " deletado com sucesso!";
    }
}
