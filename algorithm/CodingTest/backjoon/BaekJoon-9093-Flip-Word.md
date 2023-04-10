

# [BaekJoon-9093] 단어 뒤집기

## 문제

문장이 주어졌을 때, 단어를 모두 뒤집어서 출력하는 프로그램을 작성하시오. 단, 단어의 순서는 바꿀 수 없다. 단어는 영어 알파벳으로만 이루어져 있다.

## 입력

첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있으며, 문장이 하나 주어진다. 단어의 길이는 최대 20, 문장의 길이는 최대 1000이다. 단어와 단어 사이에는 공백이 하나 있다.

## 출력
각 테스트 케이스에 대해서, 입력으로 주어진 문장의 단어를 모두 뒤집어 출력한다.

## 예제 입력 1
2  
I am happy today  
We want to win the first prize  

## 예제 출력 1

I ma yppah yadot  
eW tnaw ot niw eht tsrif ezirp  


## 풀이
스택을 이용하면 N개의 문자를 스택에 넣었다 빼면 역순으로 출력할 수 있다.

1. 스택에 알파벳을 넣는다.
2. 공백이나 문자열의 끝이면 스택에서 모두 빼낸다.
3. 역순으로 만들도록 출력

## 코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        flipWord();
    }

    public static void flipWord() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = parseInt(in.readLine());

        while (0 < t--) {
            Stack<Character> stack = new Stack<>();
            String word = in.readLine() + "\n";
            for (int i  = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ch != ' ' && ch != '\n') {
                    stack.push(ch);
                    continue;
                }
                while(!stack.isEmpty()) {
                    out.write(stack.pop());
                }
                out.write(ch);
            }
            out.flush();
        }
        out.close();;
        in.close();;
    }
    

    public static int parseInt(String arg) {
        return Integer.parseInt(arg);
    }
}


```


*문제 사이트* : https://www.acmicpc.net/problem/9093