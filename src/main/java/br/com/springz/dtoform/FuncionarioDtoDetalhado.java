package br.com.springz.dtoform;

import br.com.springz.model.Endereco;
import br.com.springz.model.Funcionario;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.Getter;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.hateoas.RepresentationModel;

import java.io.IOException;
import java.util.Optional;

@Getter @JsonComponent
public class FuncionarioDtoDetalhado extends RepresentationModel<FuncionarioDtoDetalhado> {

    private long id;
    private String nome;
    private String sobrenome;
    private String email;

    private Integer idade;

    private Endereco endereco;

    private boolean ativo;

    public FuncionarioDtoDetalhado() {

    }

    public FuncionarioDtoDetalhado(Optional<Funcionario> funcionario) {
        this.id = funcionario.get().getId();
        this.nome = funcionario.get().getNome();
        this.sobrenome = funcionario.get().getSobrenome();
        this.email = funcionario.get().getEmail();
        this.idade = funcionario.get().getIdade();
        this.endereco = funcionario.get().getEndereco();
        this.ativo = funcionario.get().getAtivo();
    }
//    public FuncionarioDtoDetalhado(Funcionario funcionario) {
//        this.id = funcionario.getId();
//        this.nome = funcionario.getNome();
//        this.sobrenome = funcionario.getSobrenome();
//        this.email = funcionario.getEmail();
//        this.idade = funcionario.getIdade();
//        this.ativo = funcionario.getAtivo();
//    }


    @Override
    public String toString() {
        return "FuncionarioDtoDetalhado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                ", endereco=" + endereco +
                ", ativo=" + ativo +
                '}';
    }

    public static class FuncionarioDtoDetalhadoSerializer extends JsonSerializer<FuncionarioDtoDetalhado> {


        @Override
        public void serialize(FuncionarioDtoDetalhado value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

            gen.writeStartObject();
            gen.writeStringField(
                    "endereco",
                    "CEP: "+ value.endereco.getCep()
                    +"\nLocalidade: "+ value.endereco.getLocalidade()
                    +"\nLogradouro: "+ value.endereco.getLogradouro()
            );
            gen.writeEndObject();
        }
    }
}
