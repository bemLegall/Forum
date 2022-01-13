package br.com.uiui.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.alura.forum.modelo.Topico;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {Topico.class})
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
