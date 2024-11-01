package com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.customizedexception;

import com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.customizedexception.response.ExceptionResponse;
import com.kojstarinnovations.afaas.commons.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.persistence.TransactionRequiredException;
import jakarta.validation.UnexpectedTypeException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ControllerAdvisor
 *
 * @author Augusto Vicente
 */
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    /**
     * Handle not found exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle null pointer exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        ex.printStackTrace();

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle illegal argument exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle no data found exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(NoDataFoundException.class)
    public final ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle already exists exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(DuplicateException.class)
    public final ResponseEntity<Object> handleAlreadyExistsException(DuplicateException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handle invalid data exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(InvalidDataException.class)
    public final ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle validate exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(ValidateException.class)
    public final ResponseEntity<Object> handleValidateException(ValidateException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle data integrity violation exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Data Integrity Violation", List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handle io exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Username Not Found: " + ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle io exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(MalformedJwtException.class)
    public final ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Malformed JWT: " + ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle io exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(UnsupportedJwtException.class)
    public final ResponseEntity<Object> handleUnsupportedJwtOperationException(UnsupportedOperationException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Unsupported Operation: " + ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle io exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public final ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Expired JWT: " + ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle io exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(SignatureException.class)
    public final ResponseEntity<Object> handleSignatureException(SignatureException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Signature Exception: " + ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle io exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(org.springframework.security.core.AuthenticationException.class)
    public final ResponseEntity<Object> handleAuthenticationException(org.springframework.security.core.AuthenticationException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle transaction required exception response entity.
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(TransactionRequiredException.class)
    public final ResponseEntity<Object> handleTransactionRequiredException(TransactionRequiredException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    /**
     * Handle unsupported operation exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(UnsupportedOperationException.class)
    public final ResponseEntity<Object> handleUnsupportedOperationException(UnsupportedOperationException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Unsupported Operation: " + ex.getMessage(), List.of(request.getDescription(false)));

        //Prints the stack trace of the exception
        ex.printStackTrace();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle bad operation exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(BadOperationException.class)
    public final ResponseEntity<Object> handleBadOperationException(BadOperationException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Bad Operation: " + ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle missing servlet request part exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(SizeLimitExceededException.class)
    public final ResponseEntity<Object> handleSizeLimitExceededException(SizeLimitExceededException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Size Limit Exceeded: " + ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle missing servlet request part exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(FileSizeLimitExceededException.class)
    public final ResponseEntity<Object> handleFileSizeLimitExceededException(FileSizeLimitExceededException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "File Size Limit Exceeded: " + ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MapperException.class)
    public final ResponseEntity<Object> handleMapperException(MapperException ex, WebRequest request) {

        ex.printStackTrace();

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Mapper Exception: " + ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle missing servlet request part exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(NotEnoughException.class)
    public final ResponseEntity<Object> handleNotEnoughException(NotEnoughException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Not Enough: " + ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle unexpected type exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    public final ResponseEntity<Object> handleUnexpectedTypeException(UnexpectedTypeException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Unexpected Type: " + ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle sql exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(SQLException.class)
    public final ResponseEntity<Object> handleSQLException(SQLException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Error in SQL: " + ex.getMessage(), List.of(request.getDescription(false)));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle method argument not valid response entity.
     *
     * @param ex      the ex
     * @param headers the headers
     * @param status  the status
     * @param request the request
     * @return the response entity
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Validation Failed", errors);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
