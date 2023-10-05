package com.baraq.merchantsystem.dao;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ConnectionPoolExample {

    public static ConnectionPool getConnectionPool(int maxIdleConnection, int keepAliveDuration, java.util.concurrent.TimeUnit timeUnit){
        ConnectionPool connectionPool = new ConnectionPool(5, 5, TimeUnit.MINUTES);
        return connectionPool;
    }
    public static void main(String[] args) throws IOException {
        ConnectionPool connectionPool = getConnectionPool(5,5,TimeUnit.MINUTES);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(connectionPool)
                .build();
        for (int i = 0; i < 10; i++) {
            Request request = new Request.Builder()
                    .url("https://www.amazon.in/s?k=bluetooth+headphones&crid=2S30MMGV4IASH&sprefix=bluetooth+headphones%2Caps%2C228&ref=nb_sb_noss_1")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    System.out.println("Request " + (i + 1) + " was successful.");
                } else {
                    System.out.println("Request " + (i + 1) + " failed.");
                }
            }
        }

        // Clean up the connection pool when done.
        connectionPool.evictAll();
    }
}
