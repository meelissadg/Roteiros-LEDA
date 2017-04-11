package sorting.divideAndConquer.hybridMergesort;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes: - Ter
 * contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 * que essa informação possa ser capturada pelo teste. - A cada chamado do
 * método de sort(T[] array) esses contadores são resetados. E a cada chamada
 * interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 * INSERTIONSORT_APPLICATIONS são incrementados. - O InsertionSort utilizado no
 * algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		int limite, meio;

		this.INSERTIONSORT_APPLICATIONS = 0;
		this.MERGESORT_APPLICATIONS = 0;
		
		if ((array != null && leftIndex >= 0 && rightIndex <= array.length && leftIndex < rightIndex)) {

			limite = rightIndex - leftIndex;
			meio = (rightIndex + leftIndex) / 2;

			if (limite <= SIZE_LIMIT) {
				insertion(array, leftIndex, rightIndex);
			}else{
				sort(array, leftIndex, meio);
				sort(array, meio + 1, rightIndex);
				merge(array, leftIndex, meio, rightIndex);
			}

		}
	}
	
	public void merge(T[] array, int inicio, int meio, int fim) {
		T[] aux = Arrays.copyOf(array, array.length);

		boolean terminou = false;
		
		int indiceInicio = inicio;
		int indiceMeio = meio + 1;
		int indiceOriginal = inicio;

		while (indiceInicio <= meio && indiceMeio <= fim) {

			if (aux[indiceInicio].compareTo(aux[indiceMeio]) < 0) {
				array[indiceOriginal] = aux[indiceInicio];
				indiceInicio++;
			} else {
				array[indiceOriginal] = aux[indiceMeio];
				indiceMeio++;
			}

			indiceOriginal++;
			
			if (indiceInicio > meio) {
				terminou = true;
			}
			
		}

		if (terminou) {
			for (int i = indiceMeio; i <= fim; i++, indiceOriginal++) {
				array[indiceOriginal] = aux[i];
			}
		} else {
			for (int i = indiceInicio; i <= meio; i++, indiceOriginal++) {
				array[indiceOriginal] = aux[i];
			}
		}
		
		this.INSERTIONSORT_APPLICATIONS++;

	}

	private void insertion(T[] array, int leftIndex, int rightIndex) {

		T aux;

		for (int i = leftIndex + 1; i <= rightIndex; i++) {

			int j = i;

			while (j > leftIndex && array[j].compareTo(array[j - 1]) < 0) {
				aux = array[j];
				array[j] = array[j - 1];
				array[j - 1] = aux;

				j--;
			}

		}this.MERGESORT_APPLICATIONS ++;
	}

}
