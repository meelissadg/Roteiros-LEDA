package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
      super(size);
      hashFunction = new HashFunctionLinearProbing<T>(size, method);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
      int probe = 0;
      int posicao = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

      if (element != null) {
         while (probe < table.length) {

            if (table[posicao] instanceof DELETED || table[posicao] == null) {
               table[posicao] = element;
               this.elements++;
               return;
            } else {
               probe++;
               COLLISIONS++;
               posicao = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

            }

         }
         throw new HashtableOverflowException();

      }

   }

   @Override
   public void remove(T element) {
      int probe = 0;

      if (element != null) {
         while (probe < table.length) {
            int posicao = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

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

   }

   @Override
   public T search(T element) {
      int probe = 0;
      T retorno = null;
      int posicao = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

      if (element != null) {
         while (probe < table.length && table[posicao] != null) {

            if (table[posicao].equals(element)) {
               retorno = (T) table[posicao];
            } else {
               probe++;
               posicao = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
            }
         }

      }

      return retorno;
   }

   @Override
   public int indexOf(T element) {
      int retorno = -1;
      int probe = 0;

      if (element != null) {
         while (probe < table.length) {
            int posicao = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

            if (table[posicao] == null) {
               break;
            }
            if (table[posicao].equals(element)) {
               retorno = posicao;
            }

            probe++;

         }
      }

      return retorno;

   }

}
