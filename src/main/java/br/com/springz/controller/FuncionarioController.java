package br.com.springz.controller;

import br.com.springz.dtoform.*;
import br.com.springz.model.Funcionario;
import br.com.springz.repository.FuncionarioRepository;
import br.com.springz.service.FuncionarioService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
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
    public List<FuncionarioDto> listarFuncionarios() throws Exception {
        return funcionarioService.listar();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public FuncionarioDtoDetalhado detalhesDoFuncionario(@PathVariable Long id) throws Exception {
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
        System.out.println(funcionarioForm);
        System.out.println(funcionarioForm.getEnderecoForm());
        System.out.println(funcionarioForm.getEnderecoForm().getBairro());
        return new ResponseEntity<>(funcionarioService.cadastrar(funcionarioForm), HttpStatus.CREATED);
    }

//    @PostMapping("./endereco")
//    @Transactional
//    public ResponseEntity<Endereco> cadastrarEndereco(@RequestBody @Valid EnderecoForm enderecoForm){
//        return new ResponseEntity<>(funcionarioService.cadastrar(enderecoForm), HttpStatus.CREATED);
//    }

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
