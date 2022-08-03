package br.com.springz.dtoform;

import br.com.springz.model.Funcionario;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Optional;

@Getter
public class FuncionarioDtoDetalhado extends RepresentationModel<FuncionarioDtoDetalhado> {

    private long id;
    private String nome;
    private String sobrenome;
    private String email;

    private Integer idade;

    private boolean ativo;

    public FuncionarioDtoDetalhado() {

    }

    public FuncionarioDtoDetalhado(Optional<Funcionario> funcionario) {
        this.id = funcionario.get().getId();
        this.nome = funcionario.get().getNome();
        this.sobrenome = funcionario.get().getSobrenome();
        this.email = funcionario.get().getEmail();
        this.idade = funcionario.get().getIdade();
        this.ativo = funcionario.get().getAtivo();
    }
//    public FuncionarioDtoDetalhado(Funcionario funcionario) {
//        this.id = funcionario.getId();
//        this.nome = funcionario.getNome();
//        this.sobrenome = funcionario.getSobrenome();
//        this.email = funcionario.getEmail();
//        this.idade = funcionario.getIdade();
//        this.ativo = funcionario.getAtivo();
//    }

}
