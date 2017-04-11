package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int meio;
		
		if ((array != null && leftIndex >= 0 && rightIndex <= array.length)){
			if (leftIndex < rightIndex) {
				meio = (leftIndex + rightIndex) / 2;

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

	}

}
