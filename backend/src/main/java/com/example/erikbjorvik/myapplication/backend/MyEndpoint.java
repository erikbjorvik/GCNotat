/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.erikbjorvik.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.myapplication.erikbjorvik.example.com",
    ownerName = "backend.myapplication.erikbjorvik.example.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "lagre",httpMethod = ApiMethod.HttpMethod.POST)
    public Notat lagre(@Named("overskrift") String overskrift, @Named("notatet") String notatet,
                        @Named("enhetsID") String enhetsID) {

        Notat response = new Notat();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
             Date dato = new Date();
        Key k = KeyFactory.createKey("Notat", overskrift + enhetsID + dato.toString());

        Entity notatInn = new Entity("Notat", k);
        notatInn.setProperty("overskrift", overskrift);
        notatInn.setProperty("notatet", notatet);
        notatInn.setProperty("dato", new Date());
        notatInn.setProperty("enhetsID", enhetsID);
        datastore.put(notatInn);

        return response;
    }

    @ApiMethod(name = "hentNotater",httpMethod = ApiMethod.HttpMethod.GET)
    public List<Entity> hentNotater(@Named("enhetsID") String enhetsID) {

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query.Filter enhetsIDFilter =
                new Query.FilterPredicate("enhetsID",
                        Query.FilterOperator.EQUAL,
                        enhetsID);


        Query q = new Query("Notat").setFilter(enhetsIDFilter);

        PreparedQuery pq = datastore.prepare(q);
        /*Notat response = new Notat();


        for (Entity result : pq.asIterable()) {
            String overskrift = (String) result.getProperty("overskrift");
            String notatet = (String) result.getProperty("notatet");
            Date dato = (Date) result.getProperty("dato");


            response.setData(overskrift, notatet, dato, enhetsID);
        }

        return response;*/
        return pq.asList(FetchOptions.Builder.withLimit(5));
    }

}
