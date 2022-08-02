package br.com.springz.dtoform;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class FuncionarioFormAtualizacao {

    @NotBlank
    @NotEmpty
    private String nome;
    @NotBlank @NotEmpty
    private String sobrenome;
//    @NotBlank @NotEmpty
//    private String email;
//    @NotBlank @NotEmpty
//    private String idade;


    public FuncionarioFormAtualizacao() {
    }

//    public FuncionarioFormAtualizacao(Funcionario funcionario) {
//        this.nome = funcionario.getNome();
//        this.sobrenome = funcionario.getSobrenome();
////        this.email = funcionario.getEmail();
////        this.idade = funcionario.getIdade().toString();
//    }

}
