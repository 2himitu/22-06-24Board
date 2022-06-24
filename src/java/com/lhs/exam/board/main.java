package com.lhs.exam.board;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 계시판 v 0.1 ==" );
        System.out.println("== 프로그램 시작 ==" );
        System.out.printf("명령) " );
        System.out.println("입력된 명령어 :"+sc.nextLine());
        System.out.println("== 프로그램 종료 ==" );
        sc.close();
    }
}
