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

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

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
    public Notat lagre(@Named("overskrift") String overskrift, @Named("notatet")
        String notatet, @Named("enhetsID") String enhetsID, @Named("token") String token) {


        Date dt = new Date();
        String datoStreng = dt.toString();
        Notat response = new Notat(overskrift, notatet,datoStreng,enhetsID, token);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        if (token.equals("-")) {
            SecureRandom random = new SecureRandom();
            token = new BigInteger(130, random).toString(32);
        }

        Entity notatInn = new Entity("Notat", token);

        notatInn.setProperty("overskrift", overskrift);
        notatInn.setProperty("notatet", notatet);
        notatInn.setProperty("dato", dt.toString());
        notatInn.setProperty("token", token);
        notatInn.setProperty("sisteEndret", dt);
        notatInn.setProperty("enhetsID", response.getEnhetsID());

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


        Query q = new Query("Notat").setFilter(enhetsIDFilter).addSort("dato", Query.SortDirection.DESCENDING);

        PreparedQuery pq = datastore.prepare(q);

        return pq.asList(FetchOptions.Builder.withDefaults());
    }

    /*@ApiMethod(name = "hentNotat",httpMethod = ApiMethod.HttpMethod.GET)
    public Entity hentNotat(@Named("overskrift") String overskrift, @Named("notatet") String notatet,
                           @Named("enhetsID") String enhetsID, @Named("dato") String dato) {

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key k = KeyFactory.createKey("Notat", overskrift + enhetsID + dato);
        try {
            Entity notat = datastore.get(k);
            return notat
        } catch (EntityNotFoundException e) {
            return null;
        }



    }*/

}
