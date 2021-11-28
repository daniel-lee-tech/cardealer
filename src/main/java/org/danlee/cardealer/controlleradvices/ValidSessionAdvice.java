package org.danlee.cardealer.controlleradvices;

import org.danlee.cardealer.exceptions.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidSessionAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(AccessDeniedException accessDeniedException) {
        return "unauthorized.html";
    }
}
