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
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

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
    public MyBean sayHi(@Named("overskrift") String overskrift, @Named("notatet") String notatet) {
        MyBean response = new MyBean();

        //response.setData("Hi, " + name);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();

        Entity notatInn = new Entity("Notat","jfisaofiaiofoia");
        notatInn.setProperty("overskrift", overskrift);
        notatInn.setProperty("notatet", notatet);
        datastore.put(notatInn);
        memcache.put("jfisaofiaiofoia", notatInn);

        return response;
    }

}
