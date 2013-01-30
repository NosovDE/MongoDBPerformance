package ru.http;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


/**
 * Author:      Dmitriy E. Nosov <br>
 * Date:        30.01.13, 19:11 <br>
 * Company:     Korus Consulting IT<br>
 * Description:  <br>
 */
public class SimpleLogs {

    @Test(enabled = true)
    public void test() throws IOException {


        for (int n = 0; n < 10; n++) {
            final int num = n;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000000; i++) {
                        post(num);
                    }
                    System.out.println("Complete: " + num);
                }
            }).start();
        }
        System.out.println("Started!");
        System.in.read();

    }

    private void post(int name) {
        HttpClient client = new DefaultHttpClient();
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
