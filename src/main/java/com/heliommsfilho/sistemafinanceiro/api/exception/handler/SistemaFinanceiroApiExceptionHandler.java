package com.heliommsfilho.sistemafinanceiro.api.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.heliommsfilho.sistemafinanceiro.api.Mensagens;

@ControllerAdvice
public class SistemaFinanceiroApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messages;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msgUsuario = messages.getMessage(Mensagens.MENSAGEM_INVALIDA.toString(), null, LocaleContextHolder.getLocale());
		String msgDesenvolvedor = ex.getCause().toString();
		
		return handleExceptionInternal(ex, new Erro(msgUsuario, msgDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	public static class Erro {
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		
		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
	}
}