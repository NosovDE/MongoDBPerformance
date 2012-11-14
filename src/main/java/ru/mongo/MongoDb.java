package ru.mongo;

/**
 * Author:      Dmitriy E. Nosov <br>
 * Date:        13.11.12, 10:54 <br>
 * Company:     Korus Consulting IT<br>
 * Revision:    \$Id$ <br>
 * Description: <br>
 */

import com.mongodb.*;


public class MongoDb {

    public static void main(String[] args) {
        Mongo mongo = null;
        try {
            // соединимся с базой
            mongo = new Mongo("10.3.0.134", 27017);
            final DB db = mongo.getDB("mydb");

            // откуда хотим
            final DBCollection coll = db.getCollection("persons");
            long start = System.currentTimeMillis();
            System.out.println(" start " + start);
            /* for (int i = 0; i < 30_000_000; i++) {
                //Create and insert document to collection
                DBObject document = new BasicDBObject();
                document.put("name", String.format("%07d", (int) (Math.random() * 10000)));
                document.put("age", String.format("%03d", (int) (Math.random() * 100)));

                final WriteResult insert = coll.insert(document);

                //    System.out.println("" + i + ". insert " + document.toString() + " --> result: " + insert.getLastError());
        //        if (i % 10000 == 0)
          //          System.out.println("" + i + ". insert " + document.toString() + " --> result: " + insert.getLastError());
            }*/
            long stop = (System.currentTimeMillis() - start) / 1000;
            System.out.println(" stop " + stop + " sec");
            System.out.println("");
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("");
            System.out.println("");

            start = System.currentTimeMillis();
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
            stop = (System.currentTimeMillis() - start) / 1000;
            System.out.println(" stop " + stop + " sec");
            System.out.println("");
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("");
            System.out.println("");

            // отпустим базу
            mongo.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mongo != null) mongo.close();
        }
    }


}
