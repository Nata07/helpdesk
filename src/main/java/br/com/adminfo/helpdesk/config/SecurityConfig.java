package br.com.adminfo.helpdesk.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Natanael
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	// CONFIGURANDO PERMISSAO DAS ROTAS DE NAVEGAÇÃO (O QUE É PERMITIDO OU NAO)
		protected void configure(HttpSecurity http) throws Exception{
			http
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/registro").permitAll()
				.antMatchers("/**")
					.hasAnyAuthority("ADMIN","USUARIO")
					.anyRequest()
				.authenticated()
					.and()
					.csrf()
					.disable() 
					.formLogin()
				//definindo pagina de login padrao
				.loginPage("/login") 
					// Em caso de erro redireciona para pagina de erro e mostra o erro gerado. 
					.failureUrl("/login?errors=true")
					// CONFIGURADO URL PADRAO
					.defaultSuccessUrl("/")
					// CONFIGURANDO PARAMETROS DE VALIDAÇÃO DO LOGIN (EMAIL E SENHA)
					.usernameParameter("email")
					.passwordParameter("senha")
				.and()
				// CONFIGURANDO LOGOUT
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/")
						.and()
						.exceptionHandling()
						.accessDeniedPage("/acessoNegado");
		}
		
		
		// QUALQUER USUARIO TEM ACESSO NO QUE TIVER DECLARADO
		public void configure(WebSecurity webSecurity){
			webSecurity
				.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "/img/**", "/resources/**");
		}
	
	//CONFIGURANDO AUTENTICAÇÃO
		protected void configure(AuthenticationManagerBuilder auth) throws Exception{
			auth
				.jdbcAuthentication()
					.usersByUsernameQuery("select email, senha, ativo from usuario u where u.email = ? and u.ativo = 1")
					.authoritiesByUsernameQuery("select u.email, p.nome from usuario u "
							+ "inner join usuario_permissao up on (u.codigo = up.codigo_usuario) "
							+ "inner join permissao p on (up.codigo_permissao = p.codigo)"
							+ "where u.email = ?"
							+ "and u.ativo = 1")
					.dataSource(dataSource)
					.passwordEncoder(bcrypt);
		}
}
