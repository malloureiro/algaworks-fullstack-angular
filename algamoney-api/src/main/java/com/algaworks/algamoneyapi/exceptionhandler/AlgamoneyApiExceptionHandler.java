package com.algaworks.algamoneyapi.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgamoneyApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemAlerta = messageSource.getMessage("requisicao.invalida", null, null);
		String erro = ex.getCause() == null ? ex.toString() : ex.getCause().toString();

		ApiError apiErro = new ApiError(mensagemAlerta, erro);
		return new ResponseEntity<Object>(Arrays.asList(apiErro), HttpStatus.BAD_REQUEST);
		
		//return handleExceptionInternal(ex, apiErro, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ApiError> erros = new ArrayList<>();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			erros.add(new ApiError(messageSource.getMessage(fieldError, null), fieldError.toString()));
		}
		
		return new ResponseEntity<Object>(erros, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String mensagemAlerta = messageSource.getMessage("recurso.nao.encontrado", null, null);
		String erro = ex.getCause() == null ? ex.toString() : ex.getCause().toString();
		
		List<ApiError> apiErro = Arrays.asList(new ApiError(mensagemAlerta, erro));
		return new ResponseEntity<Object>(apiErro, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String mensagemAlerta = messageSource.getMessage("recurso.operacao.nao.permitida", null, null);
		String erro = ExceptionUtils.getRootCauseMessage(ex);
		
		List<ApiError> apiErro = Arrays.asList(new ApiError(mensagemAlerta, erro));
		return new ResponseEntity<Object>(apiErro, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({InvalidDataAccessApiUsageException.class})
	public ResponseEntity<?> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex, WebRequest request) {
		String mensagemAlerta = messageSource.getMessage("recurso.operacao.sem.referencia", null, null);
		String erro = ExceptionUtils.getRootCauseMessage(ex);
		
		List<ApiError> apiErro = Arrays.asList(new ApiError(mensagemAlerta, erro));
		return new ResponseEntity<Object>(apiErro, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({PessoaInativaException.class})
	public ResponseEntity<?> handlePessoaInativaException(PessoaInativaException ex, WebRequest request) {
		String mensagemAlerta = messageSource.getMessage("recurso.encontrado.inativo", null, null);
		String erro = ExceptionUtils.getRootCauseMessage(ex);
		
		List<ApiError> apiErro = Arrays.asList(new ApiError(mensagemAlerta, erro));
		return new ResponseEntity<Object>(apiErro, HttpStatus.BAD_REQUEST);
	}
	
}

