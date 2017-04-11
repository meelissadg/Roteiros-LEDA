package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if (array != null && leftIndex >= 0  && rightIndex <= array.length) {
			if (leftIndex < rightIndex) {
				
				int pivot = particao(array, leftIndex, rightIndex);
				sort(array, leftIndex, pivot-1);
				sort(array, pivot+1, rightIndex);
			}
		}
		
	}
	
	
	public int particao(T[] array, int leftIndex, int rightIndex){
		
		T pivot = array[rightIndex];
		int indicePivot = leftIndex - 1;
		
		for (int i = leftIndex; i <= rightIndex-1; i++) {
			
			if (array[i].compareTo(pivot) <= 0) {
				indicePivot ++ ;
				Util.swap(array, indicePivot, i);
			}
		}
		Util.swap(array, indicePivot+1,rightIndex);
		return indicePivot+1;
	}
}
