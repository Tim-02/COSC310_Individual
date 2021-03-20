package assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//new package imported that allows for Regular Expressions
import java.util.regex.*;

public class ChatBot {
	
	//hash map "rules" containing tuples of (keywords, response)
	//notice that for multiple keywords stored in ArrayList, bot has one response
	private Rule rules;
	
	public ChatBot() {
		//initializing rules with one tuple
		// TODO: find a better way to get new entries here (maybe from json file?)
		rules = new Rule();
	}
	
	 /*
     * takes String outputs "intelligent" answer
     */
    public String getResponse(String input){
        //loop through all possible responses
        for(ArrayList<String> keywords : rules.keySet()) {
        	//build a keyword pattern for each response (regex standard)
        	String pattern_str = String.join("\\b|\\b", keywords);
        	pattern_str = String.format("\\b%s\\b", pattern_str);
        	Pattern pattern = Pattern.compile(pattern_str, Pattern.CASE_INSENSITIVE);
        	
        	//match with input
        	Matcher matcher = pattern.matcher(input);
        	
        	while(matcher.find()) {
        		//if match found, return respective response from rules
        		return rules.get(keywords);
        	}
        }
        return notUnderstood();   
    }  
    
    public String notUnderstood() {
    	int random = (int) (Math.random() * 5);
    	String[] responses ={
    			"Sorry, I didn't quite get that", 
    			"Sorry, I'm a little confused. Try again?",
    			"I did not understand your query",
    			"My apologies, I am not sure what you are trying to ask",
    			"I don't recognize what you are trying to ask"
    			};
		return responses[random];
    }

}
