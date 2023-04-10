
# 1,2,3 더하기


## 문제

정수 4를 1,2,3의 조합으로 나타내는 방법은 총 7가지가 있다.

-	1+1+1+1
-	1+1+2
-	1+2+1
-	2+1+1
-	2+2
-	1+3
-	3+1

정수 N이 주어졌을 때, N을, 1,2,3합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.

## 입력

첫쨰 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 11보다 작다.

## 출력

각 테스트 케이스마다, n을 1,2,3의 합으로 나타내는 방법의 수를 출력한다.

### 예제입력
```
3  
4  
7  
10
```

### 예제출력
```
7  
44  
274
```

점화식

1의 조합일 때

`N-1 + 1 = N`

2의 조합일 때

`N-2 + 2 = N`

3의 조합일 때

`N-3 + 3 = N`

`D[N] = D[n-1] + D[n-2] + D[n-3]`

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
        int t = Integer.parseInt(in.readLine());
        while(t-- != 0) {
            int n = Integer.parseInt(in.readLine());
            out.write("" + plus123(n));
            out.write("\n");
        }
        out.flush();
        out.close();
        in.close();
    }
    public static int plus123(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 3 && i - j >= 0; j++) {
                    dp[i] += dp[i - j];
            }
        }
        return dp[n];
    }
}
```