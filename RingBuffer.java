

public class RingBuffer
{
    double[] buffer;
	public int bufferCapacity;
    public int first;
    public int last;
    public int bufferSize;
    
    public RingBuffer(int capacity)
    {
        buffer = new double[capacity];
        bufferCapacity = capacity;
        first = 0;
        last = 0;
        bufferSize = 0;
    }
    
    public void zeros(){//adds zeros to the buffer
    	for (int i=0; i<bufferCapacity; i++){
    		buffer[i]=0.0;
    	}
    }
    
	public int size()
    {
        return bufferSize;
    }
    
    public boolean isEmpty()
    {
        int x=size();
        if (x==0){
        	return true;
        }
        return false;
    }
    
    public boolean isFull()
    {
        int x=size();
        if (x==bufferCapacity){
        	return true;
        }
        return false;
    }
    
    public void enqueue(double itemX)
    {
        buffer[last] = itemX;
        last++;
        if (last==bufferCapacity) {
        	last=0;//loops back around
        }
        bufferSize++;//increment size each time something is added
    }
    
    public double dequeue()
    {
        double returnValue = buffer[first];//temporarily saves it, so can return original first value
        first++; 
        if (first==bufferCapacity) 
        {first=0;
        }//loops back around
        
        bufferSize--;//decrement size each time something is removed
        return returnValue;
    }

    public double peek()
    {
        return buffer[first];//returns first element of buffer
    }
    
    public static void main(String argv[]) 
    {
    }
}