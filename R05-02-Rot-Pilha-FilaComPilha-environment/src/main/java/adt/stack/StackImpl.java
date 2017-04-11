package adt.stack;


public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	/**
	 * Acessar o topo da lista
	 */
	@Override
	public T top() {
		if (isEmpty()) {
			return null;
		}
		return array[top];
	}
	

	@Override
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		}return false;
	}

	@Override
	public boolean isFull() {
		return (top+1) == array.length;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (!isFull()) {
			this.top++;

			this.array[top] = element;
		}else{
			throw new StackOverflowException();
		}
	}
	
	
	@Override
	public T pop() throws StackUnderflowException {
		T retorno = null;
		
		if (!isEmpty()) {
			retorno = this.array[top];
			top--;
		}else{
			throw new StackUnderflowException();
		}
		return retorno;
		
		
		
		
	}

}
