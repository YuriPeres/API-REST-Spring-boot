package br.com.springz.model;

import br.com.springz.dtoform.EnderecoForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Table(name = "tb_endereco")
@Data @AllArgsConstructor @NoArgsConstructor
public class Endereco {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "cep")
    private String cep;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "localidade")
    private String localidade;

    @Column(name = "uf")
    private String uf;



    public Endereco(EnderecoForm enderecoForm) {
        this.cep = enderecoForm.getCep();
        this.logradouro = enderecoForm.getLogradouro();
        this.complemento = enderecoForm.getComplemento();
        this.bairro = enderecoForm.getBairro();
        this.localidade = enderecoForm.getLocalidade();
        this.uf = enderecoForm.getUf().toUpperCase();

    }

    public Endereco(String cep, String logradouro, String complemento, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

}
