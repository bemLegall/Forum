package br.com.uiui.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.uiui.forum.dto.TopicoDto;

@RestController

public class TopicoController{
	/*
	 * @Autowired private TopicoRepository topicoRepository;
	 */
	
	@RequestMapping("/topicos")
	public List<TopicoDto>lista() {
		
		Topico topico = new Topico("duvida","duvida com spi",new Curso("Spring","Amando"));
			
		return TopicoDto.converter(Arrays.asList(topico,topico));


		
		
	}
	
	
}
