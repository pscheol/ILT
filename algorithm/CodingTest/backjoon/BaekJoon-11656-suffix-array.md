

# [BaekJoon-11656] 접미사배열 
## 문제

접미사 배열은 문자열 S의 모든 접미사를 사전순으로 정렬해 놓은 배열이다.

baekjoon의 접미사는 baekjoon, aekjoon, ekjoon, kjoon, joon, oon, on, n 으로 총 8가지가 있고, 이를 사전순으로 정렬하면, aekjoon, baekjoon, ekjoon, joon, kjoon, n, on, oon이 된다.

문자열 S가 주어졌을 때, 모든 접미사를 사전순으로 정렬한 다음 출력하는 프로그램을 작성하시오.

## 입력

첫째 줄에 문자열 S가 주어진다. S는 알파벳 소문자로만 이루어져 있고, 길이는 1,000보다 작거나 같다.

## 출력

첫째 줄부터 S의 접미사를 사전순으로 한 줄에 하나씩 출력한다.

### 예제 입력 1

```
baekjoon
```

### 예제 출력 1

```
aekjoon
baekjoon
ekjoon
joon
kjoon
n
on
oon
```

## 해결방버

자바 문자열 함수 중 `substring()` 을 이용하면 문자열을 분리하고 sort() 를 통해 정렬한다.

## 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        suffixOfArray();
    }

    public static void suffixOfArray() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        String[] arr = new String[str.length()];

        for (int i = 0; i<str.length(); i++) {
            arr[i] = str.substring(i);
        }
        Arrays.sort(arr);
        for (String s : arr) {
            out.write(s+'\n');
        }
        out.flush();
        out.close();;
        in.close();
    }
}
```

*문제 사이트* : https://www.acmicpc.net/problem/11656