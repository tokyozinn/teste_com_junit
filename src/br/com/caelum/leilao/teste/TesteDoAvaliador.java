package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.AfterClass;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	private Avaliador leiloeiro;

	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
	}

	@Test(expected = RuntimeException.class)
	public void deveHaverUmLanceNoLeilao() {

		Leilao leilao = new CriadorDeLeilao().para("PS4 Novo").constroi();

		leiloeiro.avalia(leilao);
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		Leilao leilao = new CriadorDeLeilao().para("PS4 Novo").lance(new Usuario("João"), 250.0)
				.lance(new Usuario("Maria"), 300.0).lance(new Usuario("José"), 450.0).constroi();

		leiloeiro.avalia(leilao);
		assertThat(leiloeiro.getMaiorLance(), equalTo(450.0));
		assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {

		Leilao leilao = new CriadorDeLeilao().para("PS4 Novo").lance(new Usuario("João"), 1000.0).constroi();

		leiloeiro.avalia(leilao);
		List<Lance> maior = leiloeiro.getTresMaiores();

		assertThat(maior.size(), equalTo(1));
		assertThat(leiloeiro.getMaiorLance(), equalTo(1000.0));
		assertThat(leiloeiro.getMenorLance(), equalTo(1000.0));
	}

	@Test
	public void retornaTresMaioresLances() {

		Usuario maria = new Usuario("Maria");
		Usuario joao = new Usuario("João");

		Leilao leilao = new CriadorDeLeilao().para("PS4 Novo").lance(joao, 250.0).lance(maria, 100.0).lance(joao, 200.0)
				.lance(maria, 400.0).constroi();

		leiloeiro.avalia(leilao);
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
	}

	@Test
	public void lancesAleatoriosTest() {

		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new CriadorDeLeilao().para("XBox One").lance(joao, 120.0).lance(maria, 523.0).lance(joao, 448.0)
				.lance(maria, 469.0).lance(joao, 756.0).lance(maria, 58.0).lance(joao, 985.0).constroi();

		leiloeiro.avalia(leilao);
		assertThat(leiloeiro.getMaiorLance(), equalTo(985.0));
		assertThat(leiloeiro.getMenorLance(), equalTo(58.0));
	}

	@AfterClass
	public static void finish() {
		System.out.println("Fim.");
	}

}
