package com.softech.ls360.dashboard.exception;

public class OAuth2AuthenticationServerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String oauth2AuthenticationServerError;
	private int httpStatusCode;

	public OAuth2AuthenticationServerException(String oauth2AuthenticationServerError, int httpStatusCode) {
		super(oauth2AuthenticationServerError);
		this.oauth2AuthenticationServerError = oauth2AuthenticationServerError;
		this.httpStatusCode = httpStatusCode;
	}

	public String getOauth2AuthenticationServerError() {
		return oauth2AuthenticationServerError;
	}

	public void setOauth2AuthenticationServerError(String oauth2AuthenticationServerError) {
		this.oauth2AuthenticationServerError = oauth2AuthenticationServerError;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

}
