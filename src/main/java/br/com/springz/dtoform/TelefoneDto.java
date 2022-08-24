package br.com.springz.dtoform;

import br.com.springz.model.Funcionario;
import br.com.springz.model.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor
public class TelefoneDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Min(value = 16, message = "Idade incorreta.") @Max(value = 100, message = "Já deu né? Aposenta.")
    @NotNull(message = "Nome informado de forma incorreta.")
    private Long numero;

    private Set<Funcionario> funcionarios;

    public TelefoneDto(Telefone telefone) {
        this.id = telefone.getId();
        this.numero = telefone.getNumero();
        this.funcionarios = telefone.getFuncionarios();
    }
}
