package br.unipar.programacaointernet.serviceccep.servicecep.service;

import jakarta.xml.ws.Endpoint;

public class EnderecoPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/endereco", new EnderecoSIB());

        System.out.println("Endereço Endpoint publicado com sucesso!");
    }
}
