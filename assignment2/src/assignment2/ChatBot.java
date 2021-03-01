package assignment2;

import java.util.HashSet;

public class ChatBot {
	 /*
     * takes String outputs "intelligent" answer
     */
    public String getResponse(String s){
        String response = "";
        s.toLowerCase();
        HashSet<String> words = StringtoSet(s);
        if(words.contains("what")) {
            if(words.contains("time")) {
                if(words.contains("open")) {
                    response = "We open at 5:00 AM";
                }else if(words.contains("close")) { 
                    response = "We close at 11:00 PM";
                }
            }else { 
            	if(words.contains("trainer")) {
            		response = "Our trainers include rich piana and sylvester Stallone";
                }else if(words.contains("equipment")){
                	response = "Squat Rack, and Bench press... what else would you need?";
                }
            }
            
        }
        return response;   
    }
    /*
     * takes String outputs a hashset of individual words
     */
    public HashSet<String> StringtoSet(String s){
        HashSet<String> words  = new HashSet<>();  
        String[] temp = s.split(" ");
        
        for(int i = 0; i < temp.length; i++) {
            words.add(temp[i]);
        }
        
        return words;
    }
    
    

}
