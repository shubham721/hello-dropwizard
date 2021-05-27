package com.shubham.goyal.learning.dropwizard.guice;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.shubham.goyal.learning.dropwizard.entities.MapBinderExperimentKey;
import com.shubham.goyal.learning.dropwizard.services.MapBinderInterfaceExperimentImpl;
import com.shubham.goyal.learning.dropwizard.services.interfaces.MapBinderInterfaceExperiment;

public class MapBinderExperimentGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();
        MapBinder<MapBinderExperimentKey, MapBinderInterfaceExperiment> binder = MapBinder.newMapBinder(
                binder(), MapBinderExperimentKey.class, MapBinderInterfaceExperiment.class);

        binder.addBinding(new MapBinderExperimentKey("shubham", "goyal")).to(
                MapBinderInterfaceExperimentImpl.class);
    }

}
