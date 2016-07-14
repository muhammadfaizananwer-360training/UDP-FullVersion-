package com.softech.ls360.dashboard.controller;

import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softech.ls360.dashboard.config.spring.annotation.WebController;
import com.softech.ls360.dashboard.service.JsonWebTokenService;

@WebController
@RequestMapping(value="/token")
public class JwtController {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Inject
	private ObjectMapper objectMapper;
	
	@Inject
	private JsonWebTokenService jsonWebTokenService;
	
	/**With @Value annotation, you can use the properties key to get the value from properties file.*/
	@Value("${outh2.client.username}")
	private String oauth2ClientUsername;
	
	@Value("${oauth2.client.password}")
	private String oauth2ClientPassword;
	
	@Value("${oauth2.token.end.point}")
	private String oauth2TokenEndPoint;
	
	@RequestMapping(value = "get", method = {RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<String> getAccessToken(@RequestParam Map<String,String> allRequestParams) throws Exception {
		
		logger.info("Request received at " + getClass().getName() + " for access token");
		
		String userName = allRequestParams.get("userName");
		String password = allRequestParams.get("password");
		String grantType = "password";
	    
		String apiResponse = jsonWebTokenService.getToken(oauth2TokenEndPoint, oauth2ClientUsername, oauth2ClientPassword, userName, password, grantType);
		JsonWebToken jwt = objectMapper.readValue(apiResponse, JsonWebToken.class);
		String accessToken = jwt.getAccessToken();
		
		return new ResponseEntity<String>(accessToken, HttpStatus.OK);
	}

	public static class JsonWebToken {
		
		@JsonProperty("access_token")
		private String accessToken;
		
		@JsonProperty("token_type")
		private String tokenType;
		
		public String getAccessToken() {
			return accessToken;
		}
		
		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}
		
		public String getTokenType() {
			return tokenType;
		}
		
		public void setTokenType(String tokenType) {
			this.tokenType = tokenType;
		}
		
	} //end of static inner class JsonWebToken
	
}
