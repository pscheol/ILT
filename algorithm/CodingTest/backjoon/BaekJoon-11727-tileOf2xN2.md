

# 2xn 타일링 2


## 문제

2×n 직사각형을 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)

## 출력

첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.

### 입력 2 -> 출력 3

### 입력 8 -> 출력 171

### 입력 12 -> 출력 2731

## 점화식

-	2xn 직사각형을 1x2, 2x2, 2x1 타일로 채우는 방법의 수

d[i] = 2x i직사각형을 채워야함

조건 1

2 x 1인 블록을 N 번재 위치에 놓으면

2 X (n-1) 만큼 나머지를 채워야함

조건 2

2 x 2 블록을 N 번째 위치에 놓으면

2 X (n-2) 만큼 나머지를 채워야함

조건 3

1 x 2 블록을 N 번째 위치에 놓으면

2 X (n-2) 만큼 나머지를 채워야함

그래서 점화식

Dp[N] = Dp[n-1] + (2 * Dp[N-2])

이된다.

코드

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
        out.write("" + tileOf2xn2(n));
        out.write("\n");
        out.flush();
        out.close();
        in.close();
    }
    public static int tileOf2xn2(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i-1] + (2*dp[i-2])) % 10007;
        }
        return dp[n];
    }
}
```

