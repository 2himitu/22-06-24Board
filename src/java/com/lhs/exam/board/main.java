package com.lhs.exam.board;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    static void makeTestData(ArrayList<Article> article){
        article.add(new Article(1,"제목1","내용1"));
        article.add(new Article(2,"제목2","내용2"));
        article.add(new Article(3,"제목3","내용3"));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 계시판 v 0.1 ==" );
        System.out.println("== 프로그램 시작 ==" );
        int articlesLastId = 0 ;
        Article lastArticle = null;
        ArrayList<Article> articles = new ArrayList<>();

        makeTestData(articles);
        if(articles.size()>0){
            articlesLastId = articles.get(articles.size()-1).id;
        }


        String cmd;
        do {
            System.out.printf("명령) " );
            cmd = sc.nextLine();
            if(cmd.equals("/usr/article/write")) {
                System.out.println("- 게시물등록 -");
                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("내용 : ");
                String body = sc.nextLine();
                int id = articlesLastId + 1;
                articlesLastId = id;
                Article article = new Article(id, title, body);
                articles.add(article);
                lastArticle = article;
                System.out.printf("%d번 계시물이 등록 되었습니다.\n", article.id);
                System.out.printf("새로운 객체 : %s\n", article.toString());

            }else if(cmd.equals("/usr/article/list")){
                if(articles.size()==0){
                    System.out.println("리스트에 아무것도 없습니다.");
                }else{
                    System.out.println(" - 게시물 리스트 - ");
                    System.out.println("-------------------");
                    System.out.println(" 번호 / 제목 ");
                    System.out.println("-------------------");
                    for (int i = articles.size()-1;i >= 0;i--) {
                        System.out.println(articles.get(i).id + " / " + articles.get(i).title);
                    }
                }

            }else if(cmd.equals("/usr/article/detail")){
                if(lastArticle==null){
                    System.out.println("게시물이 없습니다.");
                }else{
                    Article article = lastArticle;
                    System.out.println("- 게시물상제보기 -");
                    System.out.printf("번호 : %d\n",article.id);
                    System.out.printf("제목 : %s\n",article.title);
                    System.out.printf("내용 : %s\n",article.body);
                }

            }else{
                System.out.printf("입력된 명령어 : %s\n",cmd);
            }


        }
        while(!cmd.equals("exit"));
        System.out.println("== 프로그램 종료 ==" );
        sc.close();
    }
}
class Article {
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
