package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int tamanho = 0;
		SingleLinkedListNode<T> aux = head;

		while (!aux.isNIL()) {
			tamanho++;
			aux = aux.next;
		}
		return tamanho;

	}

	@Override
	public T search(T element) {
		T retorno = null;
		SingleLinkedListNode<T> aux = head;

		if (!isEmpty()) {

			while (aux.data != element && !aux.isNIL()) {
				aux = aux.next;
			}
		}
		
		if (aux.data == element) {
			retorno = aux.data;
			
		}
		
		return retorno;
	}

	@Override
	public void insert(T element) {
		
		SingleLinkedListNode<T> aux = head;
		
		if (isEmpty()) {
			//cria o novo no
			//com o element 
			head = new SingleLinkedListNode<T>(element, aux);
		}else{
			while (!aux.next.isNIL()) {
				aux = aux.next;
			}
			
			SingleLinkedListNode novoNo = new SingleLinkedListNode<>(element, aux.next);
			aux.next = novoNo;
			
		}
	}

	@Override
	public void remove(T element) {
		SingleLinkedListNode<T> aux = head;
		
		if (aux.data != element) {
			
			SingleLinkedListNode antes = new SingleLinkedListNode<>();
			 
			while (aux.data != element && !aux.isNIL()) {
				antes = aux;
				aux = aux.next;
			}
			
			if (!aux.isNIL()) {
				antes.next = aux.next;
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] retorno = (T[]) new Object[size()];
		SingleLinkedListNode<T> aux = head;
		int cont = 0;
		
		while (!aux.isNIL()) {
			retorno[cont] = aux.data;
			cont++;
			aux = aux.next;
		}
		
		return retorno;
		
		
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
