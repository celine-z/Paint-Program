/*
 * Celine Zhang
 * June 2016
 * Contains methods for a linked list
 */

class LinkedList<T> {
  private int numberOfNodes = 0; 
  private ListNode<T> front = null;
  
  // Adds a node to the front of the linked list
  public void addFront( T element ) {
    front = new ListNode<T>( element, front );
    numberOfNodes++;
  }
  
  // Returns a reference to the data in the first node, or null if the list is empty
  public T first() {
    if (isEmpty()) 
      return null;
    
    return front.getData();
  }
  
  // Removes a node from the front of the linked list, if there is one
  // Returns a reference to the data in the first node, or null if the list is empty
  @SuppressWarnings("unchecked")
  public T removeFront() {
    T tempData;
    
    if (isEmpty()) 
      return null;
    
    tempData = front.getData();
    front = front.getNext();
    numberOfNodes--;
    return tempData;
  }
  
  // Returns true if the linked list contains a specific element, or false otherwise
  @SuppressWarnings("unchecked")
  public boolean contains( T key ) {
    ListNode<T> searchNode;
    searchNode = front;
    while ( (searchNode != null) && (!key.equals(searchNode.getData())) ) {
      searchNode = searchNode.getNext();
    }
    return (searchNode != null);
  }
  
  // Insert a node in the list with a given key value
  @SuppressWarnings("unchecked")
  public void insert( Comparable key ) {
    ListNode<T> before = null;
    ListNode<T> after = front;
    ListNode<T> newNode;        
    
    // Go through the list to find the ListNode before and after insertion point
    while ((after != null) && (key.compareTo(after.getData()) > 0)) {
      before = after;
      after = after.getNext();
    }
    
    // Create the new node with link pointing to after
    newNode = new ListNode<T>( (T)key, after);
    
    // Change front of the list or set before's link to point to new node
    if (before == null) {
      front = newNode;
    }
    else {
      before.setNext(newNode);
    }
    numberOfNodes++;
  }
  
  // Delete a node in the list with a given key value
  @SuppressWarnings("unchecked")
  public Boolean delete( T key ) {
    ListNode<T> before = null;
    ListNode<T> after = null;
    ListNode<T> searchNode;
    searchNode = front;
    
    if( contains(key)){
      while ( (searchNode != null) && (!key.equals(searchNode.getData())) ) {
        before = searchNode;
        searchNode = searchNode.getNext();
      }
      after = searchNode.getNext();
      if ( before == null)
        front = after;
      else
        before.setNext(after);
    }
    else{
      return false;
    }
    
    numberOfNodes--;
    return true;
  }
  
  // Returns true if the linked list is empty, or false otherwise
  public boolean isEmpty() {
    return (front == null);
  }
  
  // Deletes all of the nodes in the linked list
  public void makeEmpty() {
    front = null;
    numberOfNodes = 0;
  }
  
  // Returns the number of nodes in the linked list
  public int size() {
    return numberOfNodes;
  }
  
  // Return String representation of the linked list.
  @SuppressWarnings("unchecked")
  public String toString() {
    ListNode<T> node;
    String linkedList = "FRONT ==> ";
    
    node = front;
    while (node != null) {
      linkedList += "[ " + node.getData() + " ] ==> ";
      node = node.getNext();
    }
    
    return linkedList + "NULL";
  }
}