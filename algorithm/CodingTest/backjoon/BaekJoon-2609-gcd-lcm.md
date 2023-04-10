

# [BaekJoon-2609] 최대공약수와 최소공배수

## 문제

두 개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하시오.

## 입력

첫째 줄에는 두 개의 자연수가 주어진다. 이 둘은 10,000이하의 자연수이며 사이에 한 칸의 공백이 주어진다.

## 출력

첫째 줄에는 입력으로 주어진 두 수의 최대공약수를, 둘째 줄에는 입력으로 주어진 두 수의 최소 공배수를 출력한다.

### 예제 입력 1

```
24 18
```

### 예제 출력 1

```
6
72
```

## 풀이

최대공약수 : gcd(a, a%b) 유클리드 호재법을 통해 최대공약수를 구할 수 있다.

> 호제법 : 두 수가 서로 상대방 수를 나누어서 결국 원하는 수를 얻는 알고리즘.

`a` 를 `b`로 나눈 나머지를 `r` 이라고 했을 때 `GCD(a,b) = GCD(b,r)`과 같다.
여기서 `r`이 `0` 이면 `b`가 최대공약수가 된다.

$GCD(24,16)=GCD(16,8)=GCD(8,0)=8,13$

최소공배수 : $L = lcm(a, b)$ 은 $L= lcm(a, b)= a * b / gcd(a, b)$이 성립

## 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        String[] split = str.split(" ");
        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);
        int gcd = gcd(a, b);
        out.write(gcd + "\n");
        out.write(lcm(a, b, gcd) + "\n");
        out.flush();
        out.close();
        ;
        in.close();
        ;
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
    //재귀를 이용한 gcd
    public static long gcd2(long a, long b) {
		return b != 0 ? gcd(b, a % b) : a;
	}

    public static int lcm(int a, int b, int gcd) {
        return (a * b) / gcd;
    }
}
```

*문제 사이트* : https://www.acmicpc.net/problem/6588