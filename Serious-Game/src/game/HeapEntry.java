package game;

public class HeapEntry
{
    protected String element; // The entry's data.
    protected int priority; // The entry's priority.


    // Create a new priority queue array entry.

    public HeapEntry()
    {
      element = null;
      priority = 100;
    }


    public HeapEntry (String element, int priority)
    {
      this.element = element;
      this.priority = priority;
    } // PQLinkEntry constructor

    // Return the element of the link entry.
    public String getElement ()
    {
     return element;
    } // getElement method

    // Return the entry's priority.
    public int getPriority ()
    {
     return priority;
    } // getPriority method

    public void setElement(String newElement)
    {
      element = newElement;
    }

    public void setPriority (int newPriority)
    {
      priority = newPriority;
    }

}//HeapEntry