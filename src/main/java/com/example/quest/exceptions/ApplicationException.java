package com.example.quest.exceptions;

/**
 * Общее исключение для ошибок в приложении
 */
public class ApplicationException extends Exception {

    /**
     * @param message - сообщение исключения
     */
    public ApplicationException(String message) {
        super(message);
    }

}
