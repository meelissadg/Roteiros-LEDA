package vetor;

public class TestarVetor {

	// preencha esse metodo com codigo para testar a classe vetor.
	// À medida que voce evoluir no exercício o conteúdo deste mpetodo
	// também será modificado.

	public static void main(String[] args) {
		Vetor<Aluno> vetor = new Vetor<Aluno>(5);

		Aluno rick = new Aluno("Rick", 5);
		Aluno eleven = new Aluno("Eleven", 7);
		Aluno mike = new Aluno("Mike", 8);
		Aluno negan = new Aluno("Negan", 9);
		
		// true
		System.out.println("O vetor ta vazio? " + vetor.isVazio());
		// false
		System.out.println("O vetor ta cheio? " + vetor.isCheio());

		vetor.inserir(rick);
		vetor.inserir(eleven);
		vetor.inserir(mike);
		vetor.inserir(negan);

		// false
		System.out.println("O vetor ta vazio? " + vetor.isVazio());
		// false
		System.out.println("O vetor ta cheio? " + vetor.isCheio());

		System.out.println();
		
		System.out.println(vetor.procurar(eleven));
		System.out.println(vetor.procurar(mike));
		System.out.println(vetor.procurar(negan));
		System.out.println(vetor.procurar(eleven));
		System.out.println(vetor.procurar(rick));
		System.out.println();
		
		//remove
		System.out.println("Removi " +vetor.remover(eleven));
		//nao acha retorna null
		System.out.println(vetor.procurar(eleven));
		
		System.out.println("Removi " +vetor.remover(mike));
		System.out.println(vetor.procurar(mike));
		System.out.println(vetor.procurar(negan));

		vetor.inserir(new Aluno("Michone", 10));
		vetor.inserir(new Aluno("Carl", 8));
		
		System.out.println();
		System.out.println("O vetor ta vazio? " + vetor.isVazio());
		System.out.println("O vetor ta cheio? " + vetor.isCheio());
		
		//vetor.maximo();
		}
}
