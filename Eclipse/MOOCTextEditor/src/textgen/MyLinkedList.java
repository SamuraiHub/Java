package textgen;

import java.util.AbstractList;
import java.util.Collection;

import javax.lang.model.element.Element;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	protected LLNode<E> head;
	protected LLNode<E> tail;
	private int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
	}

	public MyLinkedList(java.util.Collection<E> c) {
		// TODO: Implement this method
		addAll(size, c);
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	public boolean add(E element) {
		// TODO: Implement this method
		if(element == null)
			throw new NullPointerException("Cannot insert a null element to a list");
		
		LLNode<E> n = new LLNode<E>(element, tail, null);
		if(head == null)
		{
			head = n;
			tail = n;
		}
		else
		{
		    tail.next = n;
		    tail = n;
		}
		size += 1;
		modCount++;
		return true;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 * @throws NullPointerException
	 *             if the specified collection is null.
	 */
	public E get(int index) {
		// TODO: Implement this method.
		checkElementIndex(index);

		LLNode<E> a = node(index);
		return a.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	public void add(int index, E element) {
		// TODO: Implement this method
		checkPositionIndex(index);
		if(element == null)
			throw new NullPointerException("Cannot insert a null element to a list");

		LLNode<E> pred, succ;
		if (index == size) {
			succ = null;
			pred = tail;
		} else {
			succ = node(index);
			pred = succ.prev;
		}

		LLNode<E> newNode = new LLNode<>(element, pred, succ);

		if (pred == null)
			head = newNode;
		else
			pred.next = newNode;

		if (succ == null)
			tail = newNode;
		else
			succ.prev = newNode;

		size += 1;
		modCount++;
	}

	/** Return the size of the list */
	public int size() {
		// TODO: Implement this method
		return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		// TODO: Implement this method
		checkElementIndex(index);

		E element;

		if (index == size - 1) {
			element = tail.data;
			tail = tail.prev;
			if(tail != null) tail.next = null;
			else head = null;
		} else if (index == 0) {
			element = head.data;
			head = head.next;
			head.prev = null;
		} else {
			LLNode<E> r = node(index);
			element = r.data;
			r.next.prev = r.prev;
			r.prev.next = r.next;
		}

		size -= 1;
		modCount++;

		return element;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E set(int index, E element) {
		// TODO: Implement this method
		checkElementIndex(index);
		if(element == null)
			throw new NullPointerException("Cannot set an element to a null element");

		LLNode<E> a = node(index);
		E Element = a.data;
		a.data = element;
		return Element;
	}

	/**
	 * Appends all of the elements in the specified collection to the end
     * of this list. The new elements will appear in the list in the order
	 * that they are returned by the specified collection's iterator.
	 *
	 * @param c
	 *            collection containing elements to be added to this list
	 * @return {@code true} if this list changed as a result of the call
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	public boolean addAll(Collection<? extends E> c) {
		return addAll(size, c);
	}

	/**
	 * Inserts all of the elements in the specified collection into this list,
	 * starting at the specified position. Shifts the element currently at that
	 * position (if any) and any subsequent elements to the right (increases
	 * their indices). The new elements will appear in the list in the order
	 * that they are returned by the specified collection's iterator.
	 *
	 * @param index
	 *            index at which to insert the first element from the specified
	 *            collection
	 * @param c
	 *            collection containing elements to be added to this list
	 * @return {@code true} if this list changed as a result of the call
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	public boolean addAll(int index, Collection<? extends E> c) {
		checkPositionIndex(index);

		Object[] a = c.toArray();
		int numNew = a.length;
		if (numNew == 0)
			return false;

		LLNode<E> pred, succ;
		if (index == size) {
			succ = null;
			pred = tail;
		} else {
			succ = node(index);
			pred = succ.prev;
		}

		for (Object o : a) {
			@SuppressWarnings("unchecked")
			E e = (E) o;
			LLNode<E> newNode = new LLNode<>(e, pred, null);
			if (pred == null)
				head = newNode;
			else
				pred.next = newNode;
			pred = newNode;
		}

		if (succ == null) {
			tail = pred;
		} else {
			pred.next = succ;
			succ.prev = pred;
		}

		size += numNew;
		modCount++;
		return true;
	}

	/**
	 * Checks if the position index lies between this list's bounds including
	 * list size for add operations, i.e. >= 0 and <= the size of this list.
	 *
	 * @param index
	 *            index of where to check
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds
	 */
	private void checkPositionIndex(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}

	/**
	 * Checks if the element index lies between this list's bounds for get and
	 * set operations, i.e. >= 0 and < the size of this list.
	 *
	 * @param index
	 *            index of where to check
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds
	 */
	private void checkElementIndex(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}

	/**
	 * Returns the (non-null) Node at the specified element index.
	 */
	private LLNode<E> node(int index) {
		// assert isElementIndex(index);

		if (index < (size >> 1)) {
			LLNode<E> x = head;
			for (int i = 0; i < index; i++)
				x = x.next;
			return x;
		} else {
			LLNode<E> x = tail;
			for (int i = size - 1; i > index; i--)
				x = x.prev;
			return x;
		}
	}
	
	/*
	 * @return a string representation of the list data.
	*/
	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder(size());
		int a = size - 1;
		LLNode<E> n = head;
		s.append('[');
		for(int i = 0; i < a; i++) {
			s.append(n.toString());
			s.append(", ");
			n = n.next;
		}
		s.append(n.toString());
		s.append(']');
		return s.toString();
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	protected LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	protected LLNode(E e, LLNode<E> Prev, LLNode<E> Next) {
		this.data = e;
		this.prev = Prev;
		this.next = Next;
	}
	
	/*
	 * @return a string representation of the node data.
	*/
	@Override
	public String toString()
	{
		return data.toString();
	}
}
