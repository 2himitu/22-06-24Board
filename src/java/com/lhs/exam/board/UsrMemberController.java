package com.lhs.exam.board;

import java.util.ArrayList;

public class UsrMemberController {

    int memberLastId ;
    ArrayList<Member> members ;

    UsrMemberController (){
        memberLastId = 0 ;
        members = new ArrayList<>();

        makeTestData();

        if(members.size()>0){
            memberLastId = members.get(members.size()-1).id;
        }
    }

    public void actionJoin(Rq rq) {
        System.out.println("- 회원가입 -");
        System.out.printf("로그인 아이디 : ");
        String loginId = Container.sc.nextLine();
        System.out.printf("로그인 비밀번호: ");
        String loginPw = Container.sc.nextLine();
        System.out.printf("로그인 비밀번호 확인 : ");
        String loginPwConfirm = Container.sc.nextLine();

        if(loginPw.equals(loginPwConfirm)==false){
            System.out.println("비밀번호가 다릅니다.");
            return;
        }

        int id = memberLastId + 1;
        memberLastId = id;
        Member member = new Member(id,loginId, loginPw);
        members.add(member);
        System.out.printf("%s님 가입을 환영합니다.\n", member.loginId);
        System.out.printf("새로운 객체 : %s\n", member.id);
    }

    void makeTestData(){
        for(int i = 1 ; i <= 3 ; i ++){
            members.add(new Member(i,"User"+i,"User"+i));
        }
    }

}
