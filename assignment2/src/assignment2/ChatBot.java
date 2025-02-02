package assignment2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;

//new package imported that allows for Regular Expressions
import java.util.regex.*;

public class ChatBot {

	//hash map "rules" containing tuples of (keywords, response)
	//notice that for multiple keywords stored in ArrayList, bot has one response
	private Rule rules;
	private SentimentAnalyzer sentiment;
	private Stemmer stemmer;
	private PersonFinder personFinder;
	private FlickrImage img;
  
	public ChatBot() {
		rules = new Rule();
		sentiment = new SentimentAnalyzer();
		stemmer = new Stemmer();
		personFinder = new PersonFinder();
		setImg(null);
	}

	/*
	 * takes string and returns same string with stemmed words
	 */

	public String stemInput(String input) {
		//initialize final result
		String output = "";
		//Create an array of words from the input string by splitting them by spaces
		String[] inputArray = input.split("\\s+");
		//loop through the words in the array
		for (String word:inputArray) {
			//add word to the stemmer by character
			stemmer.add(word.toCharArray(), word.length());
			stemmer.stem();
			//add newly stemmed word to the output with a space
			output += stemmer.toString() + " ";
		}
		return output;
	}

	 /*
     * takes String outputs "intelligent" answer
     */
    public String getResponse(String input){

    	// get input to lowercase
		input = input.toLowerCase();

    	String[] words = input.split("\\s+");
    	// if first sentence in sentence is addressing bot
    	if(words[0].equalsIgnoreCase("you")) {
    		return addressFeedback(input);
    	}
		// check to see if a person was mentioned in input
		boolean personRefernce = personFinder.findPerson(input);

		// if a person name was metioned replace the input with the new string which changes any name to person
		input = (personRefernce)? personFinder.getSentence() :input;
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
        		// if person Refernce is true then replace word person from output with the proper name
        		if(personRefernce) {
        			return personFinder.replaceNameWithPerson(rules.get(keywords), true);
        		}
        		return rules.get(keywords);
        	}
        }

        /*
         * If no keywords found, check for nouns and show either:
         *  - Wikipedia extract of related word
         *  - Flickr image of related word
         */
		String noun = POSTagger.findNoun(input);
		if(noun!=null) {
			switch((int)Math.round(Math.random())) {
				case 0:
					//chatbot will query first noun it finds on flickr
					FlickrImage flickrResponse = flickrQuery(noun);
					if (flickrResponse != null) {
						img = flickrResponse;
						return "Not quite sure, but I found a picture of "
								+ noun
								+ " by @"
								+ flickrResponse.getUserName()
								+ ", check this out!\n[press ASK to continue]";
					}

				case 1:
					//chatbot will query first noun it finds in wikipedia
					String wikiResponse = wikiQuery(noun);
					if (wikiResponse != null && !wikiResponse.isEmpty())
						return "I didn't quite get that, but here is what I know about " + noun + ": " + wikiResponse;
			}
		}

        //if no nouns found then it uses default answers
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

    // takes a string addressing the bot specificly and outputs will display apropriate response
    public String addressFeedback(String input) {

    	int rating  = sentiment.analyze(input);
    	switch(rating){
    	case 0:
    		return "Sorry to upset you <3, how about we go for dinner and fix this up?";
    	case 1:
    		return "jeez, I thought we were friends! I still love you.";
    	case 2:
    		return "noted, anymore questions ma'am?";
    	case 3:
    		return "thank you dear, anymore questions?";
    	case 4:
    		return "That is the nicest thing anyone has ever said to me <3";
    	}
    	return "this should never be called";
    }

    //method that makes query to wiki API using noun from user
    public String wikiQuery(String noun){
		//Link to API request, "titles" must be set to article title
		String url = "https://en.wikipedia.org/w/api.php?" +
				"action=query" +
				"&format=json" +
				"&prop=extracts" +
				"&explaintext=true" +
				"&exsentences=2" +
				"&titles=";
		String response = null;

		//Opens API communicator that makes HTTP requests
    	APICommunicator api = new APICommunicator();
		String body = api.getAt(url + noun);

		try {
			//create a json object based on string received from API
			JSONObject JSONpointer = (JSONObject) new JSONParser().parse(body);

			//access inside JSON string to reach pageID
			JSONpointer = (JSONObject) JSONpointer.get("query");
			JSONpointer = (JSONObject) JSONpointer.get("pages");
			String pageid = (String) JSONpointer.keySet().iterator().next();

			if(!pageid.equals("-1")) {
				JSONpointer = (JSONObject) JSONpointer.get(pageid);
				response = (String) JSONpointer.get("extract");
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return response;
	}

	//method that makes query to Flickr to find an image url based on keyword noun
	public FlickrImage flickrQuery(String noun){
		FlickrImage photo = null;
    	String url =  "https://www.flickr.com/services/rest/?" +
				"method=flickr.photos.search" +
				"&api_key=efb2cfcd339c6a9abc7dea3acafa4f37" +
				"&safe_search=1" +
				"&per_page=1" +
				"&format=json" +
				"&nojsoncallback=1" +
				"&tags=";
    	String photoURL = null;
    	//opens API communicator that makes HTTP requests
    	APICommunicator api = new APICommunicator();
    	String body = api.getAt(url + noun);

		try {
			//create a json object based on string received from API
			JSONObject JSONpointer = (JSONObject) new JSONParser().parse(body);

			//traverse through object to reach photo information
			JSONpointer = (JSONObject) JSONpointer.get("photos");
			JSONArray temp = (JSONArray) JSONpointer.get("photo");
			JSONpointer = (JSONObject) temp.get(0);

			String photoID = (String) JSONpointer.get("id");
			String serverID = (String) JSONpointer.get("server");
			String secretID = (String) JSONpointer.get("secret");
			String ownerID = (String) JSONpointer.get("owner");
			String title = (String) JSONpointer.get("title");

			photo = new FlickrImage(photoID, secretID, serverID, ownerID, title);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return photo;
	}

	public FlickrImage getImg() {
		return img;
	}

	public void setImg(FlickrImage img) {
		this.img = img;
	}

	public boolean imgIsNull() {
    	return (img==null);
	}
}
