package vetor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestarVetor {
	
	private Aluno maria;
	private Aluno joao;
	private Aluno pedro;
	private Aluno ana;
	private Vetor<Aluno> vetor;
	
	@Before
	public void setUp(){
		this.vetor = new Vetor<>(4);
		
		this.ana = new Aluno("Ana", 5);
		this.joao = new Aluno("Joao", 6);
		this.maria = new Aluno("Maria", 8);
		this.pedro = new Aluno("Pedro", 9);
	}
	

	@Test
	public void testInserir() {
		assertTrue(vetor.isVazio());
		assertFalse(vetor.isCheio());
		
		vetor.inserir(joao);
		vetor.inserir(pedro);

		assertEquals(1, vetor.getIndice());
		assertFalse(vetor.isCheio());
		assertFalse(vetor.isVazio());

		
	}
	@Test
	public void testRemover() {
		vetor.inserir(maria);
		vetor.inserir(joao);
		assertEquals(1, vetor.getIndice());

		vetor.remover(maria);
		assertEquals(0, vetor.getIndice());

	}

	@Test
	public void testProcurar() {
		vetor.inserir(maria);
		vetor.inserir(joao);
		
		assertEquals(joao, vetor.procurar(joao));
		assertEquals(null, vetor.procurar(pedro));

	}

	@Test
	public void testIsVazio() {
		assertTrue(vetor.isVazio());
		vetor.inserir(joao);
		assertFalse(vetor.isVazio());

	}

	@Test
	public void testIsCheio() {
		assertFalse(vetor.isCheio());
		vetor.inserir(maria);
		vetor.inserir(joao);
		vetor.inserir(pedro);
		vetor.inserir(ana);
		assertTrue(vetor.isCheio());

	}

	
	@Test
	public void testMaximo() {
		vetor.inserir(ana);
		vetor.inserir(joao);
		assertEquals(joao, vetor.maximo());

	}

	@Test
	public void testMinimo() {
		
		vetor.inserir(ana);
		vetor.inserir(joao);
		
		assertEquals(ana, vetor.minimo());
	}

}
