package ru.morphia;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

/**
 * Author:      Dmitriy E. Nosov <br>
 * Date:        15.11.12, 10:36 <br>
 * Company:     Korus Consulting IT<br>
 * Revision:    \$Id$ <br>
 * Description: <br>
 */


public class HotelDAO extends BasicDAO<Hotel, String> {
    public HotelDAO(Morphia morphia, Mongo mongo) {
        super(mongo, morphia, "myDB");
    }

    public static void main(String[] args) {
      //  HotelDAO hDAO =...

      //  hDAO.save(new Hotel(...));

    }
}