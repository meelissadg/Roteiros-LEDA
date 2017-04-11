package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(getRoot());
	}

	/**
	 * Metodo usado na recursao do height()
	 * 
	 * @param node
	 * @return
	 */
	private int height(BSTNode<T> node) {
		int result = 1;

		if (node.isEmpty()) {
			return -1;
		} else {
			int heightLeft = height((BSTNode<T>) node.getLeft());
			int heightRight = height((BSTNode<T>) node.getRight());

			if (heightLeft < heightRight) {
				result = result + heightRight;
			} else {
				result = result + heightLeft;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element != null) {
			return search(root, element);
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
			return new BSTNode<T>();

		} else if (element.compareTo(node.getData()) == 0) {
			return node;

		} else if (element.compareTo(node.getData()) < 0) {
			return search((BSTNode<T>) node.getLeft(), element);

		} else {
			return search((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public void insert(T element) {
		insert(this.root, element);
	}

	private void insert(BSTNode<T> aux, T element) {

		if (element != null) {

			if (aux.isEmpty()) {
				aux.setData(element);
				aux.setLeft(new BSTNode<T>());
				aux.setRight(new BSTNode<T>());
				aux.getLeft().setParent(aux);
				aux.getRight().setParent(aux);

			} else {
				if (aux.getData().compareTo(element) > 0) {
					insert((BSTNode<T>) aux.getLeft(), element);
				} else {
					insert((BSTNode<T>) aux.getRight(), element);
				}
			}

		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(getRoot());
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;

		} else if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		}
		if (!node.getLeft().isEmpty()) {
			return minimum((BSTNode<T>) node.getLeft());
		} else {
			return node;
		}
	}

	
	  @Override
	   public BSTNode<T> sucessor(T element) {
	      if (element == null) {
	         return null;
	      }
	      BSTNode<T> resultado = null;
	      BSTNode<T> node = search(element);

	      if (node.isEmpty()) {
	         return null;
	      }
	      if (!node.getRight().isEmpty()) {
	         resultado = minimum((BSTNode<T>) node.getRight());
	      } else {
	         resultado = (BSTNode<T>) node.getParent();

	         while (resultado != null && node.equals((BSTNode<T>) resultado.getRight())) {
	            node = resultado;
	            resultado = (BSTNode<T>) resultado.getParent();

	         }
	      }
	      return resultado;
	   }


	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> resultado = null;
		BSTNode<T> node = search(element);

		if (node.isEmpty() || node == null) {
			return null;
		}
		if (node.getParent().isEmpty()) {
			return null;
		}
		if (!node.getLeft().isEmpty()) {
			resultado = maximum((BSTNode<T>) node.getLeft());
		} else {
			resultado = (BSTNode<T>) node.getParent();

			while (resultado != null && node.equals(resultado.getLeft())) {
				node = resultado;
				resultado = (BSTNode<T>) resultado.getParent();

			}
		
		}
		return resultado;

	}


	   private void grauUm(BSTNode<T> node) {

	      //se for a raiz
	      if (node.getParent() == null || node.getParent().isEmpty()) {

	         if (!node.getRight().isEmpty()) {
	            node.getRight().setParent(null);
	            this.root = (BSTNode<T>) node.getRight();
	            return;
	         } else {
	            node.getLeft().setParent(null);
	            this.root = (BSTNode<T>) node.getLeft();
	            return;
	         }
	      } else {
	         //se nao for a raiz
	         BSTNode<T> aux = null;

	         if (!node.getRight().isEmpty()) {
	            aux = (BSTNode<T>) node.getRight();
	         } else {
	            aux = (BSTNode<T>) node.getLeft();
	         }

	         aux.setParent(node.getParent());
	         if (node.getParent().getRight().equals(node)) {
	            node.getParent().setRight(aux);
	         } else {
	            node.getParent().setLeft(aux);
	         }
	      }

	   }

	   private void grauDois(BSTNode<T> node) {
	      BSTNode<T> sucessor = sucessor(node.getData());
	      if (sucessor == null) {
	         return;
	      }

	      // o sucessor agora eh o node
	      int qtsFilhos = retornaQuantFilhos(sucessor);
	      node.setData(sucessor.getData());

	      if (qtsFilhos == 0) {
	         grauZero(sucessor);
	      } else if (qtsFilhos == 1) {
	         grauUm(sucessor);
	      } else {
	         grauDois(sucessor);
	      }

	   }

	   @Override
	   public void remove(T element) {
	      if (element == null) {
	         return;
	      }

	      BSTNode<T> node = search(element);

	      if (node.isEmpty()) {
	         return;
	      }
	      int quantFilhos = retornaQuantFilhos(node);

	      if (quantFilhos == 0) {
	         grauZero(node);

	      } else if (quantFilhos == 1) {
	         grauUm(node);
	      } else {
	         grauDois(node);
	      }

	   }

	   private void grauZero(BSTNode<T> node) {
	      node.setData(null);

	   }

	   private int retornaQuantFilhos(BSTNode<T> node) {
	      int retorno = 0;

	      if (!node.getRight().isEmpty()) {
	         retorno++;
	      }
	      if (!node.getLeft().isEmpty()) {
	         retorno++;
	      }
	      return retorno;

	   }

	@Override
	public T[] preOrder() {
		T[] result = (T[]) new Comparable[size()];
		ArrayList<T> array = new ArrayList<T>();
		preOrder(this.root, array);
		return array.toArray(result);
	}

	private void preOrder(BSTNode<T> node, ArrayList<T> arrayAux) {

		if (!node.isEmpty()) {
			arrayAux.add(node.getData());
			preOrder((BSTNode<T>) node.getLeft(), arrayAux);
			preOrder((BSTNode<T>) node.getRight(), arrayAux);

		}
	}


	   @Override
	   public T[] order() {
	      T[] result = (T[]) new Comparable[size()];
	      ArrayList<T> arrayAux = new ArrayList<T>();
	      order(this.getRoot(), arrayAux);
	      return arrayAux.toArray(result);
	   }

	   private void order(BSTNode<T> node, ArrayList<T> array) {
	      if (!node.isEmpty()) {
	         order((BSTNode<T>) node.getLeft(), array);
	         array.add(node.getData());
	         order((BSTNode<T>) node.getRight(), array);
	      }
	   }

	@Override
	public T[] postOrder() {
		T[] result = (T[]) new Comparable[size()];
		ArrayList<T> arrayAux = new ArrayList<T>();
		posOrder(this.getRoot(), arrayAux);
		return arrayAux.toArray(result);
	}

	private void posOrder(BSTNode<T> node, ArrayList<T> arrayAux) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), arrayAux);
			order((BSTNode<T>) node.getRight(), arrayAux);
			arrayAux.add(node.getData());

		}

	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
