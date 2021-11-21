package com.wproject.livrariaapi.infra;

public interface EnviadorDeEmail {
	void enviarEmail(String destinatario,
			String assunto,
			String mensagem);
}
