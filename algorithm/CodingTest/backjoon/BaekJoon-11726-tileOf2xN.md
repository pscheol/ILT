

# 2xN 타일링


## 문제

2xn 크기의 직사각형을 1x2, 2x1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 n이 주어진다 ( 1<= n <= 1000)

## 출력

첫째 줄에 2xn 크기의 직사각형을 채우는 방법의 수를 10007로 나눈 나머지를 출력

## 점화식

조건 1 : N 번째 위치에 2x1타일이 들어가면

N번째 위치에 2x1 타일 한 개를 넣을 수 있으므로

**Dp(N-1)**

조건 2 : N 번째 위치에 1x2 타일이 들어가면

끝에 1x2 를 두개 넣을 수 있으므로

**Dp(N-2)**

`Dp(N) = Dp[N-1] + Dp[N-2]`

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

        int n = Integer.parseInt(in.readLine());
        out.write("" + tileOf2xn(n));
        out.write("\n");
        out.flush();
        out.close();
        in.close();
    }
    public static int tileOf2xn(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }
        return dp[n];
    }
}
```

