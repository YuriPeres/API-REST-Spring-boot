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
import java.util.List;
import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor
public class TelefoneDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Min(value = 8, message = "Um número contém mais digitos.")
    @Max(value = 13, message = "Ultrapassou numero máximo de digitos.")
    @NotNull(message = "Nome informado de forma incorreta.")
    private Long numero;

    private List<Funcionario> funcionarios;

    public TelefoneDto(Telefone telefone) {
        this.id = telefone.getId();
        this.numero = telefone.getNumero();
        this.funcionarios = telefone.getFuncionarios();
    }

    public TelefoneDto(Long id, Long numero) {
        this.id = id;
        this.numero = numero;
    }
}
