package br.com.springz.model;

import br.com.springz.dtoform.EnderecoForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Table(name = "tb_endereco")
@Getter @Setter
public class Endereco {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;


    public Endereco() {
    }


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
