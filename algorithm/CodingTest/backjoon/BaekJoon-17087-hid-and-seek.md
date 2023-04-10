

# [BaekJoon-17087] 숨바꼭질 6

## 문제
수빈이는 동생 N명과 숨바꼭질을 하고 있다. 수빈이는 현재 점 S에 있고, 동생은 A1, A2, ..., AN에 있다.

수빈이는 걸어서 이동을 할 수 있다. 수빈이의 위치가 X일때 걷는다면 1초 후에 X+D나 X-D로 이동할 수 있다. 수빈이의 위치가 동생이 있는 위치와 같으면, 동생을 찾았다고 한다.

모든 동생을 찾기위해 D의 값을 정하려고 한다. 가능한 D의 최댓값을 구해보자.

## 입력
첫째 줄에 N(1 ≤ N ≤ 105)과 S(1 ≤ S ≤ 109)가 주어진다. 둘째 줄에 동생의 위치 Ai(1 ≤ Ai ≤ 109)가 주어진다. 동생의 위치는 모두 다르며, 수빈이의 위치와 같지 않다.

## 출력
가능한 D값의 최댓값을 출력한다.

### 예제 입력 1

```
3 3
1 7 11
```

### 예제 출력 1

```
2
```

### 예제 입력 2

```
3 81
33 105 57
```

### 예제 출력 2
```
24
```

### 예제 입력 3

```
1 1
1000000000
```

### 예제 출력 3

```
999999999
```

## 해결

수빈이는 S 동생은 $A_1 ... A_N$ 에 있다고 하면 수빈이는 x-> X+D나 X - D 로 이동할 수 있다고 하고, D의 최댓값을 구하는 문제

A 에서 B 로 이동 하는 경우(A < B) : X -> X + D나 X - D 로만 이동하려면 B - X가 D의 배수가 되어야 한다.

A 에서 B C 로 이동하는 경우(A < B, A < C) : X-> X + D나 X - D로만 이동하려면 D-X가 D의 배수가 되어야하고, C-X가 의 배수가 되어야한다.

예를들어 시작점 S 가 2이고 도착지가 26이면 둘 사이 차이나는 거리 D = 24 이다. 

그래서 여기서 공통된 약수의 합을 구하면 되므로 

$|A_1 - X|,|A_2 - X|,|A_3 - X|...|A_n - X|$ 의 최대공약수를 구하면된다.



## 소스코드 

````java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        g2();
    }

    public static void g2() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        String[] split = str.split(" ");
        int n = Integer.parseInt(split[0]);
        int s = Integer.parseInt(split[1]);

        String str2 = in.readLine();
        String[] split2 = str2.split(" ");
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(split2[i]);
            a[i] = Math.abs(x-s);
        }

        int result = a[0];
        for (int i = 1; i < n; i++) {
            result = gcd(result, a[i]);
        }

        out.write(result + "\n");
        out.flush();;
        out.close();
        in.close();
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
```


*문제 사이트* : https://www.acmicpc.net/problem/17087
