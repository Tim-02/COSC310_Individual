package assignment2;

import java.io.IOException;
import java.net.*;
import java.net.http.*;
import java.net.http.HttpResponse.*;

//simple class that communicates with APIs via URL requests
public class APICommunicator {
    private HttpClient client;
    private HttpRequest req;
    private HttpResponse response;
    private String responseStr;

    public APICommunicator() {
        //build HTTP client
        client = HttpClient.newHttpClient();
    }

    public String getAt(String url) {
        //build request from URL
        req = HttpRequest.newBuilder(URI.create(url)).build();

        try {
            response = client.send(req, BodyHandlers.ofString());

            if(response.statusCode()>=400)
                System.out.println("HTTP request returned with status code " + response.statusCode());
        } catch (IOException e){
            System.out.println(e);
        } catch (InterruptedException e){
            System.out.println(e);
        }

        responseStr = (String)response.body();

        return getResponseStr();
    }

    public String getResponseStr() {
        return responseStr;
    }
}
