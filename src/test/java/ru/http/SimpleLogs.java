package ru.http;

import org.testng.annotations.Test;

import java.io.IOException;


/**
 * Author:      Dmitriy E. Nosov <br>
 * Date:        30.01.13, 19:11 <br>
 * Company:     Korus Consulting IT<br>
 * Description:  <br>
 */
public class SimpleLogs {

    public static final int ROWS = 10000;
    public static final int THREADS = 100;

    @Test(enabled = true)
    public void test() {
        HttpClientPost post = new HttpClientPost();
        post.send();
    }
}
