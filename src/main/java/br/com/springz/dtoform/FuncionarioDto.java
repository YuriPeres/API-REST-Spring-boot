package br.com.springz.dtoform;

import br.com.springz.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor @NoArgsConstructor
public class FuncionarioDto extends RepresentationModel<FuncionarioDto> {

    private Long id;
    private String nome;
    private String sobrenome;
//    private String email;


    public FuncionarioDto(long id, String nome, String sobrenome) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public FuncionarioDto(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.sobrenome = funcionario.getSobrenome();
//        this.email = funcionario.getEmail();

    }
    public FuncionarioDto(FuncionarioFormAtualizacao form) {
        this.nome = form.getNome();
        this.sobrenome = form.getSobrenome();
//        this.email = funcionario.getEmail();

    }



}
