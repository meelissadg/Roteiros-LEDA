package adt.btree;

import java.util.ArrayList;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

		
	@Override
	public int height() {
		if (isEmpty()) {
			return -1;
		}
		return height(this.root, 0);
	}

	private int height(BNode<T> node, int altura) {
		if (node.isLeaf()) {
			return altura++;
		} else {
			altura++;
			return height(node.getChildren().get(0), altura);

		}
	}


	@Override
	public BNode<T>[] depthLeftOrder() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}
	
	@Override
	public int size() {
		return size(this.root, 0);

	}

	private int size(BNode<T> node, int tamanho) {
		if (node.getChildren().size() == 0) {
			return tamanho += node.getElements().size();
		} else {

			tamanho += node.getElements().size();

			for (int i = 0; i < node.getChildren().size(); i++) {
				return size(node.getChildren().get(i), tamanho);
			}
		}
		return tamanho;

	}

	@Override
	public BNodePosition<T> search(T element) {
		return search(this.root, element);
	}

	private BNodePosition<T> search(BNode<T> node, T element) {
		int posicao = 0;
		while(posicao < node.size() && element.compareTo(node.getElementAt(posicao)) < 0) {
			posicao++;
		}
		if(posicao < node.size() && element.equals(node.getElementAt(posicao))) {
			return new BNodePosition<T>(node, posicao);
		}
		if(node.isLeaf()) {
			return new BNodePosition<T>(null, -1);
		}
		return search(node.getChildren().get(posicao), element);
	
	}

	@Override
	public void insert(T element) {
		insert(this.root, element);
	}

	private void insert(BNode<T> node, T element) {
		int posicao = 0;
		
		while(posicao < node.size() && element.compareTo(node.getElementAt(posicao)) < 0) {
			posicao++;
		}
		if(node.isLeaf()) {
			node.addElement(element);
			
			while(node.size() == node.getOrder() && !node.equals(this.root)) {
				split(node);
				node = node.getParent();
			}
			
			if(node.size() == node.getOrder() && node.equals(this.root)){
				split(node);
				this.root = node.getParent();
			}
		} else {
			insert(node.children.get(posicao), element);
		}
	}

	private void split(BNode<T> node) {
		node.split();
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
