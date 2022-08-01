package br.com.springz.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class ErroDeFormularioDto {

    private String campo;
    private String erro;


}
