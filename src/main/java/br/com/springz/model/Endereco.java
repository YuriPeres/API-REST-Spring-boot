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

    @OneToOne(mappedBy = "endereco")
    private Funcionario funcionario;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;


    public Endereco() {
    }

    public Endereco(Long id, Funcionario funcionario, String cep, String logradouro, String complemento, String bairro, String localidade, String uf) {
        this.funcionario = funcionario;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public Endereco(EnderecoForm enderecoForm) {
        this.cep = enderecoForm.getCep();
        this.logradouro = enderecoForm.getLogradouro();
        this.complemento = enderecoForm.getComplemento();
        this.bairro = enderecoForm.getBairro();
        this.localidade = enderecoForm.getLocalidade();
        this.uf = enderecoForm.getUf();

    }

    public Endereco(String cep, String logradouro, String complemento, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", funcionario=" + funcionario +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }


}
