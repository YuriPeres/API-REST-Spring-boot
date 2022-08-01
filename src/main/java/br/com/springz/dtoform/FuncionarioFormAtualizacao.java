package br.com.springz.dtoform;

import br.com.springz.model.Funcionario;
import br.com.springz.repository.FuncionarioRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class FuncionarioFormAtualizacao {

    @NotBlank
    @NotEmpty
    private String nome;
    @NotBlank @NotEmpty
    private String sobrenome;
//    @NotBlank @NotEmpty
//    private String email;
//    @NotBlank @NotEmpty
//    private String idade;


    public FuncionarioFormAtualizacao() {
    }

    public FuncionarioFormAtualizacao(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.sobrenome = funcionario.getSobrenome();
//        this.email = funcionario.getEmail();
//        this.idade = funcionario.getIdade().toString();
    }

    public Funcionario atualizarFuncionario(Long id, FuncionarioRepository funcionarioRepository) {
        Funcionario funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.setNome (this.nome);
        funcionario.setSobrenome(this.sobrenome);
//        funcionario.setEmail(this.email);
//        funcionario.setIdade(Integer.parseInt((this.idade)));

        return funcionario;
    }
}
