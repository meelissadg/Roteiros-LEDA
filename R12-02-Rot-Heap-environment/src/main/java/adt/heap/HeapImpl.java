package adt.heap;

import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap eh definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Essa comparação não é feita diretamente com os elementos armazenados,
 * mas sim usando um comparator. Dessa forma, dependendo do comparator, a heap
 * pode funcionar como uma max-heap ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] resp = Util.makeArray(index + 1);
		for (int i = 0; i <= index; i++) {
			resp[i] = this.heap[i];
		}
		return resp;
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		if (position < 0) {
			return;
		}

		int left = left(position);
		int right = right(position);
		int max = position;

		if (left < this.size() && comparator.compare(heap[left], heap[position]) > 0) {
			max = left;
		}
		if (right < this.size() && comparator.compare(heap[right], heap[max]) > 0) {
			max = right;
		}
		if (max != position) {
			Util.swap(heap, position, max);
			heapify(max);
		}
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}

		if (element == null) {
			return;
		}
		
		if (index < heap.length-1) {
			index ++;
			heap[index] = element;
			
			int i = this.index;
			while (i > 0 && this.comparator.compare(this.heap[parent(i)], this.heap[i]) < 0){
				Util.swap(heap, i, parent(i));
				i = parent(i);
			}
		} 
	
	}
	


	@Override
	public void buildHeap(T[] array) {
		
		if (array == null || array.length == 0) {
			return;
		}
		
		heap = (T[]) new Comparable[array.length];
		this.index = -1;

		for (int i = 0; i < array.length; i++) {
			this.insert(array[i]);
		}

		for (int i = array.length / 2; i <= 0; i--) {
			this.heapify(i);
		}
	}

	@Override
	public T extractRootElement() {

		if (index == -1) {
			return null;
		} 
			T max = heap[0];
			Util.swap(heap, 0, index);
			this.index--;
			heapify(0);

			return max;
		}
	

	@Override
	public T rootElement() {
		if (index == -1) {
			return null;
		}
		return heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		//teste de elementos validos
		if (array == null || array.length == 0)
			return array;
		
		
		T[] aux = this.heap;
		int indice = this.index;
		
		Comparator<T> comparador = this.getComparator();
		this.comparator = new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		};
		
		//coloca o maior elemento no topo ou menor no topo
		this.buildHeap(array);
		
		
		T[] retorno = ((T[]) new Comparable[array.length]);
		
		for (int i = array.length - 1; i >= 0; i--){
			retorno[i] = this.extractRootElement();
			//vai colocando o root no array
		}
		
		this.heap = aux;
		this.index = indice;
		this.comparator = comparador;
		
		return retorno;
	}

	@Override
	public int size() {
		return index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
