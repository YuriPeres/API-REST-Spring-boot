package br.com.springz.dtoform;

import br.com.springz.model.Funcionario;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class FuncionarioDto extends RepresentationModel<FuncionarioDto> {

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



}
