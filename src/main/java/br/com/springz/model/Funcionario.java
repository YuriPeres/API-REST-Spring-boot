package br.com.springz.model;

import br.com.springz.dtoform.FuncionarioForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_funcionario_telefone",
            joinColumns        = @JoinColumn(name = "id_funcionario", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_telefone", referencedColumnName = "id")
    )
    private List<Telefone> telefones;

    public Funcionario(String nome, String sobrenome, String email, Integer idade, Boolean ativo, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.idade = idade;
        this.ativo = ativo;
        this.endereco = endereco;
    }

    public Funcionario(String nome, String sobrenome, String email, Integer idade, Boolean ativo, Endereco endereco,
                       List telefones) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.idade = idade;
        this.ativo = ativo;
        this.endereco = endereco;
        setTelefones(telefones);
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
