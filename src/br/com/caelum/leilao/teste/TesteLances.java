package br.com.caelum.leilao.teste;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class TesteLances {

	@Test
	public void naoAceitarDoisLancesDoMesmoUsuario() {

		Leilao leilao = new Leilao("PS5 novo");
		Usuario steveJobs = new Usuario("Steve Jobs");

		leilao.propoe(new Lance(steveJobs, 2000.0));
		leilao.propoe(new Lance(steveJobs, 3000.0));

		assertThat(leilao.getLances().size(), equalTo(1));

	}

	@Test(expected = IllegalArgumentException.class)
	public void naoPodeTerLanceIgualAZero() {

		Leilao leilao = new CriadorDeLeilao().para("PS4 Novo").lance(new Usuario("John Wick"), 0).constroi();
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoPodeTerLanceComValorNegativo() {

		Leilao leilao = new CriadorDeLeilao().para("PS4 Novo").lance(new Usuario("John Wick"), -150.0).constroi();
	}
}
