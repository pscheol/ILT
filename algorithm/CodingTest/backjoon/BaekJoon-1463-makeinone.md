
# 1로 만들기

## 문제
정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.

X가 3으로 나누어 떨어지면, 3으로 나눈다.
X가 2로 나누어 떨어지면, 2로 나눈다.
1을 뺀다.
정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.

## 입력
첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.

## 출력
첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

### 예제 입력 1 
```
2
```
### 예제 출력 1 
```
1
```
### 예제 입력 2 
```
10
```
### 예제 출력 2 
```
3
```

## 힌트

10의 경우에 10 -> 9 -> 3 -> 1 로 3번 만에 만들 수 있다.


## 해결

**Table[i] = i 로 만드는데 최소 횟수**

- i가 3으로 나누어 떨어질경우 : `Table[i / 3] + 1`

- i가 2으로 나누어 떨어질경우 : `Table[i / 2] + 1`
- i가 1을 뺄 경우 : `Table[i - 1] + 1`

최솟값을 구하는 것으로

`table[i] = Min(Table[i/3], Table[i/2], Table[i-1])+1`

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
        out.write("" + makeItOne(n));
        out.write("\n");
        out.flush();
        out.close();
        in.close();
    }
    public static int makeItOne(int n) {
        int[] table = new int[n + 1];
        table[1] = 0;
        for (int i = 2; i <= n; i++) {
            table[i] = table[i - 1] + 1;
            if (i % 2 == 0 && table[i] > table[i / 2] + 1) {
                table[i] = table[n / 2] + 1;
            } else if (i % 3 == 0 && table[i] > table[i / 3] + 1) {
                table[i] = table[n / 3] + 1;
            }
        }
        return table[n];
    }
    //짧은 코드
    public static int makeItOne2(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i / 2] + (i % 2), dp[i / 3] + (i % 3)) + 1;
        }
        return dp[n];
    }
}
```

