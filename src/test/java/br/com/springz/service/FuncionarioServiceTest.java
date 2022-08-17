package br.com.springz.service;

import br.com.springz.controller.FuncionarioController;
import br.com.springz.dtoform.FuncionarioDtoDetalhado;
import br.com.springz.model.Endereco;
import br.com.springz.model.Funcionario;
import br.com.springz.repository.FuncionarioRepository;
import br.com.springz.utils.EnderecoStub;
import br.com.springz.utils.FuncionarioStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static br.com.springz.utils.FuncionarioStub.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)//liga o spring com junit
@SpringBootTest
public class FuncionarioServiceTest {


    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        funcionarioService = new FuncionarioService(funcionarioRepository);
    }

    @Test
    public void deveListar() {


    }

    @Test
    public void detalharFuncionarioValido() throws Exception {
        Funcionario funcionarioTest = new Funcionario(
                ID_VALIDO, NOME_VALIDO, SOBRENOME_VALIDO, EMAIL_VALIDO,
                IDADE_VALIDA, VERDADEIRO, EnderecoStub.ENDERECO_VALIDO
        );
        given(funcionarioRepository.findById(1L)).willReturn(Optional.of(FUNCIONARIO_VALIDO));

        assertEquals(new FuncionarioDtoDetalhado(funcionarioTest).add(linkTo(methodOn(FuncionarioController.class).listarFuncionarios()).
                withRel("Lista de Funcionarios")), funcionarioService.detalharFuncionario(1L));

    }


    @Test
    public void cadastrarValido() {

    }

    @Test
    public void atualizar() {
    }

    @Test
    public void delete() {
    }
}