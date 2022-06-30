package com.lhs.exam.board;

public class Member {
    int id;
    String loginId;
    String loginPw;

    Member(int id, String loginId, String loginPw) {
        this.id= id;
        this.loginId= loginId;
        this.loginPw= loginPw;
    }
    @Override
    public String toString (){
        return String.format("\n{id:%d\n, loginId: %s\n, loginPw: %s}\n",id,loginId,loginPw);
    }
}

