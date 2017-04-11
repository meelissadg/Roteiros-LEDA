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
		
		this.comparadorMaximo = (Comparator<T>) new ComparadorMax();
		this.comparadorMinimo = (Comparator<T>) new ComparadorMin();
	}

	public void setComparadorMaximo(Comparator comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T o) {
		this.arrayInterno[indice + 1] = o;
		indice++;
	}

	// Remove um objeto do vetor
	public T remover(T o) {
		T objRetorno = null;
		boolean achei = false;

		if (!isVazio() && !achei) {
			// fazer metodo que retorna o indice
			// fazer trazendo pra ca
			for (int i = 0; i <= indice; i++) {
				if (arrayInterno[i].equals(o)) {
					objRetorno = o;
					achei = true;
					this.arrayInterno[i] = arrayInterno[indice];
					this.arrayInterno[indice] = null;
					this.indice--;
					
				}
			}
		}
		return objRetorno;
	}

	// Procura um elemento no vetor
	public T procurar(T o) {
		T objRetorno = null;

		if (indice != -1) {
			for (int i = 0; i <= indice; i++) {
				if (arrayInterno[i].equals(o)) {
					objRetorno = arrayInterno[i];
				}
			}
		}
		return objRetorno;
	}

	public int getIndice() {
		return indice;
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		if (indice == -1) {
			return true;
		}
		return false;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		if (arrayInterno[tamanho - 1] != null) {
			return true;
		} else {
			return false;
		}
	}

	
	public T maximo(){
		T max = null;
		
		if (!isVazio()) {

			max = arrayInterno[0];
			
			for (int i = 0; i <= indice; i++) {
				if (comparadorMaximo.compare(max, arrayInterno[i]) < 0) {
					max = arrayInterno[i];
				}
		}
	}
		return max;
	}
	
	public T minimo(){
		T minimo = null;
		
		if (!isVazio()) {
			minimo = arrayInterno[0];

			for (int i = 0; i <= indice; i++) {
				if (comparadorMinimo.compare(minimo, arrayInterno[i]) < 0) {
					minimo = arrayInterno[i];
				}
		}
	}
		return minimo;
	}
	
}

class ComparadorMax implements Comparator<Aluno> {

	@Override
	public int compare(Aluno a1, Aluno a2) {
		int resultado = (int) (a1.getMedia() - a2.getMedia());
		return resultado;
	}

}

class ComparadorMin implements Comparator<Aluno> {

	@Override
	public int compare(Aluno a1, Aluno a2) {
		int resultado = (int) (a2.getMedia() - a1.getMedia());
		return resultado;
	}

}