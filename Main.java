package ch1;

import java.util.*;

class Main {
    public String solution(String str) {

        char[] arr = str.toCharArray();
        ArrayList<Character> list = new ArrayList<>(); //중복되지 않은 값만 넣어줄 list 생성

        for (int i = 0; i < arr.length; i++) {
            if (!list.contains(arr[i])) { //ArrayList의 contains()메소드를 활용해 이미 존재하지 않는 값만 넣어준다
                list.add(arr[i]);
            }

        }
        String answer = "";
        for (char x : list) {
            answer += x;
        }
        return answer;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.print(T.solution(str));

    }
}




