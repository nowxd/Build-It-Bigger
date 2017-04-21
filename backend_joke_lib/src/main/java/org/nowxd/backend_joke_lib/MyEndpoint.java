/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package org.nowxd.backend_joke_lib;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import org.nowxd.Joker;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend_joke_lib.nowxd.org",
                ownerName = "backend_joke_lib.nowxd.org",
                packagePath = ""
        )
)
public class MyEndpoint {

    private Joker joker;

    public MyEndpoint() {
        joker = new Joker();
    }

    @ApiMethod(name = "getJokeBean")
    public JokeBean getJokeBean() {
        JokeBean jokeBean = new JokeBean();
        jokeBean.setJoke(joker.getJoke());
        return jokeBean;
    }

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

}
