package br.com.springz.dtoform;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data @AllArgsConstructor @NoArgsConstructor
public class FuncionarioFormAtualizacao {

    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
//    @NotBlank @NotEmpty
//    private String email;
//    @NotBlank @NotEmpty
//    private String idade;


//    public FuncionarioFormAtualizacao(Funcionario funcionario) {
//        this.nome = funcionario.getNome();
//        this.sobrenome = funcionario.getSobrenome();
////        this.email = funcionario.getEmail();
////        this.idade = funcionario.getIdade().toString();
//    }

}
