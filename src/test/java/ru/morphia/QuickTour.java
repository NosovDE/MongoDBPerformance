package ru.morphia;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Author:      Dmitriy E. Nosov <br>
 * Date:        15.11.12, 10:34 <br>
 * Company:     Korus Consulting IT<br>
 * Revision:    \$Id$ <br>
 * Description: <br>
 */

public class QuickTour {

    public static void main(String[] args) throws UnknownHostException {

        Mongo mongo = new Mongo("10.3.0.134", 27017);
        Morphia morphia = new Morphia();
        morphia.map(Hotel.class).map(Address.class);
        Datastore ds = morphia.createDatastore(mongo, "my_database");

        //at application start
        //map classes before calling with morphia map* methods
        ds.ensureIndexes(); //creates indexes from @Index annotations in your entities
        ds.ensureCaps(); //creates capped collections from @Entity


        // Now we can use the Datastore instance to save classes with MongoDB. To save a Hotel in Mongo:
        Hotel hotel = new Hotel();
        hotel.setName("My Hotel");
        hotel.setStars((int) (Math.random() * 10));

        Address address = new Address();
        address.setStreet("123 Some street");
        address.setCity("Some city");
        address.setPostCode("123 456");
        address.setCountry("Some country");

        //set address
        hotel.setAddress(address);

        System.out.println("Create hotel = " + hotel);
        // Save the POJO
        ds.save(hotel);


        //Loading a Hotel from Mongo is also simple:

        String hotelId = hotel.getId().toString(); // the ID of the hotel we want to load

        // and then map it to our Hotel object
        hotel = ds.get(Hotel.class, hotel.getId());
        System.out.println("hotelId = " + hotelId);
        System.out.println("hotel = " + hotel);

        //Using a query is just as simple as loading Hotel:
        // it is easy to get four-star hotels.
        List<Hotel> fourStarHotels = ds.find(Hotel.class, "stars >=", 4).asList();
        System.out.println("fourStarHotels = " + fourStarHotels);
        //or
        fourStarHotels = ds.find(Hotel.class).field("stars").greaterThanOrEq(4).asList();
        System.out.println("fourStarHotels = " + fourStarHotels);


    }

}
