package br.com.springz.dtoform;

import br.com.springz.model.Funcionario;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class EnderecoForm {



    //private Funcionario funcionario;

    private String localidade;
    private String logradouro;
    private String cep;
    private String complemento;
    private String bairro;
    private String uf;


    public EnderecoForm() {
    }

}
