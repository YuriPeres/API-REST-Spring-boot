package br.com.springz.service;

import br.com.springz.config.exceptions.ExceptionIdNaoEcontrado;
import br.com.springz.controller.FuncionarioController;
import br.com.springz.dtoform.EnderecoForm;
import br.com.springz.dtoform.FuncionarioDto;
import br.com.springz.dtoform.FuncionarioDtoDetalhado;
import br.com.springz.dtoform.FuncionarioForm;
import br.com.springz.model.Funcionario;
import br.com.springz.repository.FuncionarioRepository;
import br.com.springz.utils.EnderecoStub;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static br.com.springz.utils.FuncionarioStub.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)//liga o spring com junit
@SpringBootTest
public class FuncionarioServiceTest {


    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;
    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    //private FuncionarioForm funcionarioForm = new FuncionarioForm(NOME_VALIDO,SOBRENOME_VALIDO,EMAIL_VALIDO,IDADE_VALIDA,EnderecoStub.ENDERECO_VALIDO);


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        funcionarioService = new FuncionarioService(funcionarioRepository);
    }

    @Test
    @DisplayName("Verifica se o funcionarioService.listar() retorna uma List<FuncionarioDto> corretamente")
    public void listarTodosValido() throws Exception {
        given(funcionarioRepository.findAll()).willReturn(List.of(FUNCIONARIO_VALIDO));

        List<FuncionarioDto> resposta = funcionarioService.listarTodos();


        assertNotNull(resposta);
        assertEquals(1, resposta.size());
        assertEquals(FuncionarioDto.class, resposta.get(INDEX).getClass());
        assertEquals(ID_VALIDO, resposta.get(INDEX).getId());
        assertEquals(NOME_VALIDO, resposta.get(INDEX).getNome());
        assertEquals(SOBRENOME_VALIDO, resposta.get(INDEX).getSobrenome());


    }

    @Test
    @DisplayName("Verifica se o funcionarioService.acharPorId() retorna um FuncionarioDtoDetalhado corretamente")
    public void acharPorIdValido() throws Exception {
        Funcionario funcionarioTest = new Funcionario(
                ID_VALIDO, NOME_VALIDO, SOBRENOME_VALIDO, EMAIL_VALIDO,
                IDADE_VALIDA, ATIVO_VALIDO, EnderecoStub.ENDERECO_VALIDO
        );
        given(funcionarioRepository.findById(anyLong())).willReturn(Optional.of(FUNCIONARIO_VALIDO));

        FuncionarioDtoDetalhado resposta = funcionarioService.acharPorId(ID_VALIDO);

        assertNotNull(resposta);
        assertEquals(FuncionarioDtoDetalhado.class, resposta.getClass());
        assertEquals(ID_VALIDO, resposta.getId());
        assertEquals(NOME_VALIDO, resposta.getNome());
        assertEquals(SOBRENOME_VALIDO, resposta.getSobrenome());
        assertEquals(EMAIL_VALIDO, resposta.getEmail());
        assertEquals(IDADE_VALIDA, resposta.getIdade());
        assertEquals(ATIVO_VALIDO, resposta.isAtivo());
        assertEquals(EnderecoStub.ENDERECO_VALIDO, resposta.getEndereco());

        //Adicionando o link do hateoas para conferir o retorno do service
        assertEquals(new FuncionarioDtoDetalhado(funcionarioTest)
                .add(linkTo(methodOn(FuncionarioController.class).listarFuncionarios()).
                withRel("Lista de Funcionarios")),
                    resposta);

    }

    @Test
    @DisplayName("Verifica se o funcionarioService.acharPorId() lida corretamente com exception")
    public void acharPorIdComException() throws Exception {
        given(funcionarioRepository.findById(anyLong()))
                .willThrow(new ExceptionIdNaoEcontrado(OBJETO_NAO_ENCONTRADO));

        expectedException.expectMessage(OBJETO_NAO_ENCONTRADO);
        funcionarioService.acharPorId(ID_VALIDO);
//        try{
//            funcionarioService.acharPorId(ID_VALIDO);
//        } catch (Exception ex){
//            assertEquals(ExceptionIdNaoEcontrado.class, ex.getClass());
//            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
//        }

    }


    @Test
    @DisplayName("Quando cadastrar retorna sucesso.")
    public void cadastrarValido() {
        given(funcionarioRepository.save(any())).willReturn(FUNCIONARIO_VALIDO);
        EnderecoForm enderecoform = new EnderecoForm(EnderecoStub.LOCALIDADE_VALIDA, EnderecoStub.LOGRADOURO_VALIDO,
                EnderecoStub.CEP_VALIDO, EnderecoStub.COMPLEMENTO_VALIDO, EnderecoStub.BAIRRO_VALIDO, EnderecoStub.UF_VALIDA);

        FuncionarioForm form = new FuncionarioForm(NOME_VALIDO, SOBRENOME_VALIDO, EMAIL_VALIDO, IDADE_VALIDA, enderecoform);
        Funcionario resposta = funcionarioService.cadastrar(form);

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
    public void atualizar() {
    }

    @Test
    public void delete() {
    }
}