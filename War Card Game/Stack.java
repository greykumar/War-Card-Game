/* 

* Grey Kumar 

* CPSC 5002, Seattle University 

* This is free and unencumbered software released into the public domain. 

*/

package gkumar_p3;
import java.util.*;

/**
 * This class builds the stacks for the dealer deck and the discard deck
 * 
 * @author Grey Kumar
 *
 * @param <T> the generic data type
 */
public class Stack<T> {

	private T[] s; // Holds stack elements
	private int top; // Stack top pointer

	/**
	 * Sets the length of the stack
	 * 
	 * @param capacity The capacity of the stack.
	 */
	public Stack(int length) {
		s = (T[]) new Object[length];
		top = 0;
	}

	/**
	 * checks if stack is empty
	 * 
	 * @return true if stack is empty.
	 */
	public boolean empty() {
		return top == 0;
	}

	/**
	 * The push method pushes a value onto the stack.
	 * 
	 * @param x The value to push onto the stack.
	 * @exception StackOverflowException When the stack is full.
	 */
	public void push(T x) {
		if (top == s.length)
			throw new IllegalArgumentException("Stack is full!");
		else {
			s[top] = x;
			top++;
		}
	}

	/**
	 * The pop method pops a value off the stack.
	 * 
	 * @return The value popped.
	 * @exception EmptyStackException When the stack is empty.
	 */
	public T pop() {
		if (empty())
			
			throw new IllegalArgumentException("Stack is empty!");
		else {
			top--;
			return s[top];
		}
	}

	/**
	 * The peek method returns the value at the top of the stack.
	 * 
	 * @return value at top of the stack.
	 * @exception EmptyStackException When the stack is empty.
	 */
	public T peek() {
		if (empty())
			throw new IllegalArgumentException("Stack is empty!");
		else {
			return s[top - 1];
		}
	}
	
	/**
	 * This method creates a copy of a stack
	 * 
	 * @return the newly copied stack
	 */
	public Stack <T> copy() {
		Stack <T> copyStack = new Stack<>(top);
		for (int i = 0; i < top; i++) {
			copyStack.push(s[i]);
		}
		
		return copyStack;
	}
	
	/**
	 * this method checks if two stacks are equal
	 * 
	 * @param obj2 the stack being compared
	 * @return true if equal
	 */
	public boolean equals(Stack<T> obj2) {
		if (top != obj2.top) {
			return false;
		}
		
		for(int i = 0; i < top; i++) {
			if(!(s[i].equals(obj2.s[i]))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method formulates a string of the stack values
	 * 
	 * @return the string 
	 */
	public String toString() {
		String str = "";
		
		for(int i = top-1; i >= 0; i--) {
			str += s[i] + " ";
		}
		return str;
	}
}
