package br.com.springz.service;

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
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@Service
@ResponseBody
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public List<FuncionarioDto> listar(){
        return converterListaFuncionarioDto(funcionarioRepository.findAll());
    }

    public ResponseEntity<FuncionarioDtoDetalhado> detalharFuncionario(Long id) {
        if(funcionarioExiste(id)) {
            FuncionarioDtoDetalhado funcionarioDtoDetalhado = new FuncionarioDtoDetalhado(funcionarioRepository.findById(id));
            funcionarioDtoDetalhado.add(linkTo(methodOn(FuncionarioController.class).listarFuncionarios()).withRel("Lista de Funcionarios"));
            return ResponseEntity.ok(funcionarioDtoDetalhado);
        }
        return ResponseEntity.notFound().build();
    }
//    public FuncionarioDtoDetalhado detalharFuncionarioPorNome(String nome) {
//        Funcionario funcionario = funcionarioRepository.findByNome(nome);
//        return new FuncionarioDtoDetalhado(funcionario);
//    }

    public Funcionario cadastrar(FuncionarioForm funcionarioForm){
        return funcionarioRepository.save(new Funcionario(funcionarioForm));
    }

    public ResponseEntity<FuncionarioDto> atualizar(Long id, FuncionarioFormAtualizacao funcionarioFormAtualizacao){
        if(funcionarioExiste(id)){
            return ResponseEntity.ok(new FuncionarioDto(atualizarFuncionario(id, funcionarioFormAtualizacao)));
        }
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<?> delete(Long id) {
        if (funcionarioExiste(id)){
            funcionarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /*
    Utilidades
     */
    public boolean funcionarioExiste(Long id){
        return funcionarioRepository.findById(id).isPresent();
    }

    public List<FuncionarioDto> converterListaFuncionarioDto(List<Funcionario> funcionarios) {
        List<FuncionarioDto> listaFuncionarioDto = new ArrayList<>();
        for(int i=0; i < funcionarios.size();i++){
            listaFuncionarioDto.add(new FuncionarioDto(funcionarios.get(i)));
            long id = listaFuncionarioDto.get(i).getId();
            listaFuncionarioDto.get(i).add(linkTo(methodOn(FuncionarioController.class).detalhesDoFuncionario(id)).withSelfRel());
        }
        return listaFuncionarioDto;
    }

    public Funcionario atualizarFuncionario(Long id, FuncionarioFormAtualizacao funcionarioFormAtualizacao) {
        Funcionario funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.setNome (funcionarioFormAtualizacao.getNome());
        funcionario.setSobrenome(funcionarioFormAtualizacao.getSobrenome());
//        funcionario.setEmail(funcionarioFormAtualizacao.getEmail());
//        funcionario.setIdade(funcionarioFormAtualizacao.getIdade());
        return funcionario;
    }

}
