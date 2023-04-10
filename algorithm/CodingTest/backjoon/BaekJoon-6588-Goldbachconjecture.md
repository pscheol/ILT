

# [BaekJoon-6588] 골드바흐의 추측


## 문제

1742년, 독일의 아마추어 수학가 크리스티안 골드바흐는 레온하르트 오일러에게 다음과 같은 추측을 제안하는 편지를 보냈다.

4보다 큰 모든 짝수는 두 홀수 소수의 합으로 나타낼 수 있다.
예를 들어 8은 3 + 5로 나타낼 수 있고, 3과 5는 모두 홀수인 소수이다. 또, 20 = 3 + 17 = 7 + 13, 42 = 5 + 37 = 11 + 31 = 13 + 29 = 19 + 23 이다.

이 추측은 아직도 해결되지 않은 문제이다.

백만 이하의 모든 짝수에 대해서, 이 추측을 검증하는 프로그램을 작성하시오.

## 입력

입력은 하나 또는 그 이상의 테스트 케이스로 이루어져 있다. 테스트 케이스의 개수는 100,000개를 넘지 않는다.

각 테스트 케이스는 짝수 정수 n 하나로 이루어져 있다. (6 ≤ n ≤ 1000000)

입력의 마지막 줄에는 0이 하나 주어진다.

## 출력

각 테스트 케이스에 대해서, n = a + b 형태로 출력한다. 이때, a와 b는 홀수 소수이다. 숫자와 연산자는 공백 하나로 구분되어져 있다. 만약, n을 만들 수 있는 방법이 여러 가지라면, b-a가 가장 큰 것을 출력한다. 또, 두 홀수 소수의 합으로 n을 나타낼 수 없는 경우에는 "Goldbach's conjecture is wrong."을 출력한다.

### 예제 입력 1

```
8
20
42
0
```

### 예제 출력 1

```
8 = 3 + 5
20 = 3 + 17
42 = 5 + 37
```

## 풀이방법

에라토스테네스의 체를 가지고 소수 배열을 만들어놓고 소수배열이면서 n-1번 째 배열이 소수이면 두 소수의 합을 구할 수 있다.

에라토스테네스의 체에서 소수가 아닌 것은 `true` 고 **소수**는 `false`로 되어 있다.

`i 번째 소수 eq i 번째 소수 eq (n - i) 번째 소수`

즉 
`if (!check[i] && (!check[i] && !check[n - i]))` 

## 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        goldbachsConjecture();
    }

    public static void goldbachsConjecture() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int length = 1000000;
        boolean[] check = new boolean[length + 1];
        check[0] = check[1] = true;
        for (int i = 2; i <= length; i++) {
            if (!check[i])
                for (int j = i + i; j <= length; j += i) check[j] = true;
        }

        int n = 0;
        int a;
        boolean flags;
        while ((n = Integer.parseInt(in.readLine())) != 0) {
            a = 2;
            flags = false;
            for (int i = a; i < n; i++) {
                if (!check[i] && (!check[i] && !check[n - i])) {
                    out.write(n + " = " + i + " + " + (n - i) + "\n");
                    flags = true;
                    break;
                }
            }
            if (!flags) out.write("Goldbach's conjecture is wrong.");
            out.flush();
        }


        out.close();
        in.close();
    }
}
```


*문제 사이트* : https://www.acmicpc.net/problem/6588
