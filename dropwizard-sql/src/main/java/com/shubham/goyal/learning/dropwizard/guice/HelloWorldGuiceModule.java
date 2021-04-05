package com.shubham.goyal.learning.dropwizard.guice;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.shubham.goyal.learning.dropwizard.UtilityFunctions;
import com.shubham.goyal.learning.dropwizard.config.HelloWorldConfiguration;
import com.shubham.goyal.learning.dropwizard.dal.ServiceToDalMapper;
import com.shubham.goyal.learning.dropwizard.dal.ServiceToDalMapperImpl;
import com.shubham.goyal.learning.dropwizard.dal.StudentRepositoryImpl;
import com.shubham.goyal.learning.dropwizard.dal.interfaces.StudentRepostitory;
import com.shubham.goyal.learning.dropwizard.services.HelloWorldServiceImpl;
import com.shubham.goyal.learning.dropwizard.services.interfaces.HelloWorldService;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import org.hibernate.SessionFactory;

public class HelloWorldGuiceModule extends AbstractModule {


    @Override
    protected void configure() {
        super.configure();
        bind(HelloWorldService.class).to(HelloWorldServiceImpl.class).in(Singleton.class);
        bind(StudentRepostitory.class).to(StudentRepositoryImpl.class).in(Singleton.class);
        bind(ServiceToDalMapper.class).to(ServiceToDalMapperImpl.class);
    }

    @Provides
    @Singleton
    public HibernateBundle<HelloWorldConfiguration> provideHibernateBundle()
    {
        return new HibernateBundle<HelloWorldConfiguration> (
                ImmutableList.<Class<?>>builder().addAll(UtilityFunctions.getEntityClasses()).build(), new SessionFactoryFactory()) {
            @Override
            public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
                return configuration.getDatabase();
            }
        };
    }

    @Provides
    @Singleton
    public SessionFactory providesSessionFactory(HibernateBundle<HelloWorldConfiguration> bundle){
        return bundle.getSessionFactory();
    }

}
