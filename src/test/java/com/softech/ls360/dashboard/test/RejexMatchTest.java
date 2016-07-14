package com.softech.ls360.dashboard.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class RejexMatchTest {

	private static final Logger logger = LogManager.getLogger();
	
	@Test
	public void test() {
		//String requestUri = "/LS360Dashboard/courses";
		String requestUri = "/LS360Dashboard/123*((&^";
		//String requestUri = "/LS360Dashboard/";
		String contextPath = "/LS360Dashboard";
		
		//String routingMatch = contextPath + "(/\\w+)";
		String routingMatch = contextPath + "(/\\S+)";
		
		if (requestUri.matches(routingMatch)) {
			logger.info("match");
		} else {
			logger.info("no match");
		}
		
	}

}
