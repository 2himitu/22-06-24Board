package com.lhs.exam.board;

public class Article {
    int id ;
    String title;
    String body;

    Article(){}

    Article(int id, String title, String body) {
        this.id= id;
        this.title= title;
        this.body= body;
    }
    @Override
    public String toString (){
        return String.format("\n{id:%d\n, title: %s\n, body: %s}\n",id,title,body);
    }
}
