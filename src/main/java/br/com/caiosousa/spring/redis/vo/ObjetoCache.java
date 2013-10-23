package br.com.caiosousa.spring.redis.vo;

import java.io.Serializable;

/**
 * Objeto que será salvo no cache. Deve ser serializável.
 *
 * @author caiosousa
 */
public class ObjetoCache implements Serializable {

	private static final long serialVersionUID = 7194090258926613875L;
	
	private Long identificador;
	private String descricao;

	public ObjetoCache(Long identificador, String descricao) {
		this.identificador = identificador;
		this.descricao = descricao;
	}
	
	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "ObjetoCache [identificador=" + identificador + ", descricao="
				+ descricao + "]";
	}

}
