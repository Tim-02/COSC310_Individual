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
            response = containWhat(words);
        }else if (words.contains("where")) { 
            response = containWhere(words);
        }else if (words.contains("who")) {
        	response = containWho(words);
        }else if (words.contains("how")) {
        	response = containHow(words);
        }else if (words.contains("when")) {
        	response = containWhen(words);
        }else
        	response = "Visit our front desk for more information";
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
    
    
 /*
  * Responses sectioned by WHAT, WHERE, WHO, HOW, WHEN
  */
    
    //WHAT
    public String containWhat (HashSet<String> s) {
    	//default response 
    	String response = "Contact our help desk";
    	// WHAT -> TIME -> OPEN/CLOSE
    	if (s.contains("time")) {
    		if (s.contains("open")) {
    			response = "we open at 5:00 AM";
    		}else if (s.contains("close")){
    			response = "we close at 11:00PM";
    		}else {
    			response = "Please contact our help desk for more time information";
    		}
    	}
    	
    	// WHAT -> EQUIMENT -> HAVE/RECOMMEND/COST
    	else if (s.contains("equipment")) {
    		if (s.contains("have")) {
    			response = "Our gym include world class equipments personally used by Sylvester Stallone";
    		} else if (s.contains("recommend")) {
    			response = "Our cardio section was just updated with new treadmills!";
    		} else if (s.contains("cost")) {
    			response = "All of our equipment is free to use with the gym membership";
    		} else {
    			response ="For further equipment inquiries, please contact our front desk";
    		}
    	}
    	//WHAT -> TRAINER
    	else if (s.contains("trainer")){
    		response = "Our trainers include rich piana and sylvester Stallone";
    	}
    	return response;
    }
    //WHERE
    public String containWhere (HashSet<String> s) {
    	String response = "Visit our website for map of our facility";
    	//WHERE -> WASHROOM -> MEN/WOMAN
    	if (s.contains("washroom")) {
    		if (s.contains("men")) {
    			response = "Men's washroom is near the exit";
    		} else
    			response = "Woman's washroom is near locker room";
    	//WHERE -> HELP DESK
    	} else if (s.contains("help desk")) {
    		response = "Help desk is located near the entrance";
    	//WHERE -> SHOWER
    	} else if (s.contains("shower")) {
    		response = "Shower is located in the locker room";
    	}
    	return response;
     }
    
    //WHO
    public String containWho (HashSet<String> s) {
    	String response = "For more staff information, visit our help desk";
    	//WHO -> TRAINER
    	if (s.contains("trainer")) {
    		response = "Our trainers are Our trainers include rich piana and sylvester Stallone";
    	//WHO -> OWNER
    	}else if (s.contains("owner")) {
    		response = "Our owner is Arnold Schwarzenegger";
    	//WHO -> MEMBER
    	}else if (s.contains("member")) {
    		response = "Anyone over the age of 18 can join and become a member";
    	}
    
    	return response;
    }
    public String containHow (HashSet<String> s) {
    	String response = "For how to, please visit our website";
    	//HOW -> USE
    	if (s.contains("use")) {
    		//HOW -> USE -> EQUIPMENT
    		if (s.contains("equipment")) {
    			response = "Contact our trainers for instruction to use equipment";
    		//HOW -> USE -> SHOWER
    		} else if (s.contains("shower")) {
    			response = "Take off your cloth and stand in the water lol";
    		} else
    			response = "For more info on how to use services offered, visit front desk";
    	//HOW -> GET
    	}else if (s.contains("get")) {
    		//HOW -> GET -> MEMBERSHIP
    		if (s.contains("membership")) {
    			response = "you can register for membership on our website";
    		//HOW -> GET -> DISCOUNT
    		}else if (s.contains("discount")) {
    			response = "Check our website for discounts";
    		}
    	}
    	return response;
    }
    public String containWhen (HashSet<String> s) {
    	String response = "For more time information, visit our website";
    	if (s.contains("open")){
    		response = "we open at 5:00 AM";
    	}else if (s.contains("close")) {
    		response = "we close at 11:00 PM";
    	}
    	return response;
    }
    
    

}
