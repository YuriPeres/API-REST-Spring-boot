package br.com.springz.dtoform;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("endereco")
    private EnderecoForm enderecoForm;


    public FuncionarioForm() {
    }



}
