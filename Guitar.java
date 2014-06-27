import util.StdAudio;


public class Guitar {

	private static String keyboard;;
	private static GuitarString[] gStrings; 

	public Guitar (GuitarString[] gStrings, String keyboard){
		this.keyboard=keyboard;
		this.gStrings=gStrings;
	       for(int i=0;i<keyboard.length();i++){
	            double iNote = 440.0* Math.pow(2, i/12.0);//puts the correct frequency with each string
	            System.out.println(iNote);
	            gStrings[i] = new GuitarString(iNote);
	        }
	}

	
	public synchronized static void notePlayed (char key){
		//synchronized so stdaudio is not accessed by multiple threads at same time, but this also makes
		//sure that the sounds are combined
		if ((keyboard.indexOf(key)>=0)&&(keyboard.indexOf(key)<keyboard.length())){//makes sure key pressed is with keyboard string, so as to not get array out of bounds
     		   gStrings[keyboard.indexOf(key)].pluck();}//pluck the corresponding string of the key pressed
     	   
     			   double sample=0.0;
     			   for (int i=0; i<keyboard.length(); i++){//adds all of the strings to sample to be played
     				   sample+=gStrings[i].sample();        			   
     			   }	

     			   StdAudio.play(sample);
     			   for (int j=0; j<keyboard.length(); j++){//ticks all of the strings
     			   gStrings[j].tick();
     			   }


     		   }
     	   }
	

