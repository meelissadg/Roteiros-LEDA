package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	/**
	 * Acessa o elemento da cabeca
	 */
	@Override
	public T head() {
		if (!isEmpty()) {
			return this.array[0];
		}
		return null;

	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return tail + 1 == array.length;
	}

	private void shiftLeft() {
		if (!isEmpty()) {

			for (int i = 0; i < tail; i++) {
				array[i] = array[i + 1];
			}
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (!isFull()) {
			this.tail++;
			this.array[tail] = element;
		} else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T retorno = null;
		
		if (!isEmpty()) {
			retorno = this.head();
			this.shiftLeft();
			this.tail--;
		}
		return retorno;
	}

}
