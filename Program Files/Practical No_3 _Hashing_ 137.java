
package Practical_2;

import java.util.*;


	class HashTable {
	    private int[] table;
	    private int size;

	    public HashTable(int size) {
	        this.size = size;
	        table = new int[size];
	        for (int i = 0; i < size; i++) {
	            table[i] = -1; // initialize all slots as empty
	        }
	    }

	    // 1️⃣ Modulo Division Hashing
	    public int moduloDivisionHash(int key) {
	        return key % size;
	    }

	    // 2️⃣ Digit Extraction Hashing
	    public int digitExtractionHash(int key) {
	        int sum = 0;
	        while (key > 0) {
	            sum += key % 10;
	            key /= 10;
	        }
	        return sum % size;
	    }

	    // 3️⃣ Fold Shift Hashing
	    public int foldShiftHash(int key) {
	        String keyStr = Integer.toString(key);
	        int sum = 0;
	        for (int i = 0; i < keyStr.length(); i += 2) {
	            String chunk = keyStr.substring(i, Math.min(i + 2, keyStr.length()));
	            sum += Integer.parseInt(chunk);
	        }
	        return sum % size;
	    }

	    // 4️⃣ Fold Boundary Hashing (same as fold shift for demonstration)
	    public int foldBoundaryHash(int key) {
	        String keyStr = Integer.toString(key);
	        int sum = 0;
	        for (int i = 0; i < keyStr.length(); i += 2) {
	            String chunk = keyStr.substring(i, Math.min(i + 2, keyStr.length()));
	            sum += Integer.parseInt(chunk);
	        }
	        return sum % size;
	    }

	    // Linear probing for collision resolution
	    private int linearProbe(int key, int hash) {
	        int index = hash;
	        int i = 0;
	        while (table[index] != -1) {
	            i++;
	            index = (hash + i) % size;
	        }
	        return index;
	    }

	    // Insert key using chosen hash method
	    public void insert(int key, String hashType) {
	        int index;
	        switch (hashType) {
	            case "Modulo Division":
	                index = moduloDivisionHash(key);
	                break;
	            case "Digit Extraction":
	                index = digitExtractionHash(key);
	                break;
	            case "Fold Shift":
	                index = foldShiftHash(key);
	                break;
	            case "Fold Boundary":
	                index = foldBoundaryHash(key);
	                break;
	            default:
	                throw new IllegalArgumentException("Unknown hashing type");
	        }
	        index = linearProbe(key, index);
	        table[index] = key;
	        System.out.println("Inserted key " + key + " at index " + index);
	    }

	    // Display hash table
	    public void display() {
	        for (int i = 0; i < size; i++) {
	            System.out.println("Index " + i + ": " + (table[i] == -1 ? "Empty" : table[i]));
	        }
	    }
	}

	public class Testing_All {
	    public static void main(String[] args) {
	        int[] keys = {27, 44, 56, 72, 99, 123, 150, 1001};

	        // Using Modulo Division Hashing
	        System.out.println("Using Modulo Division Hashing:");
	        HashTable hashTable = new HashTable(10);
	        for (int key : keys) {
	            hashTable.insert(key, "Modulo Division");
	        }
	        hashTable.display();

	        // Using Digit Extraction Hashing
	        System.out.println("\nUsing Digit Extraction Hashing:");
	        hashTable = new HashTable(10);
	        for (int key : keys) {
	            hashTable.insert(key, "Digit Extraction");
	        }
	        hashTable.display();

	        // Using Fold Shift Hashing
	        System.out.println("\nUsing Fold Shift Hashing:");
	        hashTable = new HashTable(10);
	        for (int key : keys) {
	            hashTable.insert(key, "Fold Shift");
	        }
	        hashTable.display();

	        // Using Fold Boundary Hashing
	        System.out.println("\nUsing Fold Boundary Hashing:");
	        hashTable = new HashTable(10);
	        for (int key : keys) {
	            hashTable.insert(key, "Fold Boundary");
	        }
	        hashTable.display();
	    }
	}
