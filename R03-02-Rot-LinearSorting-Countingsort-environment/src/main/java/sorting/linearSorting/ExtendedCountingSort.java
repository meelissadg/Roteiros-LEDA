package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	private int retornaMenor(Integer[] array, int leftIndex, int rightIndex) {
		int menor = 0;

		if (array.length != 0) {

			menor = array[leftIndex];

			for (int i = leftIndex; i <= rightIndex; i++) {
				if (array[i] <= menor) {
					menor = array[i];
				}
			}
		}
		return menor;
	}

	private int retornaMaior(Integer[] array, int leftIndex, int rightIndex) {
		int maior = 0;

		if (array.length != 0) {
			maior = array[leftIndex];
			for (int i = leftIndex + 1; i <= rightIndex; i++) {
				if (array[i] >= maior) {
					maior = array[i];
				}
			}
		}

		return maior;
	}

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		if (leftIndex < rightIndex && leftIndex >= 0 && array[leftIndex] != null) {

			int maior = retornaMaior(array, leftIndex, rightIndex);
			int menor = retornaMenor(array, leftIndex, rightIndex);

			int[] aux = new int[(maior - menor) + 1];
			Integer[] ordenado = new Integer[rightIndex - leftIndex + 1];

			if (menor >= 0) {

				// varrendo o array do inicio ao fim
				for (int i = leftIndex; i <= rightIndex; i++) {
					int valorArray = array[i];
					aux[valorArray - menor] += 1;
				}

				// somando
				for (int j = 1; j < aux.length; j++) {
					aux[j] = aux[j] + aux[j - 1];
				}

				// alocando no array B

				for (int o = leftIndex; o <= rightIndex; o++) {
					int valorArray = array[o];
					int valorC = aux[valorArray - menor] - 1;

					ordenado[valorC] = array[o];
					aux[valorArray - menor] -= 1;

				}
			}else{
				
				// varrendo o array do inicio ao fim
				for (int i = leftIndex; i <= rightIndex; i++) {
					int valorArray = array[i];
					aux[valorArray + Math.abs(menor)] += 1;
				}

				// somando
				for (int j = 1; j < aux.length; j++) {
					aux[j] = aux[j] + aux[j - 1];
				}

				// alocando no array B

				for (int o = leftIndex; o <= rightIndex; o++) {
					int valorArray = array[o];
					int valorC = aux[valorArray] + Math.abs(menor);
					ordenado[valorC-1] = array[o];
					
					aux[valorC] -= 1;
				}
				
			}

			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = ordenado[i];
			}
		}
	}
}
