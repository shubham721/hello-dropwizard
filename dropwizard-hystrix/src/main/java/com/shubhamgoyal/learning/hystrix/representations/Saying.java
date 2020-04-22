package com.shubhamgoyal.learning.hystrix.representations;

public class Saying {

    private  long id;

    private String content;

    public  Saying(){

    }

    public Saying(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
