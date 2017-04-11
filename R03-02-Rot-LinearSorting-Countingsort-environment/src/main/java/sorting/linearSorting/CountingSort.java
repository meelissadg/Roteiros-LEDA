package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	
	private int retornaMaior(Integer[] array, int leftIndex, int rightIndex){
		int maior = array[leftIndex];
		
		
		//o primeiro eh maior
		for (int i = leftIndex+1; i <= rightIndex; i++) {
			if (array[i] >= maior ) {
				maior = array[i];
			}
		}
		return maior;
	}
	
	
	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if (leftIndex < rightIndex && leftIndex >= 0 && array[leftIndex] != null) {
			
			int maior = retornaMaior(array, leftIndex, rightIndex);
			
			int[] c = new int[maior+1];
			
			Integer[] ordenado = new Integer[rightIndex+1];
			
			
			//varrendo o array do inicio ao fim
			for (int i = leftIndex; i <= rightIndex; i++) {
				c[array[i]]++;
			}
			
			//somando
			for (int j = 1; j < c.length; j++) {
				c[j] += c[j-1];
			}
			
			//alocando no array B
			
			for (int o = leftIndex; o <= rightIndex ; o++) {
				int valorArray = array[o];
				int valorC = c[valorArray] - 1;
				ordenado[valorC] = valorArray;
				c[valorArray]--;
			}
			
			for (int i = 0; i < ordenado.length; i++) {
				array[i] = ordenado[i];
			}
			
		}
		}
		

}