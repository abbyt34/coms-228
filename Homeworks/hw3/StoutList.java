package edu.iastate.cs2280.hw3;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Abby Taylor
 * @date 11/19/2024
 */
/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E>
{
  /**
   * Default number of elements that may be stored in each node.
   */
  private static final int DEFAULT_NODESIZE = 4;
  
  /**
   * Number of elements that can be stored in each node.
   */
  private final int nodeSize;
  
  /**
   * Dummy node for head.  It should be private but set to public here only  
   * for grading purpose.  In practice, you should always make the head of a 
   * linked list a private instance variable.  
   */
  public Node head;
  
  /**
   * Dummy node for tail.
   */
  private Node tail;
  
  /**
   * Number of elements in the list.
   */
  private int size;
  
  /**
   * Constructs an empty list with the default node size.
   */
  public StoutList()
  {
    this(DEFAULT_NODESIZE);
  }

  /**
   * Constructs an empty list with the given node size.
   * @param nodeSize number of elements that may be stored in each node, must be 
   *   an even number
   */
  public StoutList(int nodeSize)
  {
    if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();
    
    // dummy nodes
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.previous = head;
    this.nodeSize = nodeSize;
  }
  
  /**
   * Constructor for grading only.  Fully implemented. 
   * @param head
   * @param tail
   * @param nodeSize
   * @param size
   */
  public StoutList(Node head, Node tail, int nodeSize, int size)
  {
	  this.head = head; 
	  this.tail = tail; 
	  this.nodeSize = nodeSize; 
	  this.size = size; 
  }

  /**
   * This function returns the size parameter
   * 
   * @return int size
   */
  @Override
  public int size()
  {
    return size;
  }
  
  /**
   * 
   * This method is used to add a single item to the back of the StoutList.
   * 
   * @param E item
   * @return true or false if the addition worked
   */
  @Override
  public boolean add(E item)
  {
	  //if the item does not exist
	  if (item == null) {
		  //throw a new NullPointer exception
		  throw new NullPointerException();
	  }
	  //create a local variable for the tail node
	  Node lastNode = tail.previous;
	  //if the list is empty
	  if (size == 0) {
		  //create a new variable to hold the new node and item
		  Node newNode = new Node();
		  //make sure the new item after the head is the newNode
		  head.next = newNode;
		  //make sure the node behind the newNode is for sure the head
		  newNode.previous = head;
		  //make sure the node behind the tail is the newNode
		  tail.previous = newNode;
		  //make sure the node behind the newNode is the tail
		  newNode.next = tail;
		  //add the item to the newNode
		  newNode.addItem(item);
	  }
	  //if the array is not empty
	  else {
		  //if the last node is full
		  if (lastNode.count == nodeSize) {
			  //create a new node to add into
			  Node newNode = new Node();
			  //made sure the newNode is added behind the tail
			  lastNode.next = newNode;
			  //make sure the node behind newNode is the previous node
			  newNode.previous = lastNode;
			  //make sure the tail is ahead of the newNode
			  tail.previous = newNode;
			  //make sure the tail is ahead of the newNode
			  newNode.next = tail;
			  //add the item to the newNode
			  newNode.addItem(item);
		  }
		  //if the tail node is not full
		  else {
			  //add the item to the lastNode
			  lastNode.addItem(item);
		  }
	  }
	  //increase the size of the linked list
	  size++;
	  //return true that it worked
	  return true;
  }

  /**
   * This function is used to add a item into the node at the given position. 
   * It follows the add rules specificed in the PDF
   * 
   * @param int pos, E item
   */
  @Override
  public void add(int pos, E item)
  {
	  //if the item does not exist
	  if (item == null) {
		  //throw a new NullPointerException
		  throw new NullPointerException();
	  }
	  //if the position is out of bounds
	  if (pos < 0 || pos > size) {
		  //throw a new IndexOutOfBoundsException
		  throw new IndexOutOfBoundsException();
	  }
	  
	  //find the node and offset values from the given position
	  NodeInfo n = new NodeInfo(null, 0);
	  n = n.find(pos);
	  Node node = n.node;
	  int offset = n.offset;
	  //find the predecessor node
	  Node predecessor = node.previous;
	  //if the list is empty, add
	  if (size == 0) {
		  add(item);
		  //return so the function does not incremement wrong
		  return;
	  }
	  //when offset == 0, two cases may occur
	  if (offset == 0) {
		  //when the node's predecessor is not the head and has less elements than M size
		  if (predecessor.count < nodeSize && predecessor != head) {
			  //add the node to n's predecessor
			  predecessor.addItem(item);
			  //increment size
			  size++;
			  //return to end function
			  return;
		  }
		  //when the node's predecessor is full or the node given is the tail
		  else if (predecessor.count == nodeSize || node == tail) {
			  //add the item to offset 0, or use the OG add method
			  add(item);
			  //return to end function
			  return;
		  }
	  }
	  //if there is space in node n, then put the item at offset off
	  if (node.count < nodeSize) {
		  //add the item to the given node at offset
		  node.addItem(offset, item);
		  //increment the size of the list
		  size++;
		  //return to end the function
		  return;
	  }
	  //if no conditions are met, peform the split function to add the item
	  else {
		  //create a new Node variable
		  Node newNext = new Node();
		  //find the halfway point
		  int half = nodeSize / 2;
		  //create a count variable to keep track of index's
		  int count = 0;
		  //while the count is less than half
		  while (count < half) {
			  //add the node at the half index to the newNode
			  newNext.addItem(node.data[half]);
			  //remove it from the original node
			  node.removeItem(half);
			  //incremement count so it isn't a never ending loop
			  count++;
		  }
		  //find the old next node
		  Node oldNext = node.next;
		  //move all nodes into the correct positions from each other, moving them down
		  node.next = newNext;
		  newNext.previous = node;
	      newNext.next = oldNext;
		  oldNext.previous = newNext;
		  //if offset is less than M / 2
		  if (offset <= nodeSize / 2) {
			  //add the item to the original node at offset
			 node.addItem(offset, item);
		  }
		  //if offset is greater than M / 2
		  if (offset > nodeSize / 2) {
			  //add the item to the newNode at the offset - M/2
			  newNext.addItem((offset - nodeSize/2), item);
		  }
		  //increase the size of the node
		  size++;
	  }
  }

  /**
   * This function removes the item at the given position.
   * This function also follows all the rules specified in the PDF.
   * 
   * @param int pos
   * @return E removedElement
   */
  @Override
  public E remove(int pos)
  {
	  //if the position is out of bounds
    if (pos < 0 || pos > size) {
    	//throw an IndexOutOfBoundsException
    	throw new IndexOutOfBoundsException();
    }
    //find the node and offset values from the given position
    NodeInfo n = new NodeInfo(null, 0);
    n = n.find(pos);
    Node node = n.node;
    int offset = n.offset;
    //create a removedElement variable so it can be returned
    E removedElement = node.data[offset];
    //if it is the last node (the tail) with one element
    if (node.next == tail && node.count == 1) {
    	//the node becomes the tail
    	node.previous.next = tail;
    	//make sure they line up properly
    	tail.previous = node.previous;
    	//decrement the size since an item was removed
    	size--;
    	//return the removedElement
    	return removedElement;
    }
    //last node with 2+ elements or with more than M/2 elements
    if (node.next == tail || node.count > nodeSize / 2) {
    	//remove the item at offset off
    	node.removeItem(offset);
    	//decrement the size since an item was removed
    	size--;
    	//return the removedElement
    	return removedElement;
    }
    //create a successor variable for easy tracking
    Node successor = node.next;
    //if the successor has more than M/2 elements
    if (successor.count > nodeSize / 2) {
    	//remove item first
    	node.removeItem(offset);
    	//move first item from successor to node
    	node.addItem(successor.data[0]);
    	successor.removeItem(0);
    }
    //if successor has <= M/2 elements
    else {
    	//remove the item at offset index
    	node.removeItem(offset);
    	//for the value of the successor node
    	for (int i = 0; i < successor.count; i++) {
    		//add the successor values to the node
    		node.addItem(successor.data[i]);
    	}
    	//make sure the node and successor line up
    	node.next = successor.next;
    	successor.next.previous = node;
    }
    //decrement the size since an item was removed
    size--;
    //return the removedElement
    return removedElement;
  }

  /**
   * Sort all elements in the stout list in the NON-DECREASING order. You may do the following. 
   * Traverse the list and copy its elements into an array, deleting every visited node along 
   * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting 
   * efficiency is not a concern for this project.)  Finally, copy all elements from the array 
   * back to the stout list, creating new nodes for storage. After sorting, all nodes but 
   * (possibly) the last one must be full of elements.  
   *  
   * Comparator<E> must have been implemented for calling insertionSort().    
   */
  public void sort()
  {
	  //create a new Comparable array
	  E[] arr = (E[]) new Comparable[size];
	  //create a new StoutListIterator
	  StoutListIterator iter = new StoutListIterator();
	  //intialize a count variable
	  int count = 0;
	  //while count is less than size
	  while (count < size) {
		  //add to arr at index count with empty spaces using next()
		  arr[count] = iter.next();
		  //increment count
		  count++;
	  }
	  
	  //create a new Comparator object
	  Comparator<E> comp = new StoutListComparator<E>();
	  //use InsertionSort to sort it in NON_DECREASING order
	  insertionSort(arr, comp);
	  
	  //make sure the head is before the tail
	  head.next = tail;
	  tail.previous = head;
	  //make sure size starts at zero for sake of for loop
	  size = 0;
	  //for the length of the array
	  for (int i = 0; i < arr.length; i++) {
		  //add arr[i] to the element array
		  add(arr[i]);
	  }
	  
  }
  
  /**
   * Sort all elements in the stout list in the NON-INCREASING order. Call the bubbleSort()
   * method.  After sorting, all but (possibly) the last nodes must be filled with elements.  
   *  
   * Comparable<? super E> must be implemented for calling bubbleSort(). 
   */
  public void sortReverse() 
  {
	  //create a new Comparable array
	  E[] arr = (E[]) new Comparable[size];
	  //create a new StoutListIterator
	  StoutListIterator iter = new StoutListIterator();
	  //intialize a count variable
	  int count = 0;
	  //while count is less than size
	  while (count < size) {
		  //add to arr at the index count with empty spaces using next()
		  arr[count] = iter.next();
		  //increment count
		  count++;
	  }
	  
	  //use BubbleSort to sort in NON-INCREASING order
	  bubbleSort(arr);
	  
	  //make sure the head it before the tail
	  head.next = tail;
	  tail.previous = head;
	  //make sure size starts at zero for sake of for loop
	  size = 0;
	  //for the length of the array
	  for (int i = 0; i < arr.length; i++) {
		  //add arr[i] to the element array
		  add(arr[i]);
	  }
  }
  
  /**
   * This function intializes an interator.
   * 
   * @return new StoutListIterator()
   */
  @Override
  public Iterator<E> iterator()
  {
	  //as specified on the PDF, if we are confident in our StoutListIterator, we may use it
    return new StoutListIterator();
  }

  /**
   * This function intializes an List iterator.
   * 
   * @return new StoutListIterator()
   */
  @Override
  public ListIterator<E> listIterator()
  {
	  //as specified in the PDF, if we are confident in our StoutListIterator, we may use it
    return new StoutListIterator();
  }

  /**
   * This function intializes a List iterator based on index.
   * 
   * @param int index
   * @return new StoutListIterator(index)
   */
  @Override
  public ListIterator<E> listIterator(int index)
  {
	  //as specified in the PDF, if we are confident in our StoutListIterator, we may use it
    return new StoutListIterator(index);
  }
  
  /**
   * Returns a string representation of this list showing
   * the internal structure of the nodes.
   */
  public String toStringInternal()
  {
    return toStringInternal(null);
  }

  /**
   * Returns a string representation of this list showing the internal
   * structure of the nodes and the position of the iterator.
   *
   * @param iter
   *            an iterator for this list
   */
  public String toStringInternal(ListIterator<E> iter) 
  {
      int count = 0;
      int position = -1;
      if (iter != null) {
          position = iter.nextIndex();
      }

      StringBuilder sb = new StringBuilder();
      sb.append('[');
      Node current = head.next;
      while (current != tail) {
          sb.append('(');
          E data = current.data[0];
          if (data == null) {
              sb.append("-");
          } else {
              if (position == count) {
                  sb.append("| ");
                  position = -1;
              }
              sb.append(data.toString());
              ++count;
          }

          for (int i = 1; i < nodeSize; ++i) {
             sb.append(", ");
              data = current.data[i];
              if (data == null) {
                  sb.append("-");
              } else {
                  if (position == count) {
                      sb.append("| ");
                      position = -1;
                  }
                  sb.append(data.toString());
                  ++count;

                  // iterator at end
                  if (position == size && count == size) {
                      sb.append(" |");
                      position = -1;
                  }
             }
          }
          sb.append(')');
          current = current.next;
          if (current != tail)
              sb.append(", ");
      }
      sb.append("]");
      return sb.toString();
  }


  /**
   * Node type for this list.  Each node holds a maximum
   * of nodeSize elements in an array.  Empty slots
   * are null.
   */
  private class Node
  {
    /**
     * Array of actual data elements.
     */
    // Unchecked warning unavoidable.
    public E[] data = (E[]) new Comparable[nodeSize];
    
    /**
     * Link to next node.
     */
    public Node next;
    
    /**
     * Link to previous node;
     */
    public Node previous;
    
    /**
     * Index of the next available offset in this node, also 
     * equal to the number of elements in this node.
     */
    public int count;

    /**
     * Adds an item to this node at the first available offset.
     * Precondition: count < nodeSize
     * @param item element to be added
     */
    void addItem(E item)
    {
      if (count >= nodeSize)
      {
        return;
      }
      data[count++] = item;
      //useful for debugging
      //      System.out.println("Added " + item.toString() + " at index " + count + " to node "  + Arrays.toString(data));
    }
  
    /**
     * Adds an item to this node at the indicated offset, shifting
     * elements to the right as necessary.
     * 
     * Precondition: count < nodeSize
     * @param offset array index at which to put the new element
     * @param item element to be added
     */
    void addItem(int offset, E item)
    {
      if (count >= nodeSize)
      {
    	  return;
      }
      for (int i = count - 1; i >= offset; --i)
      {
        data[i + 1] = data[i];
      }
      ++count;
      data[offset] = item;
      //useful for debugging 
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
    }

    /**
     * Deletes an element from this node at the indicated offset, 
     * shifting elements left as necessary.
     * Precondition: 0 <= offset < count
     * @param offset
     */
    void removeItem(int offset)
    {
      E item = data[offset];
      for (int i = offset + 1; i < nodeSize; ++i)
      {
        data[i - 1] = data[i];
      }
      data[count - 1] = null;
      --count;
    }    
  }
 
  private class StoutListIterator implements ListIterator<E>
  { 
	  //constant for if next() or previous() were not called before
	  private static final int NONE = -1;
	  //constant for if previous was called before
	  private static final int AHEAD = 1;
	  //constant for if next() was called before
	  private static final int BEHIND = 0;

	  //instance variable for the cursor
	  private Node cursor;
	  //instance variable for the direction
	  private int direction;
	  //instance variable for the element array
	  private E[] data;
	  //instance variable for an index int
	  private int index;
	  //instance variable for the list index in E[] list
	  private int nodeIndex;
	  
    /**
     * Default constructor 
     */
    public StoutListIterator()
    {
    	//intialize everything to starting values
    	cursor = head;
    	index = 0;
    	direction = NONE;
    	nodeIndex = 0;
    }

    /**
     * Constructor finds node at a given position.
     * @param pos
     */
    public StoutListIterator(int pos)
    {
    	//if the position is out of bounds
    	if (pos < 0 || pos > size) {
    		//throw new IndexOutOfBoundsException
    		throw new IndexOutOfBoundsException();
    	}
    	//create dummy NodeInfo object
    	NodeInfo n = new NodeInfo(null, 0);
    	//use dummy n to find the objects attributes
    	n = n.find(pos);
    	//intalize the cursor to the node
    	cursor = n.node;
    	//intialize the nodeIndex to the offset
    	nodeIndex = n.offset;
    	//intalize the index to the position
    	index = pos;
    	//intialize the direction to NONE
    	direction = NONE;
    }

    /**
     * This method returns a boolean if the function has a next value
     * 
     * @return boolean cursor < size boolean
     */
    @Override
    public boolean hasNext()
    {
    	//returns true if there is a next
    	 return index < size;
    }

    /**
     * This method returns the next value and increments and decrements indexes and cursors properly.
     * 
     * @return E element
     */
    @Override
    public E next()
    {
    	//if the function does not have a next
    	if (!hasNext()) {
    		//throw a NoSuchElementException
    		throw new NoSuchElementException();
    	}
    	//if the nodeIndex is greater or equal to the cursor count of elements
    	if (nodeIndex >= cursor.count) {
    		//increment the cursor node
    		cursor = cursor.next;
    		//reset the nodeIndex to zero since we moved on to the next node
    		nodeIndex = 0;
    	}
    	
    	//intialize the nodeIndex from the cursor before incrementing
    	E element = cursor.data[nodeIndex];
    	
    	//intalize the direction to BEHIND to signify next() was called
    	direction = BEHIND;
    	//increment nodeIndex
    	nodeIndex++;
    	//increment index
    	index++;
    	
    	//return the element from earlier
    	return element;

    }

    /**
     * This function removes the item either in front or behind the cursor depending on the call before hand
     */
    @Override
    public void remove()
    {
    	//if no call to next() or previous() was made before the call to remove()
    	if (direction == NONE) {
    		//throw an IllegalStateException
    		throw new IllegalStateException("An item cannot be removed without next() or previous() being called");
    	}
    	//if next() was the call before remove()
    	else if (direction == BEHIND) {
    		//remove the element behind the current index
    		StoutList.this.remove(index - 1);
    		//decrement the index
    		index--;
    		//decrement the nodeIndex
    		nodeIndex--;
    		//if the nodeIndex goes negative
    		if (nodeIndex < 0) {
    			//bring the cursor back to the node before it
    			cursor = cursor.previous;
    			//bring the nodeIndex to the end of the cursor node
    			nodeIndex = cursor.count - 1;
    		}
    	}
    	//if previous() was the call before remove()
    	else if (direction == AHEAD) {
    		//remove the element at the cursor
    		StoutList.this.remove(index);
    		//if the nodeIndex gets larger than the cursor count
    		if (nodeIndex >= cursor.count) {
    			//increment to the next cursor node
    			cursor = cursor.next;
    			//make the nodeIndex zero so it starts at the end of the next node
    			nodeIndex = 0;
    		}
    	}
    	//decrement size since you removed an item
    	size--;
    	//reset the direction so it isn't storing a next() or previous() call
    	direction = NONE;
    }
    
    /**
     * This function returns the next index in a node
     * 
     * @return int index
     */
    public int nextIndex() {
    	//return the index
		return index;
    }
    
    /**
     * This function returns the previous index
     * 
     * @return int index - 1
     */
    public int previousIndex() {
    	//return the index before
    	return index - 1;
    }
    
    /**
     * This function finds if there is a previous element.
     * 
     * @return boolean index > 0
     */
    public boolean hasPrevious() {
    	//return a boolean value associated with index compared to zero
    	return index > 0;
    }
    
    /**
     * This function finds the previous element and increments and decrements cursor and indexes properly.
     * 
     * @return E cursor.data[nodeIndex]
     */
    public E previous() {
    	//if there is no previous element
    	if(!hasPrevious()) {
    		//throw a new NoSuchElementException
    		throw new NoSuchElementException();
    	}
    	
    	//decrement the index
    	index--;
    	//decrement the nodeIndex
    	nodeIndex--;
    	//if the nodeIndex goes negative
    	if (nodeIndex < 0) {
    		//go to the previous cursor node
    		cursor = cursor.previous;
    		//make sure the nodeIndex is at the end of the next cursor node
    		nodeIndex = cursor.count - 1;
    	}
    	
    	//set the direction to AHEAD to signify that previous() was called last
    	direction = AHEAD;
    	//return the element associated with nodeIndex
    	return cursor.data[nodeIndex];
    }
    
    /**
     * This function sets the replace element in the index it happens to be in depending on what was called before hand
     * 
     * @param E replaceElem
     */
    public void set(E replaceElem) {
    	//if the replace element is null
    	if (replaceElem == null) {
    		//throw a new NullPointerException
    		throw new NullPointerException();
    	}
    	//if there was no call to next() or previous() before hand
    	if (direction == NONE) {
    		//throw a new IllegalStateException
    		throw new IllegalStateException();
    	}
    	//if there was a call to previous() before hand
    	else if (direction == AHEAD) {
    		//assign the replace element to the nodeIndex value in the cursor
    		cursor.data[nodeIndex] = replaceElem;
    	}
    	//if there was a call to next() before hand
    	else if (direction == BEHIND) {
    		//if the nodeIndex is greater than 0
    		if (nodeIndex > 0) {
    			//assign the replace element to the index behind the current node
    			cursor.data[nodeIndex - 1] = replaceElem;
    		}
    		//if the nodeIndex is less than 0
    		else {
    			//assign the replace element to the previous cursors last element spot
    			cursor.previous.data[cursor.previous.count - 1] = replaceElem;
    		}
    	}
    }
    
    /**
     * This function takes in a E item and adds it to proper element based on the recent call to next() or previous()
     * 
     * @param E item
     */
    @Override
	public void add(E item) {
    	//if the item is null
		if (item == null) {
			//throw a new NullPointerException
			throw new NullPointerException();
		}
		//if there was no call to next() or previous() before hand
		if (direction == NONE) {
			//throw a new IllegalStateException
			throw new IllegalStateException();
		}
		//if there was a call to next() before hand
		else if (direction == BEHIND) {
			//add the given item to the node at the index
			StoutList.this.add(index, item);
			//increment the index
			index++;
			//increment the size since an item was added
			size++;
			//increment the nodeIndex
			nodeIndex++;
			//if the nodeIndex is the same as the nodeSize
			if (nodeIndex == nodeSize) {
				//move the cursor node to the next node
				cursor = cursor.next;
				//set the nodeIndex to the beginning of the next node
				nodeIndex = 0;
			}
		}
		//if there was a call to previous() before hand
		else if (direction == AHEAD) {
			//add the item to the index one ahead
			StoutList.this.add(index + 1, item);
			//increment the size since an item was added
			size++;
		}
		//reset the direction so there is no call to next() or previous()
		direction = NONE;
  }
  }
  

  /**
   * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order. 
   * @param arr   array storing elements from the list 
   * @param comp  comparator used in sorting 
   */
  private void insertionSort(E[] arr, Comparator<? super E> comp)
  {
	  //for the length of the array starting at 1
	  for (int i = 1; i < arr.length; i++) {
		  //find the key at the i value of the array
		  E key = arr[i];
		  //start a j value for loop and comparing
		  int j = i -1;
		  //while j is 0 or larger and arr[j] > key
		  while (j > -1 && comp.compare(key, arr[j]) < 0) {
			  //add the j value to the next index
			  arr[j + 1] = arr[j];
			  //decrement the j value
			  j--;
		  }
		  //assign the key to the next index
		  arr[j + 1] = key;
	  }
  }
  
  /**
   * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a 
   * description of bubble sort please refer to Section 6.1 in the project description. 
   * You must use the compareTo() method from an implementation of the Comparable 
   * interface by the class E or ? super E. 
   * @param arr  array holding elements from the list
   */
  private void bubbleSort(E[] arr)
  {
	  //start a boolean to track if the array is sorted
	  boolean isSorted = false;
	  //while the boolean is false
	  while (!isSorted) {
		  //assign it to true
		  isSorted = true;
		  //for the length of the array start at 1
		  for (int i = 1; i < arr.length; i++) {
			  //if arr[i] is greater than arr[i - 1]
			  if (arr[i].compareTo(arr[i - 1]) >= 0) {
				  //swap arr[i] with arr[i - 1]
				  E temp = arr[i];
				  arr[i] = arr[i - 1];
				  arr[i - 1] = temp;
				  //set the boolean to false
				  isSorted = false;
			  }
		  }
	  }
  }
  
  private class NodeInfo{
	  //create a node variable
	  public Node node;
	  //create a offset variable
	  public int offset;
	  
	  /**
	   * Constructor to set the node and offset
	   * 
	   * @param node
	   * @param offset
	   */
	  public NodeInfo (Node node, int offset) {
		  //set the node
		  this.node = node;
		  //set the offset
		  this.offset = offset;
	  }
	  
	  /**
	   * This function finds the offset and node at the given position and returns a NodeInfo object
	   * @param pos
	   * @return NodeInfo
	   */
	  public NodeInfo find(int pos) {
		  //if the position is out of bounds
		  if (pos < 0 || pos > size) {
			  //throw a new IndexOutOfBoundsException
			  throw new IndexOutOfBoundsException();
		  }
		  
		  //create a currentIndex variable
		  int currentIndex = 0;
		  //temp node is the next node after the dummy variable for the head
		  Node temp = head.next;
		  
		  //while the temp value is not the tail
		  while (temp != tail) {
			  //if the currentIndex + the count of the temp is less than the given position
			  if (currentIndex + temp.count <= pos) {
				  //increment the currentIndex with the count of the temp
				  currentIndex += temp.count;
				  //increment the temp node to the next 
				  temp = temp.next;
			  }
			  //else
			  else {
				  //return a new NodeInfo object at the temp value and the position minus the currentIndex
				  return new NodeInfo(temp, pos - currentIndex);
			  }
		  }
		  //if all else does not run, return a new NodeInfo object at the previous node and the position minus the currentIndex minus the previous count
		  return new NodeInfo(temp.previous, pos - (currentIndex - temp.previous.count));
	  }
	  
  }
  
  /**
   * StoutListComparator makes it so we can use the compare() function
   * @param <E>
   */
  public class StoutListComparator<E> implements Comparator<E>{

	  /**
	   * This function uses the Comparable interface and the compareTo function
	   * 
	   * @param E o1, o2
	   * @return the compareTo function
	   */
	@Override
	public int compare(E o1, E o2) {
		return ((Comparable<? super E>)o1).compareTo(o2);
	}
	  
  }
}
