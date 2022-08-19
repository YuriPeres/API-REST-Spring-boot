package br.com.springz.dtoform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class FuncionarioForm {

    @NotBlank(message = "Nome informado de forma incorreta.") @Length(min = 1, max = 100)
    private String nome;

    @NotBlank(message = "Sobrenome informado de forma incorreta.") @Length(min = 1, max = 100)
    private String sobrenome;

    @NotBlank(message = "Email informado de forma incorreta.") @Length(min = 1, max = 100)
    private String email;

    @Min(value = 16, message = "Idade incorreta.") @Max(value = 100, message = "Já deu né? Aposenta.")
    @NotNull(message = "Nome informado de forma incorreta.")
    private Integer idade;

    @JsonProperty("endereco")
    private EnderecoForm enderecoForm;

}
