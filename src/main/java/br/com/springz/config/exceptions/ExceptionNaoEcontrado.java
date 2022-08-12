package br.com.springz.config.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ExceptionNaoEcontrado extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String descricaoMsgDeErro;

    public ExceptionNaoEcontrado(String mensagemDeErro) {
        super(mensagemDeErro);
    }

    public ExceptionNaoEcontrado(String mensagemDeErro, String descricaoMsgDeErro) {
        super(mensagemDeErro);
        this.descricaoMsgDeErro = descricaoMsgDeErro;
    }


}
