package br.com.springz.dtoform;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class FuncionarioForm {

    @NotBlank @NotEmpty
    private String nome;
    @NotBlank @NotEmpty
    private String sobrenome;
    @NotBlank @NotEmpty
    private String email;
    @Min(value = 16, message = "Idade incorreta.")
    @NotNull
    private int idade;


    public FuncionarioForm() {
    }

//    public FuncionarioForm(Funcionario funcionario) {
//        this.nome = funcionario.getNome();
//        this.sobrenome = funcionario.getSobrenome();
//        this.email = funcionario.getEmail();
//        this.idade = funcionario.getIdade();
//    }

}
