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
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import java.util.Date;

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
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("overskrift") String overskrift, @Named("notatet") String notatet,
                        @Named("enhetsID") String enhetsID) {
        MyBean response = new MyBean();
        Date dato = new Date();
        //response.setData("Hi, " + name);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        //MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();

        Key k = KeyFactory.createKey("Notat", overskrift+enhetsID+dato.toString());

        Entity notatInn = new Entity("Notat", k);
        notatInn.setProperty("overskrift", overskrift);
        notatInn.setProperty("notatet", notatet);
        notatInn.setProperty("datoOpprettet", dato);
        notatInn.setProperty("enhetsID", enhetsID);
        datastore.put(notatInn);

        //memcache.put("jfisaofiaiofoia", notatInn);

        return response;
    }

}
