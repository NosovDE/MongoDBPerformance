package ru.http;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:      Dmitriy E. Nosov <br>
 * Date:        31.01.13, 18:21 <br>
 * Company:     Korus Consulting IT<br>
 * Description:  <br>
 */
public class HttpClientPost {
    public static final int ROWS = 10000;
    public static final int THREADS = 100;


    public void send() {
        try {
            for (int n = 0; n < THREADS; n++) {
                final int num = n;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long begin = System.currentTimeMillis();
                        for (int i = 0; i < ROWS; i++) {
                            post(num);
                        }
                        System.out.println("Complete: " + num + " thread. Time: "
                                + (System.currentTimeMillis() - begin) / 1000 + " sec for " + ROWS + " row(s)");
                    }
                }).start();
            }
            System.out.println("Started!");
            System.in.read();
        } catch (Exception e) {

        }
    }

    private void post(int name) {
        // set the connection timeout value to 30 seconds (30000 milliseconds)
        final HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 60000);

        HttpClient client = new DefaultHttpClient(httpParams);
        HttpPost post = new HttpPost("http://10.2.0.182/api/entry/");

        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
            nameValuePairs.add(
                    new BasicNameValuePair(
                            "data",
                            "{\"Entry info\": \"Thread num " + name + ". Some message " + String.format("%07d", (int) (Math.random() * 10000)) + "\"}"));
            nameValuePairs.add(new BasicNameValuePair("owner", "{\"ip\": \"127.0.0.1\", \"user\": \"root\"}"));
            nameValuePairs.add(new BasicNameValuePair("level", "info"));
            nameValuePairs.add(new BasicNameValuePair("tags", "[\"me\", \"java\"]"));

            // System.out.println("nameValuePairs = " + nameValuePairs);
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                //  System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
