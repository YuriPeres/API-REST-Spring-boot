package br.com.springz.config.ExceptionsDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErroDeFormularioDto {

    private String campo;
    private String erro;


}
