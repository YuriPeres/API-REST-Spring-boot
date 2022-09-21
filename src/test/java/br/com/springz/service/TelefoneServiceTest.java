package br.com.springz.service;

import br.com.springz.config.exceptions.ExceptionIdNaoEcontrado;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.springz.utils.FuncionarioStub.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)//liga o spring com junit
@SpringBootTest
public class TelefoneServiceTest {


    public static final BigInteger NUMERO_VALIDO = BigInteger.valueOf(988776644);
    public static final BigInteger NUMERO_VALIDO_DOIS = BigInteger.valueOf(123456789);
    private static final List<Funcionario> LISTA_FUNCIONARIO = List.of(FUNCIONARIO_VALIDO);
    public static final BigInteger NUMERO_VALIDO_TRES = BigInteger.valueOf(888888888);
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
        List<BigInteger> listaNumeros = new ArrayList();
        listaNumeros.add(NUMERO_VALIDO);
        given(telefoneRepository.findAll()).willReturn(List.of
                (new Telefone(ID_VALIDO, listaNumeros, 0)));

        List<TelefoneDto> resposta = telefoneService.listarTodosTelefones();


        assertNotNull(resposta);
        assertEquals(1, resposta.size());
        assertEquals(TelefoneDto.class, resposta.get(INDEX).getClass());
        assertEquals(ID_VALIDO, resposta.get(INDEX).getId());
        assertEquals(NUMERO_VALIDO, resposta.get(INDEX).getNumero());
}


    @Test
    @DisplayName("Quando cadastrar retorna sucesso.")
    public void cadastrarTelefoneEmFuncionarioValido() {
        Funcionario funcionarioTeste = FUNCIONARIO_VALIDO;
        Telefone telefoneTeste = new Telefone();
        telefoneTeste.setNumero(NUMERO_VALIDO);
        telefoneTeste.setId(1L);
        funcionarioTeste.setTelefones(new ArrayList<>(List.of(telefoneTeste)));

        given(funcionarioRepository.findById(any())).willReturn(Optional.of(FUNCIONARIO_VALIDO));
        given(telefoneRepository.save(any())).willReturn(telefoneTeste);
        given(funcionarioRepository.save(any())).willReturn(funcionarioTeste);


        TelefoneDto dto = new TelefoneDto(null, List.of(NUMERO_VALIDO));
        Funcionario resposta = telefoneService.cadastrarTelefoneEmFuncionario(dto);

        assertNotNull(resposta);
        assertEquals(Funcionario.class, resposta.getClass());
        assertEquals(ID_VALIDO, resposta.getTelefones().get(0).getId());
        assertEquals(NUMERO_VALIDO, resposta.getTelefones().get(0).getNumero());
        assertEquals(funcionarioTeste, resposta);
    }



    @Test
    @DisplayName("Verifica se o telefoneService.cadastrar() lida corretamente com exception de funcionario")
    public void cadastrarTelefoneException() {
        given(funcionarioRepository.findById(anyLong()))
                .willThrow(new ExceptionIdNaoEcontrado(OBJETO_NAO_ENCONTRADO));

        expectedException.expectMessage(OBJETO_NAO_ENCONTRADO);
        funcionarioService.atualizar(anyLong(), new FuncionarioFormAtualizacao());

    }

    @Test
    @DisplayName("Verifica se o telefoneService.deletarTelefoneApenasDoFuncionario() realmente chama o delete")
    public void deletarTelefoneApenasDoFuncionario(){
        given(funcionarioRepository.findById(anyLong())).willReturn(Optional.of(FUNCIONARIO_VALIDO));
        given(telefoneRepository.findById(anyLong())).willReturn(Optional.of(new Telefone()));
        doNothing().when(telefoneRepository).deletarTelefoneApenasDoFuncionario(anyLong(),anyLong());
        telefoneService.deletarTelefoneApenasDoFuncionario(0L,0L);
        verify(telefoneRepository, times(1)).
                deletarTelefoneApenasDoFuncionario(anyLong(),anyLong());
    }

    @Test
    @DisplayName("Delete quando não acha resposta")
    public void deletarTelefoneApenasDoFuncionarioComException() {
        for(int i=0; i<2; i++){
            if(i==0){
                given(funcionarioRepository.findById(anyLong()))
                        .willThrow(new ExceptionIdNaoEcontrado(OBJETO_NAO_ENCONTRADO));
            } else{
                given(telefoneRepository.findById(anyLong()))
                        .willThrow(new ExceptionIdNaoEcontrado(OBJETO_NAO_ENCONTRADO));
            }
            expectedException.expectMessage(OBJETO_NAO_ENCONTRADO);
            telefoneService.deletarTelefoneApenasDoFuncionario(1L, 1L);
        }
    }

