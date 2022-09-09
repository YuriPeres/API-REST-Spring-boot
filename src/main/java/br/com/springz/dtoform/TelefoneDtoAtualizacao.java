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
public class TelefoneDtoAtualizacao {

//    @NotNull(message = "id de telefone incorreto") @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Long idTelefone;

    @NotNull(message = "id de funcionário incorreto") @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idFuncionario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Numeros informado de forma incorreta.")
    private List<BigInteger> numerosAntigos;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Numeros informado de forma incorreta.")
    private List<BigInteger> numerosNovos;


}
