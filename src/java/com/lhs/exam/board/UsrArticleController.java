package com.lhs.exam.board;

import java.util.ArrayList;
import java.util.List;

public class UsrArticleController {


    int articlesLastId ;
    ArrayList<Article> articles ;

    UsrArticleController (){
        articlesLastId = 0 ;
        articles = new ArrayList<>();
        makeTestData();
        makeTestData();
        if(articles.size()>0){
            articlesLastId = articles.get(articles.size()-1).id;
        }
    }

    void makeTestData(){
        for(int i = 1 ; i <= 100 ; i ++){
            articles.add(new Article(i,"제목"+i,"내용"+i));
        }
    }

    public void actionWrite() {
        System.out.println("- 게시물등록 -");
        System.out.printf("제목 : ");
        String title = Container.sc.nextLine();
        System.out.printf("내용 : ");
        String body = Container.sc.nextLine();
        int id = articlesLastId + 1;
        articlesLastId = id;
        Article article = new Article(id, title, body);
        articles.add(article);
        System.out.printf("%d번 계시물이 등록 되었습니다.\n", article.id);
        System.out.printf("새로운 객체 : %s\n", article.toString());
    }

    public void actionDetail(Rq  rq) {
        if(rq.getParams().containsKey("id")==false){
            System.out.println("아이디를 입력 해주세요");
            return;
        }
        int id=0;

        try {
            id = Integer.parseInt(rq.getParams().get("id"));
        }catch (NumberFormatException e){
            System.out.println("아이디를 양의 정수로 입력 해주세요");
            return;
        }

        if(id> articles.size()){
            System.out.println("게시물이 없습니다.");
            return;
        }else{
            Article article = articles.get(id-1);
            System.out.println("- 게시물상제보기 -");
            System.out.printf("번호 : %d\n",article.id);
            System.out.printf("제목 : %s\n",article.title);
            System.out.printf("내용 : %s\n",article.body);
        }
    }

    public void actionList(Rq  rq) {
        if(articles.isEmpty()){
            System.out.println("리스트에 아무것도 없습니다.");
        }else {
            System.out.println(" - 게시물 리스트 - ");
            System.out.println("-------------------");
            System.out.println(" 번호 / 제목 ");
            System.out.println("-------------------");

            List<Article> filteredArticles = articles;

            if(rq.getParams().containsKey("searchKeyword")){
                String searchKeyword = rq.getParams().get("searchKeyword");
                filteredArticles = new ArrayList<>();
                for(Article article : articles){
                    boolean mathed = article.title.contains(searchKeyword) || article.body.contains(searchKeyword) ;
                    if(mathed){
                        filteredArticles.add(article);
                    }

                }
            }


            List<Article> sortedArticles = filteredArticles;
            boolean OrderByIdDesc = true;
            if (rq.getParams().containsKey("orderBy") && rq.getParams().get("orderBy").equals("idAsc")) {
                OrderByIdDesc = false;
            }
            if (OrderByIdDesc) {
                sortedArticles = Util.reverseList(sortedArticles);
            }
            for (Article article : sortedArticles) {
                System.out.println(article.id + " / " + article.title);
            }

        }
    }
}
