package br.com.springz.config.ExceptionsDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@Getter @Setter
public class NaoEcontradoDto implements Serializable {

    private Instant instante;
    private Integer status;
    private String excecao;
    private String decricao;
    private String ondeOcorreu;


    public NaoEcontradoDto() {
    }
}
