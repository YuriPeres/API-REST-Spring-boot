package br.com.springz.dtoform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneFormIds {


    @NotNull
    private Long idFuncionario;

    @NotNull
    private Long idTelefone;

}
