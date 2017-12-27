package br.com.adminfo.helpdesk.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Natanael
 * CONFIGURANDO HASHCODE DA SENHA DOS USUARIOS COM BCRYPTPASSWORD 
 */

@Configuration
public class WebMvcConfig extends WebMvcAutoConfiguration {
	
	@Bean
	public BCryptPasswordEncoder passwordEnconder(){
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		
		return bcrypt;
	}
}
