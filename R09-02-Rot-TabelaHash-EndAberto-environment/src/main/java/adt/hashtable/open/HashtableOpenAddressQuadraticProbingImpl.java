package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
      super(size);
      hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
      int probe = 0;
      int posicao = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

      while (probe < table.length) {

         if (table[posicao] instanceof DELETED || table[posicao] == null) {
            table[posicao] = element;
            this.elements++;
            return;
         } else {
            probe++;
            COLLISIONS++;
         }
         posicao = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

      }
      throw new HashtableOverflowException();

   }

   @Override
   public void remove(T element) {
      int probe = 0;

      while (probe < table.length) {
         int posicao = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

         if (table[posicao] == null) {
            break;
         }

         if (table[posicao].equals(element)) {
            table[posicao] = new DELETED();
            elements--;
         }
         probe++;
      }

   }

   @Override
   public T search(T element) {
      int probe = 0;
      T retorno = null;
      int posicao = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

      while (probe < table.length && table[posicao] != null) {

         if (table[posicao].equals(element)) {
            retorno = (T) table[posicao];
         }
         probe++;
         posicao = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

      }

      return retorno;
   }

   @Override
   public int indexOf(T element) {
      int retorno = -1;
      int probe = 0;

      while (probe < table.length) {
         int posicao = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

         if (table[posicao] == null) {
            break;
         }
         if (table[posicao].equals(element)) {
            retorno = posicao;
         }

         probe++;

      }

      return retorno;

   }

}
