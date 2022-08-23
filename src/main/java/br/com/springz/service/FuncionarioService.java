package br.com.springz.service;

import br.com.springz.config.exceptions.ExceptionIdNaoEcontrado;
import br.com.springz.controller.FuncionarioController;
import br.com.springz.dtoform.FuncionarioDto;
import br.com.springz.dtoform.FuncionarioDtoDetalhado;
import br.com.springz.dtoform.FuncionarioForm;
import br.com.springz.dtoform.FuncionarioFormAtualizacao;
import br.com.springz.model.Funcionario;
import br.com.springz.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@Service
@ResponseBody
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public List<FuncionarioDto> listarTodos() {
        return converterListaFuncionarioDto(funcionarioRepository.findAll());
    }

    public FuncionarioDtoDetalhado acharPorId(Long id)  {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() ->
                new ExceptionIdNaoEcontrado("Id não encontrado: " + id,
                        "O Id informado não existe no banco de dados ")
        );
        try {
            return new FuncionarioDtoDetalhado(funcionario)
                    .add(linkTo(methodOn(FuncionarioController.class).listarFuncionarios()).
                            withRel("Lista de Funcionarios"));
        } catch (Exception e) {
            throw new ExceptionIdNaoEcontrado("Id não encontrado: " + id,
                    "O Id informado não existe no banco de dados ");
        }
    }

//    public ResponseEntity<FuncionarioDtoDetalhado> detalharFuncionario(Long id) throws Exception {
//        if(funcionarioExiste(id)) {
//            Funcionario f = funcionarioRepository.findById(id).orElseThrow(() -> new Exception());
//            FuncionarioDtoDetalhado funcionarioDtoDetalhado = new FuncionarioDtoDetalhado(f);
//            funcionarioDtoDetalhado.add(linkTo(methodOn(FuncionarioController.class).listarFuncionarios()).withRel("Lista de Funcionarios"));
//            return ResponseEntity.ok(funcionarioDtoDetalhado);
//        }
//        return ResponseEntity.notFound().build();
//    }


//    public FuncionarioDtoDetalhado detalharFuncionarioPorNome(String nome) {
//        Funcionario funcionario = funcionarioRepository.findByNome(nome);
//        return new FuncionarioDtoDetalhado(funcionario);
//    }

    public Funcionario cadastrar(FuncionarioForm funcionarioForm) {
        return funcionarioRepository.save(new Funcionario(funcionarioForm));
    }

    public FuncionarioDto atualizar(Long id, FuncionarioFormAtualizacao funcionarioFormAtualizacao) {
        Funcionario fun = funcionarioRepository.findById(id).orElseThrow(() ->
                new ExceptionIdNaoEcontrado("Id não encontrado: " + id,
                        "O Id informado não existe no banco de dados "));

        Funcionario funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.setNome(funcionarioFormAtualizacao.getNome());
        funcionario.setSobrenome(funcionarioFormAtualizacao.getSobrenome());

        return new FuncionarioDto(funcionario);

    }


    public ResponseEntity<?> delete(Long id) {
        funcionarioRepository.findById(id).orElseThrow(() -> new ExceptionIdNaoEcontrado("Id não encontrado: " + id,
                "O Id informado não existe no banco de dados "));
        funcionarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /*
    Utilidades
     */

    private List<FuncionarioDto> converterListaFuncionarioDto(List<Funcionario> funcionarios)  {
        List<FuncionarioDto> listaFuncionarioDto = new ArrayList<>();
        for (int i = 0; i < funcionarios.size(); i++) {
            listaFuncionarioDto.add(new FuncionarioDto(funcionarios.get(i)));
            long id = listaFuncionarioDto.get(i).getId();
            try {
                listaFuncionarioDto.get(i).add(linkTo(methodOn(FuncionarioController.class).funcionarioPorId(id)).withSelfRel());
            } catch (Exception e) {
                throw new ExceptionIdNaoEcontrado("Id não encontrado: " + id,
                        "O Id informado não existe no banco de dados ");
            }
        }
        return listaFuncionarioDto;
    }


}
