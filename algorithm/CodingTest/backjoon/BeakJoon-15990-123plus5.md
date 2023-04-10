

# 1,2,3 더하기 5

## 문제

정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 3가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다. 단, 같은 수를 두 번 이상 연속해서 사용하면 안 된다.

- 1+2+1
- 1+3
- 3+1

정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.


## 입력

첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 100,000보다 작거나 같다.

## 출력

각 테스트 케이스마다, n을 1, 2, 3의 합으로 나타내는 방법의 수를 1,000,000,009로 나눈 나머지를 출력한다.

### 예제 입력 1
```
3
4
7
10
```

### 예제 출력 1

```
3
9
27
```


## 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static final long MOD = 1000000009L;
    private static final int LENGTH = 100000;
    public static void main(String[] args) throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        long[][] dp = plus123_5();
        while (t-- != 0) {
            int n = Integer.parseInt(in.readLine());
            out.write("" + (dp[n][1] + dp[n][2] + dp[n][3]) % MOD);
            out.write("\n");
            out.flush();
        }
        in.close();
        out.close();
    }

    public static long[][] plus123_5() {
        long[][] dp = new long[LENGTH + 1][4];
        for (int i = 1; i <= LENGTH; i++) {
            dp[i][1] = (i == 1) ? 1 : (dp[i-1][2] + dp[i-1][3]) % MOD;

            if (i - 2 >= 0) {
                dp[i][2] = (i == 2) ? 1 : (dp[i-2][1] + dp[i-2][3]) % MOD;
            }
            if (i - 3 >= 0) {
                dp[i][3] = (i == 3) ? 1 : (dp[i-3][1] + dp[i-3][2]) % MOD;
            }
        }
        return dp;
    }
}
```