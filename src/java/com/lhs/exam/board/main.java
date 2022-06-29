package com.lhs.exam.board;

import java.util.*;

public class main {
    static void makeTestData(ArrayList<Article> article){
        for(int i = 1 ; i <= 100 ; i ++){
            article.add(new Article(i,"제목"+i,"내용"+i));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 계시판 v 0.1 ==" );
        System.out.println("== 프로그램 시작 ==" );
        int articlesLastId = 0 ;
        ArrayList<Article> articles = new ArrayList<>();

        makeTestData(articles);
        if(articles.size()>0){
            articlesLastId = articles.get(articles.size()-1).id;
        }


        String cmd;
        Rq  rq;

        do {
            System.out.printf("명령) " );
            cmd = sc.nextLine();

            rq = new Rq(cmd);

            if(rq.getUrlPath().equals("/usr/article/write")) {
                System.out.println("- 게시물등록 -");
                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("내용 : ");
                String body = sc.nextLine();
                int id = articlesLastId + 1;
                articlesLastId = id;
                Article article = new Article(id, title, body);
                articles.add(article);
                System.out.printf("%d번 계시물이 등록 되었습니다.\n", article.id);
                System.out.printf("새로운 객체 : %s\n", article.toString());

            }else if(rq.getUrlPath().equals("/usr/article/list")){
                if(articles.isEmpty()){
                    System.out.println("리스트에 아무것도 없습니다.");
                }else {
                    System.out.println(" - 게시물 리스트 - ");
                    System.out.println("-------------------");
                    System.out.println(" 번호 / 제목 ");
                    System.out.println("-------------------");

                    boolean OrderByIdDesc = true;
                    List<Article> sortedArticles = articles;
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
            }else if(rq.getUrlPath().equals("/usr/article/detail")){
                if(rq.getParams().containsKey("id")==false){
                    System.out.println("아이디를 입력 해주세요");
                    continue;
                }
                int id=0;

                try {
                    id = Integer.parseInt(rq.getParams().get("id"));
                }catch (NumberFormatException e){
                    System.out.println("아이디를 양의 정수로 입력 해주세요");
                    continue;
                }

                if(id> articles.size()){
                    System.out.println("게시물이 없습니다.");
                    continue;
                }else{
                    Article article = articles.get(id-1);
                    System.out.println("- 게시물상제보기 -");
                    System.out.printf("번호 : %d\n",article.id);
                    System.out.printf("제목 : %s\n",article.title);
                    System.out.printf("내용 : %s\n",article.body);
                }

            }else{
                System.out.printf("입력된 명령어 : %s\n",cmd);
            }


        }
        while(!rq.getUrlPath().equals("exit"));
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

class Rq {
    private String url;
    private String urlPath;
    private Map<String, String> params;

    // 필드추가가능

    // 수정가능
    Rq(String url) {
        this.url = url;
        urlPath = Util.getUrlPathFromUrl(this.url);
        params = Util.getParamsFromUrl(this.url);
    }

    // 수정가능, if문 금지
    public Map<String, String> getParams() {
        return params;
    }

    // 수정가능, if문 금지
    public String getUrlPath() {
        return urlPath;
    }
}

// 수정불가능
class Util {
    // 이 함수는 원본리스트를 훼손하지 않고, 새 리스트를 만듭니다. 즉 정렬이 반대인 복사본리스트를 만들어서 반환합니다.
    public static <T> List<T> reverseList(List<T> list) {
        List<T> reverse = new ArrayList<>(list.size());

        for (int i = list.size() - 1; i >= 0; i--) {
            reverse.add(list.get(i));
        }
        return reverse;
    }
    static Map<String, String> getParamsFromUrl(String url) {
        Map<String, String> params = new HashMap<>();
        String[] urlBits = url.split("\\?", 2);

        if (urlBits.length == 1) {
            return params;
        }

        String queryStr = urlBits[1];
        for (String bit : queryStr.split("&")) {
            String[] bits = bit.split("=", 2);
            if (bits.length == 1) {
                continue;
            }
            params.put(bits[0].trim(), bits[1].trim());
        }

        return params;
    }

    static String getUrlPathFromUrl(String url) {
        return url.split("\\?", 2)[0];
    }
}
