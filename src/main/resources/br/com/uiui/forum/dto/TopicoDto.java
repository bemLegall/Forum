package br.com.uiui.forum.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.modelo.Topico;

public class TopicoDto {

	private long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataDeCriacao;

	public TopicoDto(Topico topico) {

		this.dataDeCriacao = topico.getDataCriacao();
		this.id = topico.getId();
		this.mensagem = topico.getMensagem();
		this.titulo = topico.getTitulo();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

	public long getId() {
		return id;
	}

	public static List<TopicoDto> converter(List<Topico> topicos) {
		
		return  topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
	}

}
