package br.com.springz.config;

import br.com.springz.config.ExceptionsDtos.NaoEcontradoDto;
import br.com.springz.config.exceptions.ExceptionNaoEcontrado;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@AllArgsConstructor
@RestControllerAdvice
public class ErroDeValidacaoHandler {

   // @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ExceptionNaoEcontrado.class})
    public ResponseEntity<NaoEcontradoDto> naoEcontrando(ExceptionNaoEcontrado ex, HttpServletRequest request) {
        NaoEcontradoDto naoEcontradoDto = new NaoEcontradoDto();
        naoEcontradoDto.setExcecao(ex.getMessage());
        naoEcontradoDto.setDecricao(ex.getDescricaoMsgDeErro());
        naoEcontradoDto.setStatus(HttpStatus.NOT_FOUND.value());
        naoEcontradoDto.setInstante(Instant.now());
        naoEcontradoDto.setOndeOcorreu(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(naoEcontradoDto);
    }
}
