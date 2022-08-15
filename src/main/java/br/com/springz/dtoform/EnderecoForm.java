package br.com.springz.dtoform;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Locale;


@Getter @Setter
public class EnderecoForm {



    @NotBlank @NotEmpty
    private String localidade;
    @NotBlank @NotEmpty
    private String logradouro;
    @NotBlank @NotEmpty
    private String cep;
    @NotBlank @NotEmpty
    private String complemento;
    @NotBlank @NotEmpty
    private String bairro;
    @NotBlank @NotEmpty @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "##")
    private String uf;


    public EnderecoForm() {
    }

}
