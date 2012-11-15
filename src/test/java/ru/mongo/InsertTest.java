package ru.mongo;


import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.UnknownHostException;

/**
 * Author:      Dmitriy E. Nosov <br>
 * Date:        14.11.12, 18:35 <br>
 * Company:     <br>
 * Revision:    \$Id$ <br>
 * Description: <br>
 */

public class InsertTest {

    final static Logger logger = LoggerFactory.getLogger(InsertTest.class);

    private Mongo mongo = null;
    private DB db = null;

    @BeforeSuite
    public void init() {
        try {
            // соединимся с базой
            mongo = new Mongo("10.3.0.134", 27017);
            db = mongo.getDB("mydb");
            logger.info("Start suite!!!");

        } catch (UnknownHostException e) {
            logger.error("UnknownHostException e " + e, e);
        }

    }


    @Test(enabled = false)
    public void insert() {
        // откуда хотим
        final DBCollection coll = db.getCollection("persons");
        long start = System.currentTimeMillis();
        System.out.println(" start " + start);
        for (int i = 0; i < 30_000_000; i++) {
            //Create and insert document to collection
            DBObject document = new BasicDBObject();
            document.put("name", String.format("%07d", (int) (Math.random() * 10000)));
            document.put("age", String.format("%03d", (int) (Math.random() * 100)));

            final WriteResult insertResult = coll.insert(document);

            //    System.out.println("" + i + ". insert " + document.toString() + " --> result: " + insert.getLastError());
            if (i % 10000 == 0)
                System.out.println("" + i + ". insert " + document.toString() + " --> result: " + insertResult.getLastError());
        }
        long stop = (System.currentTimeMillis() - start) / 1000;
        System.out.println(" stop " + stop + " sec");
        System.out.println("");
        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("");
        System.out.println("");
    }


    @Test
    public void find() {
        final DBCollection coll = db.getCollection("persons");
        long start = System.currentTimeMillis();
        System.out.println(" start search " + start);

        for (int i = 0; i < 1_000_000; i++) {
            String name = String.format("%07d", (int) (Math.random() * 10000));
            String age = String.format("%03d", (int) (Math.random() * 100));
            // сформулируем запрос - что хотим
            final BasicDBObject query = new BasicDBObject();
//                query.put("name", name);
            query.put("age", age);

            // исполним запрос, получим в буфер
            final DBCursor cursor = coll.find(query);
            int j = 0;
            // пробежимся по всем записям
            while (cursor.hasNext()) {
                System.out.println("---------------------");
                final DBObject item = cursor.next();
                System.out.println("" + (j++) + ". find " + item);
            }
            System.out.println("---------------------");
            // освободим буфера
            cursor.close();
        }
        long stop = (System.currentTimeMillis() - start) / 1000;
        System.out.println(" stop " + stop + " sec");
        System.out.println("");
        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("");
        System.out.println("");

    }


    @AfterSuite
    public void stop() {
        // отпустим базу
        if (mongo != null) {
            mongo.close();
        }

    }

}
