package br.com.springz.utils;

import br.com.springz.model.Endereco;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class EnderecoStub {

    public static final String CEP_VALIDO;
    public static final String LOGRADOURO_VALIDO;
    public static final String COMPLEMENTO_VALIDO;
    public static final String BAIRRO_VALIDO;
    public static final String LOCALIDADE_VALIDA;
    public static final String UF_VALIDA;

    public static final Endereco ENDERECO_VALIDO;

    static {
        CEP_VALIDO = "11111-111";
        LOGRADOURO_VALIDO = "exemplo de logradouo valido";
        COMPLEMENTO_VALIDO = "exemplo de complemento valido";
        BAIRRO_VALIDO = "exemplo de bairro valido";
        LOCALIDADE_VALIDA = "exemplo de localidade valida";
        UF_VALIDA = "exemplo de UF valida";

        ENDERECO_VALIDO = new Endereco(
                CEP_VALIDO, LOGRADOURO_VALIDO,
                COMPLEMENTO_VALIDO, BAIRRO_VALIDO,
                LOCALIDADE_VALIDA, UF_VALIDA
        );
    }

}
