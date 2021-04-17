package com.example.quest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение для ненайденных запросов
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends ApplicationException {

    /**
     * @param message - сообщение исключения
     */
    public NotFoundException(String message) {
        super(message);
    }

}
