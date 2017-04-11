package recursao;

public class MetodosRecursivos {

	public long calcularFatorial(int n) {
		long result = 1;

		if (n == 0) {
			return result;
		} else {
			return result * calcularFatorial(n - 1);
		}
	}

	public int calcularFibonacci(int n) {
		int result = 1;
		// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR E IMPRIMIR
		// O N-ESIMO TERMO
		// DA SEQUENCIA DE FIBONACCI, QUE TEM A SEGUINTE LEI DE FORMACAO: O
		// PRIMEIRO E SEGUNDO NUMEROS
		// DA SEQUENCIA SÃO 1. A PARTIR DO TERCEIRO TERMO, CADA TERMO DA
		// SEQUENCIA É DADO
		// PELA SOMA DOS OUTROS DOIS ANTERIORES. OBSERVE QUE SENDO O METODO
		// RECURSIVO, O FIBONACCI DOS NUMEROS ANTERIORES TAMBEM VAO SER
		// IMPRESSOS
		return result;
	}

	// recursivo
	private int countNotNull(int i, Object[] array) {
		int cont = 0;

		if (i < array.length) {
			//se for nulo nao conta
			if (array[i] == null) {
				cont += 0 + countNotNull(i + 1, array);
			} else {
				//se for != null conta
				cont += 1 + countNotNull(i + 1, array);
			}
		}
		return cont;
	}

	// sobrecarga
	public int countNotNull(Object[] array) {
		return countNotNull(0, array);
	}

	public long potenciaDe2(int expoente) {
		long result = 1;
		int cont = 0;
		//decrementar o expoente
		
		//caso base
		if (cont == expoente) {
			return result;
		}else{
			result += 2 * potenciaDe2(expoente - 1);
			cont++;
		}
		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = 0;
		// TODO IMPLEMENTE SEU CODIGO (USANDO RECURSAO) DE ENCONTRAR O n-ESIMO
		// TERMO
		// DA PROGRESSAO ARITMETICA, DADO O TERMO INICIAL E A RAZAO
		return result;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = 1;
		// TODO IMPLEMENTE SEU CODIGO (USANDO RECURSAO) DE ENCONTRAR O n-ESIMO
		// TERMO
		// DA PROGRESSAO GEOMETRICA, DADO O TERMO INICIAL E A RAZAO
		return result;
	}
}
