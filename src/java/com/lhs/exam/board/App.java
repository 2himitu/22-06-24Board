package com.lhs.exam.board;

import java.util.Scanner;

public class App {

    void main() {
        Scanner sc = Container.sc;
        System.out.println("== 계시판 v 0.1 ==" );
        System.out.println("== 프로그램 시작 ==" );


        String cmd;
        Rq  rq;
        do {
            System.out.printf("명령) " );
            cmd = sc.nextLine();
            rq = new Rq(cmd);

            if(rq.getUrlPath().equals("/usr/article/write")) {
                Container.usrArticleController.actionWrite();

            }else if(rq.getUrlPath().equals("/usr/article/list")){
                Container.usrArticleController.actionList(rq);

            }else if(rq.getUrlPath().equals("/usr/article/detail")){
                Container.usrArticleController.actionDetail(rq);

            }else if(rq.getUrlPath().equals("/usr/member/join")){
                Container.usrMemberController.actionJoin(rq);

            }else{
                System.out.printf("입력된 명령어 : %s\n",cmd);
            }
        }
        while(!rq.getUrlPath().equals("exit"));
        System.out.println("== 프로그램 종료 ==" );
        sc.close();
    }

}

