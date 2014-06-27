

public class GuitarString
{
    public RingBuffer buffer;
    public int numTicks;
    
    public GuitarString(double freq)
    {

    	int q=(int) Math.round(44100.0/freq);
    	buffer = new RingBuffer(q);
        buffer.zeros();
        numTicks=0;
    }

    public synchronized void pluck()
    {
        while(!buffer.isEmpty()) buffer.dequeue();//remove elements from buffer, so buffer size==0
        double addToBuffer;  
        while(!buffer.isFull()){//fill buffer again with random numbers
        	addToBuffer=(Math.random()-0.5);//-.5 so as to keep range -.5 to .5
        	buffer.enqueue(addToBuffer);
    }
    }
    public synchronized void tick()//takes first element of the buffer, adds to next, divides by 2, enqueues it at the end
    {

		double temp;
        temp=buffer.dequeue();
        double p = buffer.peek();
        buffer.enqueue((p+temp)/2);
        numTicks++;
    }
    
    public double sample()
    {
        return buffer.peek();
    }
    
    public int time()
    {
        return numTicks;
    }

    public static void main(String argv[])
    {
    }
}
