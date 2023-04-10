
# [BaekJoon-1158] 요세푸스 문제(Josephus of problem)


## 문제

요세푸스 문제는 다음과 같다.

1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다. 이제 순서대로 K번째 사람을 제거한다. 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다. 이 과정은 N명의 사람이 모두 제거될 때까지 계속된다. 원에서 사람들이 제거되는 순서를 (N, K)-요세푸스 순열이라고 한다. 예를 들어 (7, 3)-요세푸스 순열은 <3, 6, 2, 7, 5, 1, 4>이다.

N과 K가 주어지면 (N, K)-요세푸스 순열을 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 N과 K가 빈 칸을 사이에 두고 순서대로 주어진다. (1 ≤ K ≤ N ≤ 5,000)

## 출력

예제와 같이 요세푸스 순열을 출력한다.

### 예제 입력 1 

```
7 3
```

### 예제 출력 1 

```
<3, 6, 2, 7, 5, 1, 4>
```


## 풀이

큐를 이용하여 문제를 해결 할 수 있다.

**N 이 7 이고 K 가 3 일 경우**

1. K번 째가 되기 전 까지 맨 앞 데이터를 꺼내어(poll) 다시 queue에 맨 뒤에 삽입(offer) 한다.
2. K 번째가 되었을 때 맨 앞 데이터를 꺼내어(poll) list에 담거나, 문자열에 담는다.

끝.

## 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        josephus();
    }

    public static void josephus() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = in.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <=n; i++) {
            queue.offer(i);
        }
        int cnt = 1;
        StringBuilder sb = new StringBuilder("<");
        while (!queue.isEmpty()) {
            if (k == cnt) {
                cnt = 1;
                sb.append(queue.poll());
                if (!queue.isEmpty()) {
                    sb.append(", ");
                }
            } else {
                cnt++;
                queue.offer(queue.poll());
            }
        }
        sb.append(">");
        out.write(""+ sb.toString());
        out.flush();
        out.close();
        in.close();
    }
}
```


*문제 사이트* : https://www.acmicpc.net/problem/1158