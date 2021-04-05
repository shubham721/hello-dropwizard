package com.shubham.goyal.learning.dropwizard.config;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldConfiguration extends Configuration {

    private DataSourceFactory database;

    private SwaggerBundleConfiguration swagger;
}
