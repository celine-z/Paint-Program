/*
 * Celine Zhang
 * June 2016
 * Contains the methods of a
 * dynamic stack
 */

class DynamicStack<T> implements StackADT<T> {
  private int top;
  private LinkedList<T> stackArray ;
  
  // Creates an empty stack
  @SuppressWarnings("unchecked")
  public DynamicStack() {
    stackArray = new LinkedList<T>();
  }
  
  // Adds the specified element to the top of the stack
  public void push(T element) {
    stackArray.addFront(element);
  }
  
  // Make the stack empty
  public void makeEmpty(){
    stackArray.makeEmpty();
  }
  
  // Removes element at the top of the stack and returns a reference to it, or null if empty
  public T pop() {
    return stackArray.removeFront();
  }
  
  // Returns a reference to the element at the top of the stack, or null if empty
  public T peek() {
    return stackArray.first();
  }
  
  // Returns true if the stack contains no elements, or false
  public boolean isEmpty() {
    return stackArray.isEmpty();
  }
  
  // Returns the number of elements in the stack.
  public int size() {
    return stackArray.size();
  }
  
  // Returns a String representation of the stack
  public String toString() {
    String result = "Stack Contains: "+stackArray;         
    return result;
  }
}