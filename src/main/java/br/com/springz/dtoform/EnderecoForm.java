package br.com.springz.dtoform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Data @AllArgsConstructor @NoArgsConstructor
public class EnderecoForm {



    @NotBlank(message = "Localidade informada de forma incorreta.") @Length(min = 3, max = 100)
    private String localidade;

    @NotBlank(message = "Logradouro informado de forma incorreta.") @Length(min = 3, max = 100)
    private String logradouro;

    @NotBlank(message = "CEP informado de forma incorreta.") @Length(min = 8, max = 8)
    private String cep;

    private String complemento;

    @NotBlank(message = "Bairro informado de forma incorreta.") @Length(min = 3, max = 100)
    private String bairro;

    @NotBlank(message = "UF informado de forma incorreta.") @Length(min = 2, max = 2)
    private String uf;


}
