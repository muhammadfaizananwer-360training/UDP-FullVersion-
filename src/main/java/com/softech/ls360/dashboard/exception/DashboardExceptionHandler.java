package com.softech.ls360.dashboard.exception;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import org.apache.http.conn.HttpHostConnectException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softech.ls360.dashboard.controller.JwtController.JsonWebToken;

@ControllerAdvice
public class DashboardExceptionHandler {

	private static final Logger logger = LogManager.getLogger();
	
	@Inject
	private ObjectMapper objectMapper;
	
	@ExceptionHandler(DashboardAuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleDashboardException(DashboardAuthenticationException e) {
		ErrorResponse errors = new ErrorResponse();
		ErrorItem error = new ErrorItem();
		String errorCode = e.getErrorCode();
		String errorMessage = e.getErrorMessage();
		error.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		error.setCode(errorCode);
		error.setMessage(errorMessage);
		errors.addError(error);

		logger.error("DashboardAuthenticationException occured: " + errorMessage);
		return new ResponseEntity<ErrorResponse>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpHostConnectException.class)
	public ResponseEntity<ErrorResponse> handleHttpHostConnectExceptionException(HttpHostConnectException e) {
		
		String errorCode = "error.network.connect";
		String errorMessage = e.getMessage();
		
		ErrorResponse errors = new ErrorResponse();
		ErrorItem error = new ErrorItem();
		error.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		error.setCode(errorCode);
		error.setMessage(errorMessage);
		errors.addError(error);

		logger.error("HttpHostConnectException occured: " + errorMessage);
		return new ResponseEntity<ErrorResponse>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(OAuth2AuthenticationServerException.class)
	public ResponseEntity<ErrorResponse> handleOAuth2AuthenticationServerException(OAuth2AuthenticationServerException e) throws Exception {
		
		String errorJson = e.getMessage();
		
		OAuth2AuthneticationServerError oauth2AuthneticationServerError = objectMapper.readValue(errorJson, OAuth2AuthneticationServerError.class);
		int httpStatusCode = e.getHttpStatusCode();
		
		String errorCode = "error." + oauth2AuthneticationServerError.getError();
		String errorMessage = oauth2AuthneticationServerError.getErrorDescription();
		
		ErrorResponse errors = new ErrorResponse();
		ErrorItem error = new ErrorItem();
		error.setStatus(httpStatusCode);
		error.setCode(errorCode);
		error.setMessage(errorMessage);
		errors.addError(error);

		HttpStatus httpStatus = HttpStatus.valueOf(httpStatusCode);
		
		logger.error("OAuth2AuthenticationServerException occured: " + errorMessage);
		return new ResponseEntity<ErrorResponse>(errors, httpStatus);
	}
	
	@XmlRootElement(name = "error")
	public static class ErrorItem {
		
		private int status;
		private String code;
		private String message;

		@XmlAttribute
		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		@XmlAttribute
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		@XmlValue
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	@XmlRootElement(name = "errors")
	public static class ErrorResponse {
		
		private List<ErrorItem> errors = new ArrayList<>();

		@XmlElement(name = "error")
		public List<ErrorItem> getErrors() {
			return errors;
		}

		public void setErrors(List<ErrorItem> errors) {
			this.errors = errors;
		}

		public void addError(ErrorItem error) {
			this.errors.add(error);
		}
	}
	
	public static class OAuth2AuthneticationServerError {
		
		private String error;

		@JsonProperty("error_description")
		private String errorDescription;

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getErrorDescription() {
			return errorDescription;
		}

		public void setErrorDescription(String errorDescription) {
			this.errorDescription = errorDescription;
		}
		 
	}
	
}
