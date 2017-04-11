package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T> aux = root;
		SkipListNode<T>[] lista = new SkipListNode[maxHeight];

		for (int i = maxHeight - 1; i >= 0; i--) {
			while (aux.forward[i].getKey() < key) {
				aux = aux.forward[i];
			}

			lista[i] = aux;
		}

		if (aux.forward[0].getKey() == key) {
			aux.forward[0].setValue(newValue);
		} else {
			aux = new SkipListNode<T>(key, height, newValue);

			for (int i = 0; i < height; i++) {
				aux.forward[i] = lista[i].forward[i];
				lista[i].forward[i] = aux;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T> aux = this.root;
		SkipListNode<T>[] lista = new SkipListNode[maxHeight];

		for (int i = maxHeight - 1; i >= 0; i--) {
			while (aux.forward[i].getKey() < key) {
				aux = aux.forward[i];
			}

			lista[i] = aux;
		}
		aux = aux.forward[0];
		if (aux.getKey() == key) {

			int indice = 0;
			while (indice < maxHeight && lista[indice].forward[indice] == aux) {
				lista[indice].forward[indice] = aux.forward[indice];
				indice++;

			}
		}
	}

	@Override
	public int height() {
		int altura = maxHeight - 1;

		while (root.forward[altura].getKey() == Integer.MAX_VALUE) {
			altura--;
		}

		if (root.forward[0].getKey() == Integer.MAX_VALUE) {
			return 0;
		}

		return altura + 1;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> aux = root;

		for (int i = maxHeight - 1; i >= 0; i--) {
			while (aux.forward[i].getKey() < key) {
				aux = aux.forward[i];
			}
		}

		if (aux.forward[0].getKey() == key) {
			return aux.forward[0];
		} else {
			return null;
		}
	}

	@Override
	public int size() {
		int size = 0;

		SkipListNode<T> node = root.forward[0];

		while (node.getKey() != Integer.MAX_VALUE) {
			size++;
			node = node.forward[0];
		}

		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T> aux = root;
		SkipListNode<T>[] lista = new SkipListNode[size() + 2];
		int indice = 0;

		while (indice < size() + 2) {
			lista[indice] = aux;
			aux = aux.forward[0];
			indice++;
		}

		return lista;

	}

}
