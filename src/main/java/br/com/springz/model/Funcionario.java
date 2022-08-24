package br.com.springz.model;

import br.com.springz.dtoform.FuncionarioForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name="tb_funcionario")
@Data @AllArgsConstructor @NoArgsConstructor
public class Funcionario {

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @Length(max = 100)
    private String nome;

    @Column(name = "sobrenome")
    @Length(max = 100)
    private String sobrenome;

    @Column(name = "email", unique = true)
    @Length(max = 100)
    private String email;
    @Column(name = "idade")
    private Integer idade;

    @Column(name = "ativo")
    private Boolean ativo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    @ManyToMany(mappedBy = "funcionarios")
    private Set<Telefone> telefones;

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

}
