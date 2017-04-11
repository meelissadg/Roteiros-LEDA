package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node.isEmpty()) {
			return 0;
		}
		return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
	}

	protected void alteraRoot(BSTNode<T> node, BSTNode<T> pivot) {
		if (node == root) {
			root = pivot;
		}
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {

		if (node.isEmpty() || node == null) {
			return;
		}

		int balance = Math.abs(calculateBalance(node));

		if (balance > 1) {
			int balanceF = Math.abs(calculateBalance((BSTNode<T>) node.getLeft()));

			if (balanceF < 0) {
				rightRotation((BSTNode<T>) node.getRight());
			}
			leftRotation(node);
		} else if (balance < -1) {
			int balanceF = Math.abs(calculateBalance((BSTNode<T>) node.getLeft()));

			if (balanceF > 0) {
				leftRotation((BSTNode<T>) node.getLeft());
			}
			rightRotation(node);
		}

	}

	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.leftRotation(node);
		if (newNode.getParent() == null) {
			root = newNode;
		}
	}

	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.rightRotation(node);
		if (newNode.getParent() == null) {
			root = newNode;
		}
	}

	@Override
	public void insert(T element) {
		insert(this.root, element);
		BSTNode<T> node = search(element);
		rebalanceUp(node);
	}

	private void insert(BSTNode<T> aux, T element) {
		
		if (aux.isEmpty()) {
			aux.setData(element);
			aux.setLeft(new BSTNode<T>());
			aux.setRight(new BSTNode<T>());
			aux.getLeft().setParent(aux);
			aux.getRight().setParent(aux);

		} else {
			if (element.compareTo(aux.getData()) < 0) {
				insert((BSTNode<T>) aux.getLeft(), element);
			} else {
				insert((BSTNode<T>) aux.getRight(), element);
			}
			
			rebalance((BSTNode<T>) aux);
		}

	}


	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node.isEmpty() || node == null) {
			return;
		}

		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}

}
