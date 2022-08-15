package br.com.springz.config.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ExceptionIdNaoEcontrado extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String descricaoMsgDeErro;

    public ExceptionIdNaoEcontrado(String mensagemDeErro) {
        super(mensagemDeErro);
    }

    public ExceptionIdNaoEcontrado(String mensagemDeErro, String descricaoMsgDeErro) {
        super(mensagemDeErro);
        this.descricaoMsgDeErro = descricaoMsgDeErro;
    }


}
