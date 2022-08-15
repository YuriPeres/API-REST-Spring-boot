package br.com.springz.config;

import br.com.springz.config.ExceptionsDtos.ErroDeFormularioDto;
import br.com.springz.config.ExceptionsDtos.NaoEcontradoDto;
import br.com.springz.config.exceptions.ExceptionIdNaoEcontrado;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestControllerAdvice
public class ControllerAdviceHandler {

    private MessageSource messageSource;


    /*
    Aqui usei ResponseEntity para enviar o status.
     */
    @ExceptionHandler({ExceptionIdNaoEcontrado.class})
    public ResponseEntity<NaoEcontradoDto> naoEcontrando(ExceptionIdNaoEcontrado ex, HttpServletRequest request) {
        NaoEcontradoDto naoEcontradoDto = new NaoEcontradoDto();
        naoEcontradoDto.setExcecao(ex.getMessage());
        naoEcontradoDto.setDecricao(ex.getDescricaoMsgDeErro());
        naoEcontradoDto.setStatus(HttpStatus.NOT_FOUND.value());
        naoEcontradoDto.setInstante(Instant.now());
        naoEcontradoDto.setOndeOcorreu(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(naoEcontradoDto);
    }

    /*
    Aqui usei a anotação para mandar status.
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException ex){
        List<ErroDeFormularioDto> listaDeErros = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            listaDeErros.add(new ErroDeFormularioDto(e.getField(),
                    messageSource.getMessage(e, LocaleContextHolder.getLocale())));
        });

        return listaDeErros;
    }
}
