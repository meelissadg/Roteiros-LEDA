package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	
	@Override
	public void insert(T element){
		//se estiver vazio
		if (element == null) {
			return;
		}
		if (isEmpty()) {
			DoubleLinkedListNode<T> novoNo = new DoubleLinkedListNode<>();
			novoNo.data = element;
			novoNo.next = new DoubleLinkedListNode<>();
			novoNo.previous = new DoubleLinkedListNode<>();
			
			super.head = novoNo;
			this.last = novoNo;
			
		}else if (head == last) {
			DoubleLinkedListNode<T> novoNo = new DoubleLinkedListNode<>();
			novoNo.data = element;
			novoNo.next = new DoubleLinkedListNode<>();
			novoNo.previous = (DoubleLinkedListNode<T>) head;
			
			super.head.next = last;
			((DoubleLinkedListNode) last.next).previous = novoNo;
			this.last = novoNo;

		}else{
			DoubleLinkedListNode<T> novoNo = new DoubleLinkedListNode<>();
			novoNo.data = element;
			novoNo.next = new DoubleLinkedListNode<>();
			novoNo.previous = last;
			
			last.next = novoNo;
			((DoubleLinkedListNode)novoNo.next).previous = novoNo;
			this.last = novoNo;
			
		}
	}
	
	
	@Override
	public void insertFirst(T element) {
		if (element == null) {
			return;
		}
		if (isEmpty()) {
			DoubleLinkedListNode<T> novoNo = new DoubleLinkedListNode<>();
			novoNo.data = element;
			novoNo.next = new DoubleLinkedListNode<>();
			novoNo.previous  = new DoubleLinkedListNode<>();
			this.last = novoNo;
			this.head = novoNo;

		}else if (head == last) {
			DoubleLinkedListNode<T> novoNo = new DoubleLinkedListNode<>();
			novoNo.data = element;
			novoNo.next = last;
			novoNo.previous = new DoubleLinkedListNode<>(); 
			novoNo.previous.next = novoNo;
			this.last.previous = novoNo;
			super.head = novoNo;
			
			
		}else{
			DoubleLinkedListNode<T> novoNo = new DoubleLinkedListNode<>();
			novoNo.data = element;
			novoNo.previous = new DoubleLinkedListNode<>();
			novoNo.next = super.head;
			novoNo.previous.next = novoNo;
			((DoubleLinkedListNode)head).previous = novoNo;
			
			super.head = novoNo;
						
		}
		
	}
	
	@Override
	public void remove(T element) {
		DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) head;
		
		while (aux.isNIL() && aux.data != element) {
			aux = (DoubleLinkedListNode<T>) aux.next;
		}
		
		if (aux.data == element) {
			((DoubleLinkedListNode)aux.next).previous = aux.previous;
			aux.previous.next = aux.next;
		}
		
	}

	@Override
	public void removeFirst() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void removeLast() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
