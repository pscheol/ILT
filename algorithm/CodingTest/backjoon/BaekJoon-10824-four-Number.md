

# [BaekJoon-10824] 네 수

## 문제

네 자연수 A, B, C, D가 주어진다. 이때, A와 B를 붙인 수와 C와 D를 붙인 수의 합을 구하는 프로그램을 작성하시오.

두 수 A와 B를 합치는 것은 A의 뒤에 B를 붙이는 것을 의미한다. 즉, 20과 30을 붙이면 2030이 된다.

## 입력

첫째 줄에 네 자연수 A, B, C, D가 주어진다. (1 ≤ A, B, C, D ≤ 1,000,000)

## 출력

A와 B를 붙인 수와 C와 D를 붙인 수의 합을 출력한다.

### 예제 입력 1
10 20 30 40
### 예제 출력 1
4060

## 소스코드 

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        solve();
    }
    public static void solve() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        String[] split = str.split(" ");
        long num1 = Long.parseLong(split[0] + split[1]);
        long num2 = Long.parseLong(split[2] + split[3]);
        out.write(num1+num2+"\n");
        out.flush();
        out.close();;
        in.close();
    }
}
```

parseLong를 직접 만들어서..

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        solve2();
    }


    public static void solve2() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        String[] split = str.split(" ");
        long num1 = parseLong(split[0] + split[1]);
        long num2 = parseLong(split[2] + split[3]);
        out.write(num1+num2+"\n");
        out.flush();
        out.close();;
        in.close();
    }
    public static long parseLong(String str) {
        char[]  chs = str.toCharArray();
        int length = chs.length;
        long exp = 1;
        long result = 0;
        int i = length -1;
        while (i != -1) {
            result += (chs[i--] - '0') * exp;
            exp *= 10;
        }
        return result;
    }
}
```

*문제 사이트* : https://www.acmicpc.net/problem/10824