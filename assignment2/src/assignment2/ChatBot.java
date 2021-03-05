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
	private HashMap<ArrayList<String>, String> rules = new HashMap<ArrayList<String>, String>();
	
	public ChatBot() {
		//initializing rules with one tuple
		// TODO: find a better way to get new entries here (maybe from json file?)
		ArrayList<String> temp = new ArrayList<String>(Arrays.asList("hi", "hello", "sup", "what's up", "hey"));
		
		
		rules.put(temp, "Hi, welcome to GymBot! How can I help you?");
		
		temp = new ArrayList<String>(Arrays.asList("time", "timing", "hours", "days", "open", "opened", "openning", "opens", "close", "closed", "closes", "closing"));
		rules.put(temp, "Are you interested in working hours? The Gym is open from 5:00 am to 10 pm.");
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
        return "sorry I didn't quite get that";   
    }  

}
