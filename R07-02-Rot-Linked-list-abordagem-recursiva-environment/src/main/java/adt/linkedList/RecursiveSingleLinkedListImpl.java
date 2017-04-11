package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		if (this.data == null) {
			return true;
		}return false;
	}

	@Override
	public int size() {
		
		if (isEmpty()) {
			return 0;
		}
		else{
			return 1 + this.next.size();
		}
	}

	@Override
	public T search(T element) {
		
		if (isEmpty()) {
			return null;
		}else{
			if (data == element) {
				return this.data;
			}else{
				return this.next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
				this.next = new RecursiveSingleLinkedListImpl<>();
			}else{
				this.next.insert(element);
			}
		}
		
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (this.data == element) {
				this.data = next.data;
				next = next.next;
			}else{
				this.next.remove(element);
			}
		}
	}
	
	
	@Override
	public T[] toArray() {
		
		int contador = 0;
		T[] result = (T[]) new Object[this.size()];
		if(this.size() == 0) {
			return result;
		}
		toArray(result, this, contador);
		return result;
	}
	
	private void toArray(T[] result, RecursiveSingleLinkedListImpl<T> node, int contador) {

		while(!node.isEmpty()) {
			result[contador] = node.data;
			contador++;
			toArray(result, node.next, contador);
			return;
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
