

# 알파벳 개수

## 문제
 
 알파벳 소문자로만 이루어진 단어 S가 주어진다. 각 알파벳이 단어에 몇 개가 포함되어 있는지 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 단어 S가 주어진다. 단어의 길이는 100을 넘지 않으며, 알파벳 소문자로만 이루어져 있다.

## 출력

단어에 포함되어 있는 a의 개수, b의 개수, …, z의 개수를 공백으로 구분해서 출력한다.

### 예제 입력 1

```
baekjoon
```

### 예제 출력 1

```
1 1 0 0 1 0 0 0 0 1 1 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0
```

## 해결

아스키코드를 이용하여 쉽게 풀 수 있다.


## 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {


    public static void main(String[] args) throws Exception {
        countOfAlphabet();
    }

    public static void countOfAlphabet() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        int[] result = new int [26];
        for (char ch : str.toCharArray()) {
            result[ch - 'a']++;
        }

        for(int i : result) {
            out.write(i + " ");
        }
        out.flush();
        out.close();
        in.close();

    }
}
```

*문제 사이트* : https://www.acmicpc.net/problem/10808