package com.arslan.asaStarter.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;


/**
 * Created by user on 9/10/2017.
 */

@Configuration
public class CustomRestConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        /**
         * Uncomment and add all entities here. We need Ids to be exposed for proper functionality
         *
         * Example:
         *
         * config.exposeIdsFor(Person.class , Book.class , Library.class);
         */
        /*config.exposeIdsFor(

        );*/
    }
}
