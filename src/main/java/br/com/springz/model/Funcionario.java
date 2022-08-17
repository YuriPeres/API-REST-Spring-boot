package br.com.springz.model;

import br.com.springz.dtoform.FuncionarioForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tb_funcionario")
@Getter @Setter
public class Funcionario {

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String email;
    private Integer idade;
    private Boolean ativo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;


    public Funcionario() {

    }

    public Funcionario(String nome, String sobrenome, String email, Integer idade, Boolean ativo, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.idade = idade;
        this.ativo = ativo;
        this.endereco = endereco;
    }

    public Funcionario(Long id, String nome, String sobrenome, String email, Integer idade, Boolean ativo, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.idade = idade;
        this.ativo = ativo;
        this.endereco = endereco;
    }

    public Funcionario (FuncionarioForm funcionarioForm) {
       this.nome = funcionarioForm.getNome();
       this.sobrenome = funcionarioForm.getSobrenome();
       this.setEmail(funcionarioForm.getEmail());
       this.setIdade(funcionarioForm.getIdade());
       this.ativo = false;
       this.endereco = new Endereco(funcionarioForm.getEnderecoForm());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(sobrenome, that.sobrenome) && Objects.equals(email, that.email) && Objects.equals(idade, that.idade) && Objects.equals(ativo, that.ativo) && Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome, email, idade, ativo, endereco);
    }
}
