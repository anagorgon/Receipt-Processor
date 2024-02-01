package com.example.receiptprocessor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ReceiptApiClient {
    public String fetchReceiptDetails(String apiUrl) throws IOException, InterruptedException {
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        if(response.statusCode()==200){
            return response.body();
        }else{
            System.out.println("Error fetching receipt details. HTTP status code: "+ response.statusCode());
            return null;
        }
    }
}
