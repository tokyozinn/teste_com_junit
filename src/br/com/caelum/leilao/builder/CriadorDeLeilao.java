package br.com.caelum.leilao.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class CriadorDeLeilao {

	private Leilao leilao;
	
	public CriadorDeLeilao() {}
	
	public CriadorDeLeilao para(String descricao) {
		this.leilao = new Leilao(descricao);
		return this;
	}
	
	public CriadorDeLeilao lance(Usuario user, double valor) {
		this.leilao.propoe(new Lance(user, valor));
		return this;
	}
	
	public Leilao constroi() {
		return this.leilao;
	}
}
