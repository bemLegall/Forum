package br.com.alura.forum;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.alura.forum.repository.UsuarioRepository;
import br.com.alura.forum.security.AutenticacaoViaTokenFilter;
import br.com.alura.forum.security.TokenService;

@EnableWebSecurity
@Configuration 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	
	private AutenticacaoService autenticacaoService;
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	
	public SecurityConfiguration(AutenticacaoService autenticacaoService,TokenService tokenService,UsuarioRepository usuarioRepository  ) {
		this.autenticacaoService = autenticacaoService;
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {

		return super.authenticationManager();
	}
	
	//configuracoes de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
		
		
	}
	
	//configuracoes de autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/topicos").permitAll()
		.antMatchers(HttpMethod.GET,"/topicos/*").permitAll()
		.antMatchers(HttpMethod.GET,"/topicos/*").permitAll()
		.antMatchers(HttpMethod.GET,"/topicos/id/*").permitAll()
		.antMatchers(HttpMethod.GET,"/actuator/**").permitAll()	
		.antMatchers(HttpMethod.POST,"/auth").permitAll()
		.antMatchers(HttpMethod.DELETE,"/topicos/*").hasRole("MODERADOR")	//o Spring coloca um "Role_" antes do moderador <lembrar disso no banco de dados>
		.anyRequest().authenticated()
		.and().csrf().disable() 
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	
	}
	//configuracoes de recursos estaticos (js,css, imagens, etc).
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		
	}

	/*
	 * public static void main(String[] args) { System.out.println(new
	 * BCryptPasswordEncoder().encode("123")); }
	 */
	
	
}
