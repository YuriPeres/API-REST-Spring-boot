package br.com.springz.controller;

import br.com.springz.config.exceptions.ExceptionIdNaoEcontrado;
import br.com.springz.dtoform.*;
import br.com.springz.model.Funcionario;
import br.com.springz.service.FuncionarioService;
import br.com.springz.service.TelefoneService;
import br.com.springz.utils.EnderecoStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static br.com.springz.utils.FuncionarioStub.*;
import static br.com.springz.utils.FuncionarioStub.SOBRENOME_VALIDO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootTest @RunWith(MockitoJUnitRunner.class)
class FuncionarioControllerTest {

    @InjectMocks
    private FuncionarioController controller;

    @Mock
    private FuncionarioService service;

    @Mock
    private TelefoneService telService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new FuncionarioController(service, telService);
    }

    @Test
    void listarFuncionarios() {
        given(service.listarTodos()).willReturn(List.of(new FuncionarioDto(FUNCIONARIO_VALIDO)));

        List<FuncionarioDto> resposta = controller.listarFuncionarios();

        assertEquals(1, resposta.size());
        assertEquals(FuncionarioDto.class, resposta.get(INDEX).getClass());
        assertEquals(ID_VALIDO, resposta.get(INDEX).getId());
        assertEquals(NOME_VALIDO, resposta.get(INDEX).getNome());
        assertEquals(SOBRENOME_VALIDO, resposta.get(INDEX).getSobrenome());
    }

    @Test
    void funcionarioPorId() {
        Funcionario funcionarioTest = new Funcionario(
                ID_VALIDO, NOME_VALIDO, SOBRENOME_VALIDO, EMAIL_VALIDO,
                IDADE_VALIDA, ATIVO_VALIDO, EnderecoStub.ENDERECO_VALIDO
        );

        given(controller.funcionarioPorId(anyLong())).willReturn(new FuncionarioDtoDetalhado(FUNCIONARIO_VALIDO));

        FuncionarioDtoDetalhado resposta = controller.funcionarioPorId(ID_VALIDO);

        assertEquals(FuncionarioDtoDetalhado.class, resposta.getClass());
        assertEquals(ID_VALIDO, resposta.getId());
        assertEquals(NOME_VALIDO, resposta.getNome());
        assertEquals(SOBRENOME_VALIDO, resposta.getSobrenome());
        assertEquals(EMAIL_VALIDO, resposta.getEmail());
        assertEquals(IDADE_VALIDA, resposta.getIdade());
        assertEquals(ATIVO_VALIDO, resposta.isAtivo());
        assertEquals(EnderecoStub.ENDERECO_VALIDO, resposta.getEndereco());
        assertEquals(new FuncionarioDtoDetalhado(funcionarioTest), resposta);

    }

    @Test
    void cadastrarFuncionario() {
        given(controller.cadastrarFuncionario(any())).willReturn(FUNCIONARIO_VALIDO);
        EnderecoForm enderecoform = new EnderecoForm(EnderecoStub.LOCALIDADE_VALIDA, EnderecoStub.LOGRADOURO_VALIDO,
                EnderecoStub.CEP_VALIDO, EnderecoStub.COMPLEMENTO_VALIDO, EnderecoStub.BAIRRO_VALIDO, EnderecoStub.UF_VALIDA);

        FuncionarioForm form = new FuncionarioForm(NOME_VALIDO, SOBRENOME_VALIDO, EMAIL_VALIDO, IDADE_VALIDA, enderecoform);
        Funcionario resposta = controller.cadastrarFuncionario(form);

        assertNotNull(resposta);
        assertEquals(Funcionario.class, resposta.getClass());
        assertEquals(ID_VALIDO, resposta.getId());
        assertEquals(NOME_VALIDO, resposta.getNome());
        assertEquals(SOBRENOME_VALIDO, resposta.getSobrenome());
        assertEquals(EMAIL_VALIDO, resposta.getEmail());
        assertEquals(IDADE_VALIDA, resposta.getIdade());
        assertEquals(ATIVO_VALIDO, resposta.getAtivo());
        assertEquals(EnderecoStub.ENDERECO_VALIDO, resposta.getEndereco());


    }

    @Test
    void atualizarFuncionario() {
        given(controller.atualizarFuncionario(anyLong(), any())).willReturn(new FuncionarioDto(anyLong(), NOME_VALIDO, SOBRENOME_VALIDO));

        FuncionarioFormAtualizacao form = new FuncionarioFormAtualizacao(NOME_VALIDO, SOBRENOME_VALIDO);

        FuncionarioDto resposta = controller.atualizarFuncionario(anyLong(),form);

        assertEquals(FuncionarioDto.class, resposta.getClass());
        assertEquals(NOME_VALIDO, resposta.getNome());
        assertEquals(SOBRENOME_VALIDO, resposta.getSobrenome());
    }

    @Test
    void deletarFuncionario() {
        given(service.delete(anyLong())).willReturn(ResponseEntity.ok().build());
        ResponseEntity<?> resposta = controller.deletarFuncionario(ID_VALIDO);
        assertEquals(resposta, ResponseEntity.ok().build());
        verify(service, times(1)).delete(anyLong());

    }
}