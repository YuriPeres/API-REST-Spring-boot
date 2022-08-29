package br.com.springz.dtoform;

import br.com.springz.model.Endereco;
import br.com.springz.model.Funcionario;
import br.com.springz.model.Telefone;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.hateoas.RepresentationModel;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class FuncionarioDtoDetalhado extends RepresentationModel<FuncionarioDtoDetalhado> {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;

    private Integer idade;

    private Endereco endereco;

    private List<Telefone> telefones;

    private boolean ativo;


    public FuncionarioDtoDetalhado(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.sobrenome = funcionario.getSobrenome();
        this.email = funcionario.getEmail();
        this.idade = funcionario.getIdade();
        this.endereco = funcionario.getEndereco();
        this.telefones = funcionario.getTelefones();
        this.ativo = funcionario.getAtivo();
    }

//    public FuncionarioDtoDetalhado(Funcionario funcionario) {
//        this.id = funcionario.getId();
//        this.nome = funcionario.getNome();
//        this.sobrenome = funcionario.getSobrenome();
//        this.email = funcionario.getEmail();
//        this.idade = funcionario.getIdade();
//        this.endereco = funcionario.getEndereco();
//        this.ativo = funcionario.getAtivo();
//        this.telefones = funcionario.getTelefones();
//    }
//    public FuncionarioDtoDetalhado(Funcionario funcionario) {
//        this.id = funcionario.getId();
//        this.nome = funcionario.getNome();
//        this.sobrenome = funcionario.getSobrenome();
//        this.email = funcionario.getEmail();
//        this.idade = funcionario.getIdade();
//        this.ativo = funcionario.getAtivo();
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FuncionarioDtoDetalhado that = (FuncionarioDtoDetalhado) o;
        return ativo == that.ativo && Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(sobrenome, that.sobrenome) && Objects.equals(email, that.email) && Objects.equals(idade, that.idade) && Objects.equals(endereco, that.endereco) && Objects.equals(telefones, that.telefones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, nome, sobrenome, email, idade, endereco, telefones, ativo);
    }

    @Override
    public String toString() {
        return "FuncionarioDtoDetalhado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                ", endereco=" + endereco +
                ", telefone=" + telefones +
                ", ativo=" + ativo +
                '}';
    }

    //    public static class FuncionarioDtoDetalhadoSerializer extends JsonSerializer<FuncionarioDtoDetalhado> {
//
//
//        @Override
//        public void serialize(FuncionarioDtoDetalhado value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//
//            gen.writeStartObject();
//            gen.writeStringField(
//                    "endereco",
//                    "CEP: "+ value.endereco.getCep()
//                    +"\nLocalidade: "+ value.endereco.getLocalidade()
//                    +"\nLogradouro: "+ value.endereco.getLogradouro()
//            );
//            gen.writeEndObject();
//        }
//    }
}
