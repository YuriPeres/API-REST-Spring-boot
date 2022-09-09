package br.com.springz.service;

import br.com.springz.dtoform.*;
import br.com.springz.model.Funcionario;
import br.com.springz.model.Telefone;
import br.com.springz.repository.FuncionarioRepository;
import br.com.springz.repository.TelefoneRepository;
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

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static br.com.springz.utils.FuncionarioStub.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)//liga o spring com junit
@SpringBootTest
public class TelefoneServiceTest {


    public static final BigInteger NUMERO_VALIDO = BigInteger.valueOf(988776644);
    private static final List<Funcionario> LISTA_FUNCIONARIO = List.of(FUNCIONARIO_VALIDO);
    @InjectMocks
    private FuncionarioService funcionarioService;

    @InjectMocks
    private TelefoneService telefoneService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private TelefoneRepository telefoneRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    //private FuncionarioForm funcionarioForm = new FuncionarioForm(NOME_VALIDO,SOBRENOME_VALIDO,EMAIL_VALIDO,IDADE_VALIDA,EnderecoStub.ENDERECO_VALIDO);


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        funcionarioService = new FuncionarioService(funcionarioRepository);
        telefoneService = new TelefoneService(telefoneRepository, funcionarioRepository);
    }


    @Test
    @DisplayName("Verifica se o telefoneService.listarTodosTelefones() retorna uma Set<TelefoneDto> corretamente")
    public void listarTodosTelefonesValido() {
        given(telefoneRepository.findAll()).willReturn(List.of(new Telefone(ID_VALIDO, (List<BigInteger>) NUMERO_VALIDO, 0)));

        List<TelefoneDto> resposta = telefoneService.listarTodosTelefones();


        assertNotNull(resposta);
        assertEquals(1, resposta.size());
        assertEquals(TelefoneDto.class, resposta.get(INDEX).getClass());
        assertEquals(ID_VALIDO, resposta.get(INDEX).getId());
        assertEquals(NUMERO_VALIDO, resposta.get(INDEX).getNumeros());
}

    @Test
    @DisplayName("Quando cadastrar retorna sucesso.")
    public void cadastrarTelefoneEmFuncionarioValido() {
        given(telefoneRepository.save(any())).willReturn(new Telefone(ID_VALIDO, (List<BigInteger>) NUMERO_VALIDO, 0));
        given(funcionarioRepository.findById(any())).willReturn(Optional.of(FUNCIONARIO_VALIDO));
        given(funcionarioRepository.save(any())).willReturn(FUNCIONARIO_VALIDO);


        TelefoneDto dto = new TelefoneDto(null, List.of(NUMERO_VALIDO));
        Funcionario resposta = telefoneService.cadastrarTelefoneEmFuncionario(dto);

        assertNotNull(resposta);
        assertEquals(Funcionario.class, resposta.getClass());
        assertEquals(ID_VALIDO, resposta.getTelefones().get(0).getId());
        assertEquals(NUMERO_VALIDO, resposta.getTelefones().get(0).getNumero());
        Funcionario fun = FUNCIONARIO_VALIDO;
        fun.getTelefones().add(new Telefone(ID_VALIDO, (List<BigInteger>) NUMERO_VALIDO, 0));
        assertEquals(fun, resposta);
    }
//
//    @Test
//    @DisplayName("Quando atualizar retorna o form esperado")
//    public void atualizarComSucesso() {
//
//        given(funcionarioRepository.findById(anyLong())).willReturn(Optional.of((FUNCIONARIO_VALIDO)));
//        given(funcionarioRepository.getReferenceById(anyLong())).willReturn((FUNCIONARIO_VALIDO));
//
//        FuncionarioFormAtualizacao form = new FuncionarioFormAtualizacao(NOME_VALIDO, SOBRENOME_VALIDO);
//
//        FuncionarioDto resposta = funcionarioService.atualizar(anyLong(),form);
//
//        assertNotNull(resposta);
//        assertEquals(FuncionarioDto.class, resposta.getClass());
//        assertEquals(NOME_VALIDO, resposta.getNome());
//        assertEquals(SOBRENOME_VALIDO, resposta.getSobrenome());
//
//    }
//
//    @Test
//    @DisplayName("Verifica se o funcionarioService.atualizar() lida corretamente com exception")
//    public void atualizarException() {
//        given(funcionarioRepository.findById(anyLong()))
//                .willThrow(new ExceptionIdNaoEcontrado(OBJETO_NAO_ENCONTRADO));
//
//        expectedException.expectMessage(OBJETO_NAO_ENCONTRADO);
//        funcionarioService.atualizar(anyLong(), new FuncionarioFormAtualizacao());
//
//    }
//
//    @Test
//    @DisplayName("Delete dando sucesso deve rodar uma vez.")
//    public void deleteComSucesso() {
//        given(funcionarioRepository.findById(anyLong())).willReturn(Optional.of(FUNCIONARIO_VALIDO));
//        doNothing().when(funcionarioRepository).deleteById(anyLong());
//        funcionarioService.delete(ID_VALIDO);
//        verify(funcionarioRepository, times(1)).deleteById(anyLong());
//    }
//
//    @Test
//    @DisplayName("Delete quando não acha resposta")
//    public void deleteComException() {
//        given(funcionarioRepository.findById(anyLong()))
//                .willThrow(new ExceptionIdNaoEcontrado(OBJETO_NAO_ENCONTRADO));
//
//        expectedException.expectMessage(OBJETO_NAO_ENCONTRADO);
//        funcionarioService.delete(anyLong());
//
//    }
}