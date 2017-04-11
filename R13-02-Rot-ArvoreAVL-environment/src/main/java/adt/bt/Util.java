package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		
		if (node.isEmpty()) {
			return null;
		}		
		
		BSTNode<T> retorno = null;

		if (!node.getRight().isEmpty()) {
			BSTNode<T> pivot = (BSTNode<T>) node.getRight();
			
			pivot.setParent(node.getParent());
			node.setParent(pivot);
			pivot.setLeft(node);
			node.setRight(pivot.getLeft());
			
			
			
			 if (node.getParent().getLeft().equals(node)) {
				pivot.getParent().setLeft(pivot);
			}else{
				pivot.getParent().setRight(pivot);
			}
			retorno = pivot;
		}
		return retorno;
		
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		}		
		
		BSTNode<T> retorno = null;

		if (!node.getLeft().isEmpty()) {
			BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
			
			pivot.setParent(node.getParent());
			node.setParent(pivot);
			pivot.getRight().setParent(node);
			node.setLeft(pivot.getRight());
			
			if (node.getParent() == null) {
				node = pivot;
			}
			else if (pivot.getParent().getLeft().equals(node)) {
				pivot.getParent().setLeft(pivot);
			}else{
				pivot.getParent().setRight(pivot);
			}
			retorno = pivot;
		}
		return retorno;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
	
	
}
