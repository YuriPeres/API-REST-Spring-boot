package br.com.springz.dtoform;

import br.com.springz.model.Funcionario;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class FuncionarioForm {

    @NotBlank @NotEmpty
    private String nome;
    @NotBlank @NotEmpty
    private String sobrenome;
    @NotBlank @NotEmpty
    private String email;
    @NotBlank @NotEmpty
    private String idade;


    public FuncionarioForm() {
    }

    public FuncionarioForm(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.sobrenome = funcionario.getSobrenome();
        this.email = funcionario.getEmail();
        this.idade = funcionario.getIdade().toString();
    }

    public static Funcionario converter(FuncionarioForm funcionarioForm) {
        return new Funcionario(
                funcionarioForm.nome, funcionarioForm.sobrenome,
                funcionarioForm.getEmail(),
                Integer.parseInt(funcionarioForm.getIdade()),
                false);
    }
}
