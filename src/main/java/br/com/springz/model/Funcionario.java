package br.com.springz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
}
