

# 쉬운 계단 수


# 문제
45656이란 수를 보자.

이 수는 인접한 모든 자리수의 차이가 1이 난다. 이런 수를 계단 수라고 한다.

세준이는 수의 길이가 N인 계단 수가 몇 개 있는지 궁금해졌다.

N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오. (0으로 시작하는 수는 없다.)

# 입력
첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.

# 출력
첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.

### 예제 입력 1
```
1
```
### 예제 출력 1
```
9
```
### 예제 입력 2
```
2
```
### 예제 출력 2
```
17
```

## 풀이 
- 인접 한 자리의 차이 1 이 나는 걸 계단 수라고 함
- 45656
- 길이가 N인 계단 수를 개수를 구하는 문제

Dp[N][L]의 길이가 N인 계단 수

마지막 수 L 는 `L + 1` or `L - 1`

즉 DP[i][j] 는 길이가 i이인 마지막 수가 j인 계단수의 개수
- `Dp[i][j] = Dp[i-1][j-1] + Dp[i-1][j+1]`

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
        out.write("" + easyStair(n));
        out.write("\n");
        out.flush();
        out.close();
        in.close();
    }

    public static long easyStair(int n) {
        long mod = 1000000000L;
        long[][] dp = new long[n + 1][10];
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                dp[i][j] = 0;
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if (j + 1 <= 9) {
                    dp[i][j] += dp[i - 1][j + 1];
                }
                dp[i][j] %= mod;
            }
        }
        long solv = 0;
        for (int i = 0; i <=9; i++) {
            solv += dp[n][i];
        }
        return solv %= mod;
    }
}
```


