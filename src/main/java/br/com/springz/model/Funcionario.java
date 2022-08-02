package br.com.springz.model;

import br.com.springz.dtoform.FuncionarioForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="tb_funcionario")
@Getter @Setter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String sobrenome;

    private String email;
    private Integer idade;
    private Boolean ativo;

    public Funcionario() {

    }

    public Funcionario(String nome, String sobrenome, String email, Integer idade, Boolean ativo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.idade = idade;
        this.ativo = ativo;
    }

    public Funcionario (FuncionarioForm funcionarioForm) {
       this.nome = funcionarioForm.getNome();
       this.sobrenome = funcionarioForm.getSobrenome();
       this.setEmail(funcionarioForm.getEmail());
       this.setIdade(funcionarioForm.getIdade());
       this.ativo = false;
    }


}
