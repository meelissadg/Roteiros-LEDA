package adt.hashtable.closed;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size. For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
			// the immediate prime
			// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		int valor = number;

		if (valor % 2 == 0) {
			valor++;
		}
		while (!Util.isPrime(valor)) {
			valor++;
		}
		return valor;
	}

	@Override
	public void insert(T element) {
		
		if (verificaElementoValido(element)) {

		int posicao = getPosicao(element);

		// se estiver vazio
		if (table[posicao] == null) {
			LinkedList<T> novaLista = new LinkedList<>();
			table[posicao] = novaLista;
			novaLista.add(element);
			elements++;

		} else {
			
			boolean contemChave = ((LinkedList<T>) table[posicao]).contains(element);

			if (!contemChave) {

				((LinkedList<T>) table[posicao]).add(element);
				COLLISIONS++;
				elements++;
			}
		}

	   }

	}

	
	/**
	 * Verifica validade do elemento passado como parametro
	 * @param element
	 * @return boolean
	 */
	private boolean verificaElementoValido(T element) {
		if (element != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Retorna a posicao na tabela usando a funcao hash
	 * @param element
	 * @return inteiro 
	 */
	private int getPosicao(T element){
		return ((HashFunctionClosedAddress<T>) hashFunction).hash(element);
	}

	@Override
	public void remove(T element) {

		if (verificaElementoValido(element) && !isEmpty()) {
			// o elemento ta na table
			if (search(element) != null) {
				
				int posicao = getPosicao(element);
				// remove o elemento dessa posicao
				((LinkedList<T>) table[posicao]).remove();

				if (((LinkedList<T>) table[posicao]).contains(element)) {
					COLLISIONS--;
				}

				elements--;

			}

		}

	}

	@Override
	public T search(T element) {

		T resultado = null;

		if (isEmpty() && verificaElementoValido(element)) {
			return null;
		} else {

			int posicao = getPosicao(element);

			if (table[posicao] != null) {

				if (((LinkedList<T>) table[posicao]).contains(element)) {
					resultado = element;
				}
			}

		}

		return resultado;

	}

	@Override
	public int indexOf(T element) {
		int indice = -1;

		if (element == null) {
			return -1;
		}
		if (search(element) != null) {
			indice = getPosicao(element);
		}
		return indice;
	}
}
