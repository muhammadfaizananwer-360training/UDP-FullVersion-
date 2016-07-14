package com.softech.ls360.dashboard.config.spring.context;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import com.softech.ls360.dashboard.filter.DashboardFilterMarker;
import com.softech.ls360.dashboard.filter.ReactFilter;
import com.softech.ls360.dashboard.service.LmsUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true, 
    order = 0, 
    mode = AdviceMode.PROXY,
    proxyTargetClass = false
)
@ComponentScan(
		basePackageClasses = {DashboardFilterMarker.class}      
)
public class SecurityConfiguration {
	
	private static final Logger log = LogManager.getLogger();
	
	@Configuration
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		
		@Inject 
		private LmsUserDetailsService lmsUserDetailsService;
		
		@Inject 
		private ReactFilter reactFilter;
	
	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
		
		@Bean
	    protected SessionRegistry sessionRegistryImpl() {
	        return new SessionRegistryImpl();
	    }
		
		@Override
		protected void configure(AuthenticationManagerBuilder builder) throws Exception {
			
			ReflectionSaltSource saltSource = new ReflectionSaltSource();
	        saltSource.setUserPropertyToUse("userGuid");
			
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setSaltSource(saltSource);
	        
	        provider.setUserDetailsService(this.lmsUserDetailsService);
	       // provider.setPasswordEncoder(new ShaPasswordEncoder());
	        
	        
	        builder.authenticationProvider(provider).eraseCredentials(false);
	        
		}
		
		@Override
		public void configure(WebSecurity security) {
			security.ignoring().antMatchers("/resources/**");
		}
		
		@Override
		protected void configure(HttpSecurity security) throws Exception {
			
			log.info("Configuring Web Security.");
			
			security
				.authorizeRequests()
				    .antMatchers("/login/**").permitAll()
				    .antMatchers("/login").permitAll()
				    .antMatchers("/logout").permitAll();
			
			security.addFilterAfter(reactFilter, ExceptionTranslationFilter.class);
		
		}	
		
	} //end of class FormLoginWebSecurityConfigurerAdapter
	
}
