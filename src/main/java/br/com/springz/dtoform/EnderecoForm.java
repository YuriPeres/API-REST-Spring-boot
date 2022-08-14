package br.com.springz.dtoform;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class EnderecoForm {



    //private Funcionario funcionario;
//    @NotBlank @NotEmpty
    private String localidade;
//    @NotBlank @NotEmpty
    private String logradouro;
//    @NotBlank @NotEmpty
    private String cep;
//    @NotBlank @NotEmpty
    private String complemento;
//    @NotBlank @NotEmpty
    private String bairro;
    //@NotBlank @NotEmpty @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "##")
    private String uf;


    public EnderecoForm() {
    }

}
