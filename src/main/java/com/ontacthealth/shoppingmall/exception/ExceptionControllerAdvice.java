package com.ontacthealth.shoppingmall.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.ontacthealth.shoppingmall.model.response.OntactApiResponse;
import com.ontacthealth.shoppingmall.model.response.OntactApiResult;
import com.ontacthealth.shoppingmall.model.response.OntactErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/22
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(OntactApiRuntimeException.class)
    public ResponseEntity<OntactApiResponse> handleOntactApiRuntimeException(OntactApiRuntimeException e) {
        log.error("OntactApiRuntimeException. => {}", e.toString());

        HttpHeaders httpHeaders = new HttpHeaders();
        Charset utf8 = StandardCharsets.UTF_8;
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, utf8));

        OntactApiResponse response = new OntactErrorResponse(e.getOntactApiResult());
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);
    }

    @ExceptionHandler({ InvalidKeyException.class, NoSuchAlgorithmException.class })
    public ResponseEntity<?> handleHashException(Exception e) {
        return systemErrorResponse(OntactApiResult.SERVER_ERROR, new Exception("Encrypt/Decrypt key is requested"), HttpStatus.LOCKED);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> handleAnyException(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        return logicalErrorResponse(OntactApiResult.SERVER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR,response);
    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<?> handleRunTimeException(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        return logicalErrorResponse(OntactApiResult.SERVER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR,response);
    }

    @ExceptionHandler({ IOException.class, ParseException.class,  JsonParseException.class })
    public ResponseEntity<?> handleParseException(Exception e, HttpServletResponse response) {
        return logicalErrorResponse(OntactApiResult.SERVER_ERROR, e, HttpStatus.BAD_REQUEST,response);
    }

    @ExceptionHandler({InterruptedException.class})
    public ResponseEntity<?> handleInterruptedException(Exception e, HttpServletResponse response){
        e.printStackTrace();
        return logicalErrorResponse(OntactApiResult.SERVER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR,response);
    }

    private ResponseEntity<OntactErrorResponse> systemErrorResponse(OntactApiResult result, Exception e, HttpStatus status) {
        String message = getMessage(e);
        OntactErrorResponse<String> errorResponse = new OntactErrorResponse<>(result, message);
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    /**
     * @param result
     * @param e
     * @param status
     * @param response
     * @return
     */
    private ResponseEntity<?> logicalErrorResponse(OntactApiResult result, Exception e, HttpStatus status, HttpServletResponse response) {
        String message = getMessage(e);
        response.setHeader("errorMessageCode", String.valueOf(result.getResultCode()));
        response.setHeader("errorMessage", result.getMessage());

        OntactErrorResponse<String> errorResponse = new OntactErrorResponse<>(result,message);
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).header("errorMessage",message).body(errorResponse);
    }

    private String getMessage(Exception e) {
        String message;
        if (ObjectUtils.isEmpty(e)) {
            message = "unknown error occurred";
        } else {
            message = e.toString();
        }

        return message;
    }
}
