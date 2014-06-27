    
     
     
    public class RBTest {
     
    	public static boolean eq(double x, double y) {
    		/* You can't test doubles with the equality operator very well
    		   because of rounding issues, so we do a shady test to see if they are close.
    		   This is super shady, and your real code should never do something like it...
    		*/
    		double epsilon = 0.01;
    		if(Math.abs(x-y) < epsilon) {
    			return true;
    		}
    		else {
    			return false;
    		}
     
    	}
     
        public static void main(String[] args) {
    		RingBuffer buff = new RingBuffer(10);
    		boolean failed = false;
     
    		double vals[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
     
     
    		for(int i=0; i < 5; i++) {
    			buff.enqueue(vals[i]);
    		}
    		System.out.println("Added items... Checking size");
     
    		if(buff.size() != 5) {
    			System.out.println("Error with size() function!");
    			failed = true;
    		}
    		System.out.println("Removing entires");
     
    		for(int i=0; i < 5; i++) {
    			if(!eq(buff.dequeue(), vals[i])){
    				System.out.println("Error removing item " + i + "!");
    				failed = true;
    			}
    		}
    		System.out.println("Trying to peek");
    		buff.enqueue(1234.567);
     
    		if(buff.peek() != buff.dequeue()) {
    			failed = true;
    			System.out.println("Error with peek/dequeue function!");
    		}
     
    		System.out.println("Adding until full");
    		int j=0;
    		while(!buff.isFull()) {
    			buff.enqueue(Math.PI*j);
    			j++;
    		}
    		System.out.println("Removing until empty...");
    		while(!buff.isEmpty()) {
    			buff.dequeue();
    		}
    		System.out.println("Done tests.");
    		if(failed) {
    			System.out.println("Sorry, you failed at least one test.");
    		}
    		else{
    			System.out.println("Hooray you passed all tests!");
    		}
     
    	}
    }

