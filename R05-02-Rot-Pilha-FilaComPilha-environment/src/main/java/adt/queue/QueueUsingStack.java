package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {

		
		if (!stack1.isFull()) {
			while (!stack1.isEmpty()) {

				T elemento = stack1.top();
				try {
					stack2.push(elemento);
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
				try {
					stack1.pop();
				} catch (StackUnderflowException e) {
					e.printStackTrace();
				}

			}

			try {
				stack1.push(element);
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}

			while (!stack2.isEmpty()) {
				T elemento = stack2.top();
				try {
					stack1.push(elemento);
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
				try {
					stack2.pop();
				} catch (StackUnderflowException e) {
					e.printStackTrace();
				}
			}
		}else{
			throw new QueueOverflowException();
		}
		
	
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T elemento = head();

		if (!stack1.isEmpty()) {
			while (stack1.top() != elemento) {

				try {
					stack2.push(stack1.top());
				} catch (StackOverflowException e1) {
					e1.printStackTrace();
				}
				try {
					stack1.pop();
				} catch (StackUnderflowException e) {
					e.printStackTrace();
				}
			}
			
			
			while (!stack2.isEmpty()) {
				T novoElemento = stack2.top();
					try {
						stack1.push(novoElemento);
					} catch (StackOverflowException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						stack2.pop();
					} catch (StackUnderflowException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		} else {
			throw new QueueUnderflowException();
		}

		return head();
	}

	@Override
	public T head() {
		if (!stack1.isEmpty()) {
			return stack1.top();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
