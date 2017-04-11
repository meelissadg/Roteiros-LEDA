package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

/**
 * Este algoritmo eh RECURSIVO e aplica o selectionsort na entrada selectionando o 
 * menor e o maior elemento a cada iteração e colocando eles nas posições corretas. 
 * Nas proximas iterações o espaço de trabalho do algoritmo deve excluir as posiçoes
 * dos elementos das iterações anteriores. 
 */
public class SimultaneousRecursiveSelectionsort<T extends Comparable<T>> extends
		AbstractSorting<T> {
	
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		int novoInicio = 0;
		int novoFim = rightIndex;
		
		if (leftIndex < rightIndex) {
			
			int menor = leftIndex;
			int maior = rightIndex;
			
			for (int i = novoInicio; i < novoFim; i++) {
				
				//buscando o menor
				if (array[i].compareTo(array[menor]) < 0) {
					menor = i;
							
				}if (array[i].compareTo(array[maior]) > 0) {
					maior = i;
				}
			}
		
			Util.swap(array, menor, novoInicio);
			Util.swap(array, maior, novoFim);
			
			novoFim--;
			novoInicio++;
			sort(array, novoInicio, novoFim);
			
		}
	}
	
}
