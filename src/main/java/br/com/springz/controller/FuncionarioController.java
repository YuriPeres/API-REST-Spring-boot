package br.com.springz.controller;

import br.com.springz.dtoform.*;
import br.com.springz.model.Funcionario;
import br.com.springz.service.FuncionarioService;
import br.com.springz.service.TelefoneService;
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
    private final TelefoneService telefoneService;


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
    public FuncionarioDto atualizarFuncionario(@PathVariable Long id,
                                               @RequestBody @Valid FuncionarioFormAtualizacao formAtualizacao){
        return funcionarioService.atualizar(id, formAtualizacao);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarFuncionario(@PathVariable Long id){
        return funcionarioService.delete(id);
    }


    /*
    Telefone
     */

    @GetMapping("/telefone")
    public List<TelefoneDto> listarTelefones() {
        return telefoneService.listarTodosTelefones();
    }

    @PutMapping("/telefone/{id}")
    @Transactional
    public Funcionario cadastrarTelefone (@PathVariable Long id, @RequestBody @Valid TelefoneDto telefone){
        return telefoneService.cadastrarTelefoneEmFuncionario(id, telefone);
    }

    @DeleteMapping("/telefone/{idTelefone}")
    @Transactional
    public ResponseEntity<?> deletarTelefoneDoFuncionario(@PathVariable Long idTelefone){
        return telefoneService.deletarTelefoneApenasDoFuncionario(12L, idTelefone);
    }

}
