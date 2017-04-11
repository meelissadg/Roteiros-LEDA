package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int menor = 0;
		
		for (int i = 0; i < array.length; i++) {
			menor = i;
			
			for (int j = i; j < array.length; j++) {
				
				if (array[j].compareTo(array[menor]) < 0) {
					menor = j;
				}
			}
			Util.swap(array, menor, i);
			
		}
	}
}
