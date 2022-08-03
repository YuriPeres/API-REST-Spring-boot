package br.com.springz.controller;

import br.com.springz.dtoform.FuncionarioDto;
import br.com.springz.dtoform.FuncionarioDtoDetalhado;
import br.com.springz.dtoform.FuncionarioForm;
import br.com.springz.dtoform.FuncionarioFormAtualizacao;
import br.com.springz.model.Funcionario;
import br.com.springz.service.FuncionarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    public List<FuncionarioDto> listarFuncionarios(){
        return funcionarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDtoDetalhado> detalhesDoFuncionario(@PathVariable Long id){
        return funcionarioService.detalharFuncionario(id);
    }

//    @GetMapping("/{nome}")
//    public FuncionarioDtoDetalhado DetalhesDoFuncionarioPorNome(@PathVariable String nome){
//        return funcionarioService.detalharFuncionarioPorNome(nome);
//    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Funcionario cadastrarFuncionario(@RequestBody FuncionarioForm funcionarioForm){
//        return funcionarioService.cadastrar(funcionarioForm);
//    }

    @PostMapping
    @Transactional
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody @Valid FuncionarioForm funcionarioForm){
        return new ResponseEntity<>(funcionarioService.cadastrar(funcionarioForm), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FuncionarioDto> atualizarFuncionario(@PathVariable Long id, @RequestBody @Valid FuncionarioFormAtualizacao funcionarioFormAtualizacao){
        return funcionarioService.atualizar(id, funcionarioFormAtualizacao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarFuncionario(@PathVariable Long id){
        return funcionarioService.delete(id);
    }

}
