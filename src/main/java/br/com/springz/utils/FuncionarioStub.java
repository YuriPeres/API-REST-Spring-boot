package br.com.springz.utils;

import br.com.springz.dtoform.FuncionarioDtoDetalhado;
import br.com.springz.model.Funcionario;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class FuncionarioStub {

    public static final Long ID_VALIDO;
    public static final String NOME_VALIDO;
    public static final String SOBRENOME_VALIDO;
    public static final String EMAIL_VALIDO;
    public static final Integer IDADE_VALIDA;

    public static  final Boolean VERDADEIRO;

    public static final Boolean FALSO;

    public static final Funcionario FUNCIONARIO_VALIDO;

    public static final FuncionarioDtoDetalhado FUNCIONARIO_DETALHADO_VALIDO;


    static {
        ID_VALIDO = 1L;
        NOME_VALIDO = "exemplo de nome";
        SOBRENOME_VALIDO = "ex de sobrenome";
        EMAIL_VALIDO = "exde@email.com";
        IDADE_VALIDA = 30;
        VERDADEIRO = true;
        FALSO = false;

        FUNCIONARIO_VALIDO = new Funcionario(
                ID_VALIDO, NOME_VALIDO, SOBRENOME_VALIDO, EMAIL_VALIDO,
                IDADE_VALIDA, VERDADEIRO, EnderecoStub.ENDERECO_VALIDO
        );

        FUNCIONARIO_DETALHADO_VALIDO = new FuncionarioDtoDetalhado(FUNCIONARIO_VALIDO);
    }

}
