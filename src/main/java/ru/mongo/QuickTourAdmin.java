package ru.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;

/**
 * Author:      Dmitriy E. Nosov <br>
 * Date:        15.11.12, 13:02 <br>
 * Company:     <br>
 * Revision:    \$Id$ <br>
 * Description: <br>
 */

public class QuickTourAdmin {
    public static void main(String[] args) throws Exception {

        // connect to the local database server
        Mongo m = new Mongo();

        // Authenticate - optional
        // boolean auth = db.authenticate("foo", "bar");

        // get db names
        for (String s : m.getDatabaseNames()) {
            System.out.println(s);
        }


        // get a db
        DB db = m.getDB("com_mongodb_MongoAdmin");

        // do an insert so that the db will really be created.  Calling getDB() doesn't really take any
        // action with the server
        db.getCollection("testcollection").insert(new BasicDBObject("i", 1));
        for (String s : m.getDatabaseNames()) {
            System.out.println(s);
        }

        // drop a database
        m.dropDatabase("com_mongodb_MongoAdmin");

        for (String s : m.getDatabaseNames()) {
            System.out.println(s);
        }
    }
}
