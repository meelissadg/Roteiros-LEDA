package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T extends Comparable<T>> {

	// O array interno onde os objetos manipulados são guardados
	private T[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator<T> comparadorMaximo;
	private Comparator<T> comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		this.arrayInterno = (T[]) new Comparable[tamanho];
		this.tamanho = tamanho;
		this.indice = -1;
	}

	public void setComparadorMaximo(Comparator comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T o) {
		this.indice++;

		if (!isCheio()) {
			this.arrayInterno[indice] = o;
		}
	}

	private int retornaIndice(T o) {
		int retorno = -1;

		for (int i = 0; i <= indice; i++) {
			if (arrayInterno[i].equals(o)) {
				retorno = i;
			}
		}
		return retorno;
	}

	// Remove um objeto do vetor
	public T remover(T o) {
		T objRetorno = null;
		int indiceObj = retornaIndice(o);

		if (indiceObj != -1) {
			objRetorno = o;

			for (int i = indiceObj; i <= indice; i++) {
				arrayInterno[indiceObj] = arrayInterno[indiceObj + 1];
				indiceObj++;

			}
		}

		this.indice--;
		return objRetorno;
	}

	// Procura um elemento no vetor
	public T procurar(T o) {
		T objRetorno = null;
		int indiceObj = retornaIndice(o);

		if (indiceObj != -1) {
			objRetorno = arrayInterno[indiceObj];
		}
		return objRetorno;

	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		return this.indice == -1;

	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		return this.indice == tamanho - 1;
	}

	public T maximo() {
		T max = null;
		if (!isVazio()) {

			max = arrayInterno[0];

			for (int i = 0; i <= indice; i++) {
				if (comparadorMaximo.compare(max, arrayInterno[i]) < 0) {
					max = arrayInterno[i];
				}
			}
			return max;
		}
		throw new RuntimeException("O array estah vazio.");
	}

	public T minimo() {
		T minimo = arrayInterno[0];

		if (arrayInterno[0] != null) {

			for (int i = 0; i <= indice; i++) {
				if (arrayInterno[i] != null && comparadorMinimo.compare(minimo, arrayInterno[i]) < 0) {
					minimo = arrayInterno[i];
				}
			}
		}
		return minimo;
	}

}

class ComparadorMaximo implements Comparator<Aluno> {

	@Override
	public int compare(Aluno a1, Aluno a2) {
		int resultado = (int) (a1.getMedia() - a2.getMedia());
		return resultado;
	}

}

class ComparadorMinimo implements Comparator<Aluno> {

	@Override
	public int compare(Aluno a1, Aluno a2) {
		int resultado = (int) (a2.getMedia() - a1.getMedia());
		return resultado;
	}

}
