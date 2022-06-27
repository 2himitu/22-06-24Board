package com.lhs.exam.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 계시판 v 0.1 ==" );
        System.out.println("== 프로그램 시작 ==" );
        String cmd;
        int articlesLastId = 0 ;
        Article lastArticle = null;
        ArrayList<Article> articleArrayList = new ArrayList<>();
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
                articleArrayList.add(article);
                lastArticle = article;
                System.out.printf("%d번 계시물이 등록 되었습니다.\n", article.id);
                System.out.printf("새로운 객체 : %s\n", article.toString());

            }else if(cmd.equals("/usr/article/list")){
                if(articleArrayList.size()==0){
                    System.out.println("리스트에 아무것도 없습니다.");
                }else{
                    System.out.println(" - 게시물 리스트 - ");
                    System.out.println("-------------------");
                    System.out.println(" 번호 / 제목 ");
                    System.out.println("-------------------");
                    for (Article article : articleArrayList) {
                        System.out.println(article.id + " / " + article.title);
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
