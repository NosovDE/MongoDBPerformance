package ru.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


/**
 * Author:      Dmitriy E. Nosov <br>
 * Date:        31.01.13, 18:19 <br>
 * Company:     Korus Consulting IT<br>
 * Description:  <br>
 */
public class HttpClientGet {

    public void send() {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://www.vogella.com");
            HttpResponse response = client.execute(request);

            // Get the response
            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
               // textView.append(line);
            }
        } catch (Exception e) {

        }
    }
}
