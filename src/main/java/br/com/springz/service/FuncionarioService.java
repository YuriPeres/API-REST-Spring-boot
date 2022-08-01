package br.com.springz.service;

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

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@ResponseBody
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public List<FuncionarioDto> listar(){
        return FuncionarioDto.converter(funcionarioRepository.findAll());
    }

    public ResponseEntity<FuncionarioDtoDetalhado> detalharFuncionario(Long id) {
        if(funcionarioExiste(id)) {
            return ResponseEntity.ok(new FuncionarioDtoDetalhado(funcionarioRepository.findById(id)));
        }
        return ResponseEntity.notFound().build();
    }
//    public FuncionarioDtoDetalhado detalharFuncionarioPorNome(String nome) {
//        Funcionario funcionario = funcionarioRepository.findByNome(nome);
//        return new FuncionarioDtoDetalhado(funcionario);
//    }

    public Funcionario cadastrar(FuncionarioForm funcionarioForm){
        Funcionario funcionario = funcionarioForm.converter(funcionarioForm);
        System.out.println(funcionario);
        funcionarioRepository.save(funcionario);
        //URI uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(funcionario.getId()).toUri();
        return funcionarioRepository.save(funcionario);
    }

    public ResponseEntity<FuncionarioDto> atualizar(Long id, FuncionarioFormAtualizacao funcionarioFormAtualizacao){
        if(funcionarioExiste(id)){
            return ResponseEntity.ok(new FuncionarioDto(funcionarioFormAtualizacao.atualizarFuncionario(id, funcionarioRepository)));
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

    public boolean funcionarioExiste(Long id){
        return funcionarioRepository.findById(id).isPresent();
    }

}
