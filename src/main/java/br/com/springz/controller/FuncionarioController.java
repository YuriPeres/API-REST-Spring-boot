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

    @PutMapping("/telefone/cadastrar")
    public FuncionarioDtoDetalhado cadastrarTelefone (@RequestBody @Valid TelefoneDto telefone){
        return new FuncionarioDtoDetalhado(telefoneService.cadastrarTelefoneEmFuncionario(telefone));
    }

//    @PutMapping("/telefone/{idFuncionario}")
//    public FuncionarioDtoDetalhado cadastrarTelefone (@PathVariable Long idFuncionario, @RequestBody @Valid String telefones){
//        return new FuncionarioDtoDetalhado(telefoneService.cadastrarTelefoneEmFuncionario(idFuncionario, telefones));
//    }

    @PutMapping("/telefone/atualizar")
    public FuncionarioDtoDetalhado atualizarTelefoneDoUsuario (@RequestBody @Valid TelefoneDto dto){
        telefoneService.atualizarTelefone(dto);
        return new FuncionarioDtoDetalhado(telefoneService.retornaFuncionario(dto.getIdFuncionario()));
    }

    @DeleteMapping("/telefone/")
    public ResponseEntity<?> deletarTelefoneDoFuncionario(@RequestBody @Valid TelefoneFormIds ids){
        System.out.println(ids);
        return telefoneService.deletarTelefoneApenasDoFuncionario(ids.getIdFuncionario(), ids.getIdTelefone());
    }

    @DeleteMapping("telefone/{id}")
    public ResponseEntity<?> deletarTelefone(@PathVariable Long id){
        return telefoneService.deletarTelefone(id);
    }

}
