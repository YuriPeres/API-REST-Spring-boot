package br.com.springz.utils;

import br.com.springz.dtoform.FuncionarioDtoDetalhado;
import br.com.springz.model.Funcionario;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class FuncionarioStub {

    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;

    public static final Long ID_VALIDO;
    public static final String NOME_VALIDO;
    public static final String SOBRENOME_VALIDO;
    public static final String EMAIL_VALIDO;
    public static final Integer IDADE_VALIDA;

    public static  final Boolean ATIVO_VALIDO;

    public static final Funcionario FUNCIONARIO_VALIDO;

    public static final FuncionarioDtoDetalhado FUNCIONARIO_DETALHADO_VALIDO;


    static {
        ID_VALIDO = 1L;
        NOME_VALIDO = "exemplo de nome";
        SOBRENOME_VALIDO = "ex de sobrenome";
        EMAIL_VALIDO = "exde@email.com";
        IDADE_VALIDA = 30;
        ATIVO_VALIDO = true;

        FUNCIONARIO_VALIDO = new Funcionario(
                ID_VALIDO, NOME_VALIDO, SOBRENOME_VALIDO, EMAIL_VALIDO,
                IDADE_VALIDA, ATIVO_VALIDO, EnderecoStub.ENDERECO_VALIDO
        );

        FUNCIONARIO_DETALHADO_VALIDO = new FuncionarioDtoDetalhado(FUNCIONARIO_VALIDO);
    }

}
