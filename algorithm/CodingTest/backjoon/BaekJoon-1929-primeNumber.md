

# [BaekJoon-1929] 소수구하기

## 문제

M이상 N이하의 소수를 모두 출력하는 프로그램을 작성하시오.

## 입력

첫째 줄에 자연수 M과 N이 빈 칸을 사이에 두고 주어진다. (1 ≤ M ≤ N ≤ 1,000,000)

## 출력

한 줄에 하나씩, 증가하는 순서대로 소수를 출력한다.

### 예제 입력 1

```
3 16
```


### 예제 출력 1

```
3
5
7
11
13
```


## 풀이

일반 소수구하기로 하면 시간초과난다.

그래서 에라토스테네스의 체를 이용하여 문제를 해결할 수 있다.

고대 그리스의 수학자 에라토스테네스가 만들어 낸 소수를 찾는 방법. 이 방법은 마치 체로 치듯이 수를 걸러낸다고 하여 '에라토스테네스의 체'라고 부른다. 따지고 보면 $f(x)=x1_P(x)$ 의 수열 중 0이 아닌 것을 표로 시각화한 것이라고 볼 수 있다. 참조 : [나무위키](https://namu.wiki/w/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98%20%EC%B2%B4)

## 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception {

        prime();
    }

    public static void prime() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        String[] split = str.split(" ");
        int m = Integer.parseInt(split[0]);
        int n = Integer.parseInt(split[1]);

        int[] num = new int[n + 1];
        boolean[] check = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            num[i] = i;
        }
        check[0] = check[1] = true;
        for (int i = 2; i <=n; i++) {
            if (!check[i])
                for (int j = i + i; j <= n; j+=i) check[j] = true;
        }

        for (int i = m; i <=n; i++) {
            if (!check[i])
                out.write(num[i] + "\n");
        }
        out.flush();
        out.close();
        in.close();
    }
}
```

*문제 사이트* : https://www.acmicpc.net/problem/6588