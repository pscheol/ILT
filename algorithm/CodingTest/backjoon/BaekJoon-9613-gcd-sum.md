

# [BaekJoon-9613] GCD 합

## 문제

양의 정수 n개가 주어졌을 때, 가능한 모든 쌍의 GCD의 합을 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 테스트 케이스의 개수 t (1 ≤ t ≤ 100)이 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있다. 각 테스트 케이스는 수의 개수 n (1 < n ≤ 100)가 주어지고, 다음에는 n개의 수가 주어진다. 입력으로 주어지는 수는 1,000,000을 넘지 않는다.

## 출력

각 테스트 케이스마다 가능한 모든 쌍의 GCD의 합을 출력한다.

### 예제 입력 1

```
3
4 10 20 30 40
3 7 5 12
3 125 15 25
```

### 예제 출력 1

```
70
3
35
```

## 해결

최대공약수는 유클리드 호재법을 이용했다.
여기서 최대공약수의 합이라고 되어있는데 가능한 모든 쌍이라는 것이 

10 20 30 40

이 있다면
10, 20, 30, 40 의 최대굉약수 합

20, 30, 40 의 최대공약수 합

30, 40 의 최대공약수 합

이 된다.

그래서 루프를 두 번 돌려야하며 i 는 `i to n - 1`번 만큼 루프를 (왜냐하면 마지막 값은 하나 뿐이니..)
j 는 `i + 1 to n`번 만큼 루프를 돌린다.


## 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        sumOfGCD();
    }

    public static void sumOfGCD() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());

        while (t-- != 0) {
            String s = in.readLine();
            String[] split = s.split(" ");
            int n = Integer.parseInt(split[0]);
            long sum = 0;
            for (int i = 1; i <= n - 1; i++) {
                for (int j = i + 1; j <= n; j++) {
                    sum += gcd(Integer.parseInt(split[i]), Integer.parseInt(split[j]));
                }
            }
            out.write(sum + "\n");
            out.flush();
        }
        out.close();
        in.close();
    }

    public static long gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
```

*문제 사이트* : https://www.acmicpc.net/problem/9613