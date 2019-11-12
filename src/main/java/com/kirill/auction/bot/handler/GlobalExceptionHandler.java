package com.kirill.auction.bot.handler;

import com.kirill.auction.bot.exception.AuctionFinishedException;
import com.kirill.auction.bot.exception.AuctionNotInitException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomError handle(AuctionNotInitException exception) {
        return new CustomError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomError handle(AuctionFinishedException exception) {
        return new CustomError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomError handle(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(
                        fieldError -> fieldError.getField() + " field must must be greater than 0; "
                ).collect(Collectors.joining()).trim();
        return new CustomError(HttpStatus.BAD_REQUEST, message);
    }

    private static class CustomError {

        private final LocalDateTime timestamp;

        private final String error;

        private final String message;

        CustomError(final HttpStatus error, final String message) {
            this.timestamp = LocalDateTime.now();
            this.error = error.toString();
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

    }

}
