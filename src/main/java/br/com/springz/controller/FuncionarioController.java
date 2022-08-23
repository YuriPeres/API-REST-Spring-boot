package br.com.springz.controller;

import br.com.springz.dtoform.*;
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
    public List<FuncionarioDto> listarFuncionarios() {
        return funcionarioService.listarTodos();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public FuncionarioDtoDetalhado funcionarioPorId(@PathVariable Long id) {
        return funcionarioService.acharPorId(id);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario cadastrarFuncionario(@RequestBody @Valid FuncionarioForm funcionarioForm){
        return funcionarioService.cadastrar(funcionarioForm);
    }
    @PutMapping("/{id}")
    @Transactional
    public FuncionarioDto atualizarFuncionario(@PathVariable Long id, @RequestBody @Valid FuncionarioFormAtualizacao funcionarioFormAtualizacao){
        return funcionarioService.atualizar(id, funcionarioFormAtualizacao);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarFuncionario(@PathVariable Long id){
        return funcionarioService.delete(id);
    }
}
