package br.com.springz.dtoform;

import br.com.springz.model.Funcionario;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FuncionarioDto {

    private long id;
    private String nome;
    private String sobrenome;
//    private String email;

    public FuncionarioDto() {

    }

    public FuncionarioDto(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.sobrenome = funcionario.getSobrenome();
//        this.email = funcionario.getEmail();

    }

    public static List<FuncionarioDto> converter(List<Funcionario> funcionarios) {
        return funcionarios.stream().map(FuncionarioDto::new).collect(Collectors.toList());
    }

}
