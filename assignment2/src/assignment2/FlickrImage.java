package assignment2;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FlickrImage {
    private String id;
    private String secret;
    private String title;
    private String server;
    private String ownerID;
    private String userName;
    private String URL;

    public FlickrImage(String id, String secret, String server, String ownerID, String title){
        setId(id);
        setSecret(secret);
        setServer(server);
        setOwnerID(ownerID);
        setTitle(title);

        if(ownerID!=null||!ownerID.isEmpty()) queryUserName();

        if(id!=null||!id.isEmpty()) makeURL();
    }

    //quick request on Flickr to get username
    public void queryUserName(){
        String url = "https://www.flickr.com/services/rest/?" +
                "method=flickr.people.getInfo" +
                "&api_key=efb2cfcd339c6a9abc7dea3acafa4f37" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&user_id=";
        APICommunicator api = new APICommunicator();
        String body = api.getAt(url + ownerID);

        try {
            JSONObject JSONpointer = (JSONObject) new JSONParser().parse(body);

            //traverse through JSON object to get usernme
            JSONpointer = (JSONObject) JSONpointer.get("person");
            JSONpointer = (JSONObject) JSONpointer.get("username");
            String username = (String) JSONpointer.get("_content");

            setUserName(username);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void makeURL(){
        setURL(String.format("https://live.staticflickr.com/%s/%s_%s.jpg", server, id, secret));
    }

    /*
     * Getters and setters for attributes
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
