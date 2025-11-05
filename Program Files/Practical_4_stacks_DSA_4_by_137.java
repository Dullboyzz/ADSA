package Practical_2;

import java.util.*;

public class Testing_All {

    // Inner class representing the Stack data structure
    static class Stack {
        private int capacity;
        private int[] stack;
        private int top;

        public Stack(int capacity) {
            this.capacity = capacity;
            this.stack = new int[capacity];
            this.top = -1; // -1 indicates an empty stack
        }

        // Check if the stack is empty
        public boolean isEmpty() {
            return top == -1;
        }

        // Check if the stack is full
        public boolean isFull() {
            return top == capacity - 1;
        }

        // Push an element onto the stack
        public boolean push(int item) {
            if (isFull()) {
                System.out.println("Stack Overflow! Cannot push " + item);
                return false;
            }
            stack[++top] = item;
            System.out.println("Pushed " + item + " to stack");
            return true;
        }

        // Pop an element from the stack
        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow! Cannot pop from empty stack");
                return -1; // Return a sentinel value for error
            }
            int item = stack[top--];
            System.out.println("Popped " + item + " from stack");
            return item;
        }

        // Peek at the top element without removing it
        public int peek() {
            if (isEmpty()) {
                System.out.println("Stack is empty!");
                return -1; // Return a sentinel value for error
            }
            return stack[top];
        }

        // Get the current size of the stack
        public int size() {
            return top + 1;
        }

        // Display all elements in the stack from top to bottom
        public void display() {
            if (isEmpty()) {
                System.out.println("Stack is empty!");
                return;
            }
            System.out.println("Stack elements (top to bottom):");
            for (int i = top; i >= 0; i--) {
                System.out.println(" " + stack[i]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Stack Implementation Demo ===\n");

        Stack s = new Stack(5);

        s.push(10);
        s.push(20);
        s.push(30);
        s.push(40);
        s.push(50);

        System.out.println("\nStack size: " + s.size());
        s.display();

        System.out.println("\nTop element: " + s.peek());

        System.out.println();
        s.pop();
        s.pop();

        System.out.println("\nStack size after popping: " + s.size());
        s.display();

        System.out.println("\n--- Testing Stack Overflow ---");
        // Try to push more items than capacity
        s.push(60);
        s.push(70);
        s.push(80); 

        System.out.println("\n--- Testing Stack Underflow ---");

        // Pop all remaining items
        while (!s.isEmpty()) {
            s.pop();
        }
        // Try to pop from an empty stack
        s.pop(); 
    }
}
