package br.com.alura.forun.config.validacao;

import br.com.alura.forun.dto.ErroHandlerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroHandler {


    private MessageSource messageSource;

    @Autowired
    public ErroHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroHandlerDto> handle(MethodArgumentNotValidException exception){
        List<ErroHandlerDto> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e->{
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroHandlerDto erro = new ErroHandlerDto(e.getField(), mensagem);
            dto.add(erro);
        });
        return dto;
    }
}
