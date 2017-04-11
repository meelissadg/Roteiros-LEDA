package orderStatistic;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * -
 
  Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		
		T[] retorno = (T[]) new Comparable[array.length - k];
		int x = 0;
		T ordem = orderStatistics(array, 3);
		

		int cont = 1;
		int maior = k;
		
		
		for (int i = k; i < array.length; i++) {
			maior = i;

			for (int j = i + 1; j < array.length; j++) {
				if (array[j].compareTo(array[maior])<0) {
					maior = j;
				}

			}
			Util.swap(array, maior, i);
			cont++;
		}

		for (int i = (array.length - 1); i >= retorno.length; i--) {
			retorno[x] = array[i];
			x++;
		}

		return retorno;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		
		int comeco = 0;
		int cont = 1;
		int menor = 0;

		if (k <= array.length) {
			for (int i = comeco; i < array.length && cont < k; i++) {
				menor = i;

				for (int j = i + 1; j < array.length; j++) {
					if (array[j].compareTo(array[menor]) <= 0) {
						menor = j;
					}

				}
				Util.swap(array, menor, i);
				cont++;
			}

			return array[k - 1];
		}
		return null;

	}
}
