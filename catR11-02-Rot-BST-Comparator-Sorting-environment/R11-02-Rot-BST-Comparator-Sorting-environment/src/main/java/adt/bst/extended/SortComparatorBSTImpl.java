package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em
 * suas funcionalidades e possui um metodo de ordenar um array dado como
 * parametro, retornando o resultado do percurso desejado que produz o array
 * ordenado.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {
	private Comparator<T> comparator;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}
	
	
	@Override
	public T[] sort(T[] array) {
		return array;
		
	}

	@Override
	public T[] reverseOrder() {
		T[] result = (T[]) new Comparable[size()];
		ArrayList<T> aux = new ArrayList<>();
		
		this.reverseOrder(this.root, aux);
		return aux.toArray(result);
	}

	private void reverseOrder(BSTNode<T> node, ArrayList<T> aux) {
		if (node.isEmpty()) {
			return;
		}
		reverseOrder((BSTNode<T>) node.getRight(), aux);
		aux.add(node.getData());
		reverseOrder((BSTNode<T>) node.getLeft(), aux);
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element != null) {
			return search(this.root, element);
		} else {
			return new BSTNode<T>();
		}

	}

	/**
	 * Metodo usado na recursao do search() retorna NIL se o elemento nao
	 * existir
	 * 
	 * @param node
	 * @param element
	 * @return BSTNode
	 */
	private BSTNode<T> search(BSTNode<T> node, T element) {

		if (node.isEmpty()) {
			return node;
			
		}else if(comparator.compare(element, node.getData()) == 0) {
			return node;
		}
		else if (comparator.compare(element, node.getData()) < 0) {
			return search((BSTNode<T>) node.getLeft(), element);

		} else {
			return search((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public void insert(T element) {
		if (element == null) {
			return;
		}
		insert(this.getRoot(), element);
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);

		} else {

			if (comparator.compare(element, node.getData()) > 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			} else {
				insert((BSTNode<T>) node.getRight(), element);
			}
		}
	}

}
