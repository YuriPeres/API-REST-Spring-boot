package br.com.springz.dtoform;

import br.com.springz.model.Funcionario;
import br.com.springz.model.Telefone;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor
public class TelefoneDto {

    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "Nome informado de forma incorreta.")
    private BigInteger numero;

//    @NotNull
//    private Long idFuncionario;

    @JsonIgnore
    private List<Funcionario> funcionarios;

    public TelefoneDto(Telefone telefone) {
        this.id = telefone.getId();
        this.numero = telefone.getNumero();
        this.funcionarios = telefone.getFuncionarios();
    }

    public TelefoneDto(Long id, BigInteger numero) {
        this.id = id;
        this.numero = numero;
    }

//    public TelefoneDto(BigInteger numero, Long idFuncionario) {
//        this.idFuncionario = id;
//        this.numero = numero;
//    }
}
