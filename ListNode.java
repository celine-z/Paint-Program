/*
 * Celine Zhang
 * June 2016
 * Contains methods for a node
 */

public class ListNode<T> {
  private T data;
  private ListNode next;
  
  // Constructor if no reference to next node provided 
  public ListNode( T nodeData ) {
    this( nodeData, null);
  }
  
  // Constructor if reference to next node is proved.
  // Set data and reference to next node
  public ListNode( T nodeData, ListNode nodeNext ) {
    data = nodeData;
    next = nodeNext;
  }
  
  // Returns the data for current ListNode object
  public T getData() {
    return data;
  }
  
  // Return reference to next ListNode object
  public ListNode getNext() {
    return next;
  }
  
  // Set a new data for current ListNode object
  public void setData( T newData ) {
    data = newData;
  }
  
  // Set a new reference to the next node object
  public void setNext( ListNode newNext ) {
    next = newNext;
  }
}