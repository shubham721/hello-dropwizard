package com.shubham.goyal.learning.dropwizard;

import com.google.common.collect.ImmutableList;
import org.reflections.Reflections;

import javax.persistence.Entity;

public class UtilityFunctions {

    public static ImmutableList<Class<?>> getEntityClasses()
    {
        Reflections reflections = new Reflections("com.shubham.goyal.learning.dropwizard.dal.entities");
        ImmutableList<Class<?>> entities = ImmutableList.copyOf(reflections.getTypesAnnotatedWith(Entity.class));
        return entities;
    }

}
