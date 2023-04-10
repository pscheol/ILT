
# 오큰수 

## 문제

크기가 N인 수열 A = A1, A2, ..., AN이 있다. 수열의 각 원소 Ai에 대해서 오큰수 NGE(i)를 구하려고 한다. Ai의 오큰수는 오른쪽에 있으면서 Ai보다 큰 수 중에서 가장 왼쪽에 있는 수를 의미한다. 그러한 수가 없는 경우에 오큰수는 -1이다.

예를 들어, A = [3, 5, 2, 7]인 경우 NGE(1) = 5, NGE(2) = 7, NGE(3) = 7, NGE(4) = -1이다. A = [9, 5, 4, 8]인 경우에는 NGE(1) = -1, NGE(2) = 8, NGE(3) = 8, NGE(4) = -1이다.

## 입력
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다. 둘째에 수열 A의 원소 A1, A2, ..., AN (1 ≤ Ai ≤ 1,000,000)이 주어진다.

## 출력

총 N개의 수 NGE(1), NGE(2), ..., NGE(N)을 공백으로 구분해 출력한다.

### 예제 입력 1 

```
4
3 5 2 7
```

### 예제 출력 1 

```
5 7 7 -1
```

### 예제 입력 2 

```
4
9 5 4 8
```

### 예제 출력 2 

```
-1 8 8 -1
```


## 풀이

스택을 이용하여 문제를 해결한다.

- 오큰수를 찾지 못하면 해당 위치를 스택에 넣는다.
- 스택 가장 위에 있는 위치의 `A` 와 현재 `B` 라는 수와 비교하여 `B` 가 클 경우, 스택에 있는 `A`의 위치를 `pop` 하고 `A` 위치에 `B` 라는 수를 넣는다.
- 마지막에 스택에 남은 위치에는 큰 수가 없으므로 `-1`를 채운다.

## 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {


    public static void main(String[] args) throws Exception {
        bigNumber();
    }

    public static void bigNumber() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(in.readLine());

        String s = in.readLine();


        String[] A = s.split(" ");
        if (n != A.length) {
            return;
        }

        int[] NGE = new int[n];
        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        for (int i = 1; i < n; i++) {
            int a = Integer.parseInt(A[i]);
            while (!stack.isEmpty() && Integer.parseInt(A[stack.peek()]) < a) {
                NGE[stack.pop()] = a;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
                NGE[stack.pop()] = -1;
        }

        for (int ans :  NGE) {
            out.write(ans + " ");
        }
        out.write("\n");
        out.flush();
        out.close();
        in.close();
    }
}

```

*문제 사이트* : https://www.acmicpc.net/problem/17298