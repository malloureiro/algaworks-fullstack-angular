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
	
	/**
	 * Tratamento de exceção que ocorre quando um determinado parâmetro "desconhecido", ou seja,
	 * que não existe no model e portanto não pode ser deserializado, é enviado na requisição.
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemAlerta = messageSource.getMessage("requisicao.invalida", null, null);
		String erro = ex.getCause() == null ? ex.toString() : ex.getCause().toString();

		ApiError apiErro = new ApiError(mensagemAlerta, erro);
		return new ResponseEntity<Object>(Arrays.asList(apiErro), HttpStatus.BAD_REQUEST);
		//return handleExceptionInternal(ex, apiErro, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * Tratamento de exceção que ocorre quando uma requisição é enviada com um erro de validação (a partir de @Valid no RequestBody).
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ApiError> erros = new ArrayList<>();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			erros.add(new ApiError(messageSource.getMessage(fieldError, null), fieldError.toString()));
		}
		
		return new ResponseEntity<Object>(erros, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Tratamento de exceção lançada ao verificar um recurso inexistente no banco de dados.
	 * 
	 * Verificar lançamento explícito em PessoaServiceImpl.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String mensagemAlerta = messageSource.getMessage("recurso.nao.encontrado", null, null);
		String erro = ex.getCause() == null ? ex.toString() : ex.getCause().toString();
		
		List<ApiError> apiErro = Arrays.asList(new ApiError(mensagemAlerta, erro));
		return new ResponseEntity<Object>(apiErro, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Tratamento de exceção lançada quando uma operação em banco de dados é realizada com valor inválido de chave (violação de chave primária e/ou estrangeira).
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String mensagemAlerta = messageSource.getMessage("recurso.operacao.nao.permitida", null, null);
		String erro = ExceptionUtils.getRootCauseMessage(ex);
		
		List<ApiError> apiErro = Arrays.asList(new ApiError(mensagemAlerta, erro));
		return new ResponseEntity<Object>(apiErro, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Tratamento de exceção lançada quando uma operação em banco de dados é realizada sem informação de valor de chave de acesso.
	 * 
	 * Por exemplo: acesso ao objeto Lancamento sem informação de código de chave estrangeira.
	 *  
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({InvalidDataAccessApiUsageException.class})
	public ResponseEntity<?> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex, WebRequest request) {
		String mensagemAlerta = messageSource.getMessage("recurso.operacao.sem.referencia", null, null);
		String erro = ExceptionUtils.getRootCauseMessage(ex);
		
		List<ApiError> apiErro = Arrays.asList(new ApiError(mensagemAlerta, erro));
		return new ResponseEntity<Object>(apiErro, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Tratamento de exceção lançada explicitamente quando uma operação é realizada em um recurso Pessoa com estado inativo.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({PessoaInativaException.class})
	public ResponseEntity<?> handlePessoaInativaException(PessoaInativaException ex, WebRequest request) {
		String mensagemAlerta = messageSource.getMessage("recurso.encontrado.inativo", null, null);
		String erro = ExceptionUtils.getRootCauseMessage(ex);
		
		List<ApiError> apiErro = Arrays.asList(new ApiError(mensagemAlerta, erro));
		return new ResponseEntity<Object>(apiErro, HttpStatus.BAD_REQUEST);
	}
	
}

