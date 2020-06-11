package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(400, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(250, leiloeiro.getMenorLance(), 0.00001);

	}
	

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("PS4 novo");
		
		leilao.propoe(new Lance(joao, 1000.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		List<Lance> maior = leiloeiro.getTresMaiores();
		
		assertEquals(1, maior.size());
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void retornaTresMaioresLances() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("XBox One");
		
		leilao.propoe(new Lance(maria, 100.0));
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(joao, 400.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		
	}
	
	@Test
	public void lancesAleatoriosTest() {
		
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("XBox One");
		
		leilao.propoe(new Lance(maria, 120.0));
		leilao.propoe(new Lance(joao, 165.0));
		leilao.propoe(new Lance(maria, 680.0));
		leilao.propoe(new Lance(joao, 450.0));
		leilao.propoe(new Lance(maria, 445.0));
		leilao.propoe(new Lance(joao, 770.0));
		leilao.propoe(new Lance(maria, 130.0));
		leilao.propoe(new Lance(joao, 890.0));
		leilao.propoe(new Lance(maria, 680.0));
		leilao.propoe(new Lance(joao, 450.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(890.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.00001);
		
		
		
		
		
	}
	
}
