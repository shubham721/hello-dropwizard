package com.shubhamgoyal.learning.hystrix.javanica.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class HystrixJavanicaConfiguration extends Configuration {
    private String template;

    private String defaultName = "Stranger";

    private String hystrixConfig = "";

    public String getHystrixConfig() {
        return hystrixConfig;
    }

    public void setHystrixConfig(String hystrixConfig) {
        this.hystrixConfig = hystrixConfig;
    }

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
}
