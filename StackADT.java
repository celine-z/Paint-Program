/*
 * Celine Zhang
 * June 2016
 * Abstract methods for a stack (stack adt)
 */

public interface StackADT<T>
{
  // Adds an element to the top of the stack
  public void push(T element);
  
  // Removes and returns the top element from the stack
  public T pop();
  
  // Returns the top element, without removing it from the stack
  public T peek();
  
  // Returns true if this stack contains no elements, false otherwise
  public boolean isEmpty();
  
  // Returns the number of elements in this stack.
  public int size();
  
  // Returns a String representation of the stack
  public String toString();
  
}