package com.example.chirag.networkdemo;

/**
 * Created by Chirag on 01-10-2017.
 */

public class Post {

    int userId;
    int id;
    String title;
    String body;

    public Post(int userId,int id,String title,String body){
        this.id=id;
        this.userId=userId;
        this.title=title;
        this.body=body;
    }

}
