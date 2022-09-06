package br.com.springz.dtoform;

import br.com.springz.model.Funcionario;
import br.com.springz.model.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class TelefoneDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idFuncionario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Numeros informado de forma incorreta.")
    private List<BigInteger> numeros;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigInteger numero;

    @JsonIgnore
    private List<Funcionario> funcionarios;

    public TelefoneDto(Telefone telefone) {
        this.id = telefone.getId();
        this.numero = telefone.getNumero() ;
        this.funcionarios = telefone.getFuncionarios();
    }

    public TelefoneDto(Long id, List<BigInteger> numeros) {
        this.id = id;
        this.numeros = numeros;
    }

//    public TelefoneDto(BigInteger numero, Long idFuncionario) {
//        this.idFuncionario = id;
//        this.numero = numero;
//    }
}
