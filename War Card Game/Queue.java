/* 

* Grey Kumar 

* CPSC 5002, Seattle University 

* This is free and unencumbered software released into the public domain. 

*/
package gkumar_p3;

/**
 * This class builds the queue objects for each players hand
 * 
 * @author Grey Kumar
 *
 * @param <T> the generic data type
 */
public class Queue<T> {

	private class Node {
		T value;
		Node next;
		Node  prev;

		Node(T val, Node n, Node p) {
			value = val;
			next = n;
			prev = p;
		}
	}

	private Node front = null;
	private Node rear = null;

	/**
	 * The method enqueue adds a value to the queue.
	 * 
	 * @param s The value to be added to the queue.
	 */
	public void enqueue(T s) {
		if (rear != null) {
			rear.next = new Node(s, null, rear);
			rear = rear.next;
		} else {
			rear = new Node(s, null, null);
			front = rear;
		}
	}

	/**
	 * This method copies the values from one queue to another
	 * 
	 * @return the newly copies queue
	 */
	public Queue<T> copy() {
		Queue<T> copyQ = new Queue<T>();
		Node temp = front;
		while (temp != null) {
			copyQ.enqueue(temp.value);
			temp = temp.next;
		}
		return copyQ;
	}

	/**
	 * The empty method checks to see if the queue is empty.
	 * 
	 * @return true if and only if queue is empty.
	 */
	public boolean empty() {
		return front == null;
	}

	/**
	 * The method peek returns value at the front of the queue.
	 * 
	 * @return item at front of queue.
	 * @excepton EmptyQueueException When the queue is empty.
	 */
	public T peek() {
		if (empty())
			throw new IllegalArgumentException("Queue is empty");
		else
			return front.value;
	}

	/**
	 * The dequeue method removes and returns the item at the front of the queue.
	 * 
	 * @return item at front of queue.
	 * @exception EmptyQueueException When the queue is empty.
	 */
	public T dequeue() {
		if (empty())
			throw new IllegalArgumentException("Queue is empty");
		else {
			T value = front.value;
			front = front.next;
			if (front == null)
				rear = null;
			return value;
		}
	}

	/**
	 * The toString method concatenates all strings in the queue to give a string
	 * representation of the contents of the queue.
	 * 
	 * @return string representation of this queue.
	 */
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();

		// Walk down the list and append all values
		Node p = front;
		while (p != null) {
			sBuilder.append("| " + p.value + " ");
			p = p.next;
		}
		sBuilder.append("|");
		return sBuilder.toString();
	}
	
	/**
	 * This method determines the size of the queue
	 * 
	 * @return the size
	 */
	public int size() {
		int sze = 0;
		Node temp = front;
		while (temp != null) {
			sze++;
			temp = temp.next;
		}
		return sze;
	}

	/**
	 * This method appends a new queue object to an already existing queue object
	 * 
	 * @param tempQ the new queue object
	 */
	public void append(Queue<T> tempQ) {
		Queue<T> newQ = tempQ.copy();
		Node temp = newQ.front;
		while (temp != null) {
			enqueue(temp.value);
			temp = temp.next;
		}

	}
	
	/**
	 * This method checks if one queue is equal to the next
	 * 
	 * @param obj2 the second queue being compared
	 * @return true if equal
	 */
	public boolean equals(Queue <T> obj2) {
		Node temp = front;
		Node objTemp = obj2.front;
		if (size() != obj2.size()) {
			return false;
		}
		
		while (temp != null) {
			if (!(temp.value.equals(objTemp.value))) {
				return false;
			}
			temp = temp.next;
			objTemp = objTemp.next;
		}
		return true;
	}

}