//    @Test
//    @DisplayName("Delete dando sucesso deve rodar uma vez.")
//    public void deletarTelefoneComSucesso() {
//        given(telefoneRepository.findById(anyLong())).willReturn(Optional.of(new Telefone()));
//        doNothing().when(telefoneRepository).deleteById(anyLong());
//        telefoneService.deletarTelefone(ID_VALIDO);
//        verify(telefoneRepository, times(1)).deleteById(anyLong());
//    }

    @Test
    @DisplayName("Delete dando sucesso deve rodar uma vez.")
    public void deletarTelefoneComVinculoComSucesso() {
        given(telefoneRepository.findById(anyLong())).willReturn(Optional.of(new Telefone()));
        given(telefoneRepository.telefoneNaoTemVinculoAlgum(anyLong()))
                .willReturn(1);
        doNothing().when(telefoneRepository).deleteById(anyLong());
        doNothing().when(telefoneRepository).deletarTodosVinculosTelefone(anyLong());
        telefoneService.deletarTelefone(ID_VALIDO);
        verify(telefoneRepository, times(1)).deleteById(anyLong());
        verify(telefoneRepository, times(1)).deletarTodosVinculosTelefone(1l);
    }

    @Test
    @DisplayName("Delete quando não acha resposta")
    public void deletarTelefoneComException() {
        given(telefoneRepository.findById(anyLong()))
                .willThrow(new ExceptionIdNaoEcontrado(OBJETO_NAO_ENCONTRADO));

        expectedException.expectMessage(OBJETO_NAO_ENCONTRADO);
        telefoneService.deletarTelefone(anyLong());

    }


    @Test
    @DisplayName("Verifica:" +
            "Novo Telefone, mas não vinculado a funcionario" +
            "Telefone já existente em funcionario" +
            "telefone que sera deletado de funcionario")
    public void atualizarTelefoneComSucesso(){
        Funcionario funcionario = FUNCIONARIO_VALIDO;
        funcionario.setTelefones(new ArrayList<>());
        //telefoneDois verifica se numero já salvo em funcionário funciona
        Telefone telefoneDois = new Telefone();
        telefoneDois.setId(2l);
        telefoneDois.setNumero(NUMERO_VALIDO_DOIS);
        funcionario.getTelefones().add(telefoneDois);
        //telefoneTres verifica se deleta telefone que não vem no atualizar
        Telefone telefoneTres = new Telefone();
        telefoneTres.setId(3l);
        telefoneTres.setNumero(NUMERO_VALIDO_TRES);
        funcionario.getTelefones().add(telefoneTres);
        given(funcionarioRepository.findById(any())).willReturn(Optional.of(funcionario));


        //telefoneUm verifica se número salvo no banco, mas não em funcionário funciona
        Telefone telefoneUm = new Telefone();
        telefoneUm.setNumero(NUMERO_VALIDO);
        telefoneUm.setId(1L);
        given(telefoneRepository.findByNumero(NUMERO_VALIDO)).willReturn(telefoneUm);

        given(telefoneRepository.telefoneExisteEmFuncionario(ID_VALIDO,1L)).willReturn(false);
        doNothing().when(funcionarioRepository).ligarTelefoneExistenteEmFuncionario(anyLong(), anyLong());

        given(telefoneRepository.findByNumero(NUMERO_VALIDO_DOIS)).willReturn(telefoneDois);
        given(telefoneRepository.telefoneExisteEmFuncionario(ID_VALIDO,2L)).willReturn(true);

        TelefoneDto dto = new TelefoneDto();
        dto.setNumeros(new ArrayList<>());
        dto.getNumeros().add(NUMERO_VALIDO);
        dto.getNumeros().add(NUMERO_VALIDO_DOIS);
        telefoneService.atualizarTelefone(dto);

//        given(telefoneRepository.findByNumero(NUMERO_VALIDO_TRES)).willReturn(telefoneTres);
        doNothing().when(telefoneRepository).deletarTelefoneApenasDoFuncionario(ID_VALIDO,3l);
        given(telefoneRepository.telefoneNaoTemVinculoAlgum(any())).willReturn(1);

        verify(funcionarioRepository, times(1))
                .ligarTelefoneExistenteEmFuncionario(ID_VALIDO,1l);

        verify(telefoneRepository, times(1))
                .telefoneExisteEmFuncionario(ID_VALIDO,2l);

        verify(telefoneRepository, times(1))
                .deletarTelefoneApenasDoFuncionario(ID_VALIDO,3l);
    }

    @Test
    @DisplayName("Verifica: telefone completamente novo (nao contém em tb_telefone)")
    public void atualizarTelefoneCompletamenteNovoComSucesso(){
        Funcionario funcionario = FUNCIONARIO_VALIDO;
        funcionario.setTelefones(new ArrayList<>());
        given(funcionarioRepository.findById(any())).willReturn(Optional.of(funcionario));

        Telefone telefone = new Telefone();
        telefone.setNumero(NUMERO_VALIDO);
        given(telefoneRepository.findByNumero(NUMERO_VALIDO)).willReturn(null);

        given(telefoneRepository.save(any())).willReturn(telefone);

        TelefoneDto dto = new TelefoneDto();
        dto.setNumeros(new ArrayList<>(Arrays.asList(NUMERO_VALIDO)));

        Funcionario funResposta = new Funcionario();
        funResposta.setTelefones(new ArrayList<>(Arrays.asList(telefone)));
        given(telefoneRepository.save(any())).willReturn(telefone);
        //given(funcionario.getTelefones().add(telefoneRepository.save(telefone))).willReturn(true);

        telefoneService.atualizarTelefone(dto);
        //verify(funcionario.getTelefones()).add(telefoneRepository.save(telefone));

        verify(telefoneRepository, times(1)).save(any());
       // verify(funcionarioRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("")
    public void atualizarComExceptionEmAcharFuncionario(){
        given(funcionarioRepository.findById(anyLong()))
                .willThrow(new ExceptionIdNaoEcontrado(OBJETO_NAO_ENCONTRADO));

        expectedException.expectMessage(OBJETO_NAO_ENCONTRADO);
        TelefoneDto dto = new TelefoneDto();
        dto.setIdFuncionario(1l);
        telefoneService.atualizarTelefone(dto);
    }

    @Test
    @DisplayName("") //Esse não resolvi
    public void atualizarComExceptionEmAcharPeloNumero(){
        Funcionario funcionario = FUNCIONARIO_VALIDO;
        funcionario.setTelefones(new ArrayList<>());
        Telefone telefone = new Telefone();
        telefone.setNumero(NUMERO_VALIDO);
        telefone.setId(1l);
        funcionario.getTelefones().add(telefone);

        given(funcionarioRepository.findById(anyLong())).willReturn(Optional.of(funcionario));
        given(telefoneRepository.findByNumero(any()))
                .willThrow(new ExceptionIdNaoEcontrado(OBJETO_NAO_ENCONTRADO));

        expectedException.expectMessage(OBJETO_NAO_ENCONTRADO);
        TelefoneDto dto = new TelefoneDto();
        dto.setIdFuncionario(1l);
        dto.setNumeros(Arrays.asList(NUMERO_VALIDO));
        telefoneService.atualizarTelefone(dto);
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