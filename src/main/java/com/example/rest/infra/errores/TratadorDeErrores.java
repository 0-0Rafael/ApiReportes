package com.example.rest.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;


@RestControllerAdvice
public class TratadorDeErrores {

    /*
    Metodo para cuando no encontremos un recurso en la api se mande ese mensaje.
     */

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException exception){
        var errores = exception.getFieldErrors().stream().map(DatosErrorValidar::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidacionesDeNegocios(Exception exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity errorConstraintViolation(){
        return  ResponseEntity.badRequest().body("El campo pais_id, tiene un id de un pais que no existe.");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity errorDatosDuplicados(){
        return ResponseEntity.badRequest().body("Este pais ya esta agregado. Si no estas guardando un pais pues l campo pais_id, tiene un id de un pais que no existe. ");
    }


    private record DatosErrorValidar(String campo, String error){
        public DatosErrorValidar(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }


    }

}
