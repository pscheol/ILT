

# 쇠막대기


## 문제

여러 개의 쇠막대기를 레이저로 절단하려고 한다. 효율적인 작업을 위해서 쇠막대기를 아래에서 위로 겹쳐 놓고, 레이저를 위에서 수직으로 발사하여 쇠막대기들을 자른다. 쇠막대기와 레이저의 배치는 다음 조건을 만족한다.

쇠막대기는 자신보다 긴 쇠막대기 위에만 놓일 수 있다. - 쇠막대기를 다른 쇠막대기 위에 놓는 경우 완전히 포함되도록 놓되, 끝점은 겹치지 않도록 놓는다.
각 쇠막대기를 자르는 레이저는 적어도 하나 존재한다.
레이저는 어떤 쇠막대기의 양 끝점과도 겹치지 않는다. 
아래 그림은 위 조건을 만족하는 예를 보여준다. 수평으로 그려진 굵은 실선은 쇠막대기이고, 점은 레이저의 위치, 수직으로 그려진 점선 화살표는 레이저의 발사 방향이다.



이러한 레이저와 쇠막대기의 배치는 다음과 같이 괄호를 이용하여 왼쪽부터 순서대로 표현할 수 있다.

레이저는 여는 괄호와 닫는 괄호의 인접한 쌍 ‘( ) ’ 으로 표현된다. 또한, 모든 ‘( ) ’는 반드시 레이저를 표현한다.
쇠막대기의 왼쪽 끝은 여는 괄호 ‘ ( ’ 로, 오른쪽 끝은 닫힌 괄호 ‘) ’ 로 표현된다. 
위 예의 괄호 표현은 그림 위에 주어져 있다.

쇠막대기는 레이저에 의해 몇 개의 조각으로 잘려지는데, 위 예에서 가장 위에 있는 두 개의 쇠막대기는 각각 3개와 2개의 조각으로 잘려지고, 이와 같은 방식으로 주어진 쇠막대기들은 총 17개의 조각으로 잘려진다. 

쇠막대기와 레이저의 배치를 나타내는 괄호 표현이 주어졌을 때, 잘려진 쇠막대기 조각의 총 개수를 구하는 프로그램을 작성하시오.

## 입력

한 줄에 쇠막대기와 레이저의 배치를 나타내는 괄호 표현이 공백없이 주어진다. 괄호 문자의 개수는 최대 100,000이다. 

## 출력
잘려진 조각의 총 개수를 나타내는 정수를 한 줄에 출력한다.

### 예제 입력 1 

```
()(((()())(())()))(())
```

### 예제 출력 1 

```
17
```

### 예제 입력 2 

```
(((()(()()))(())()))(()())
```

### 예제 출력 2 

```
24
```

## 풀이

스택을 이용하여 문제를 해결할 수 있다. `()` 은 레이저이고, `(` 의 시작하여  `)` 끝은 쇠막대기를 열고 닫는 괄호이며, 쇠막대기 사이에는 `()` 들이 있다.

ex) (((()())(())()))

1. `(` 가 열릴 때마다 스택에 담는다
2. `)` 가 닫히면 제일 가까이 있는 `(`를 꺼낸다.
3. 만약 이전 괄호가 `)` 일 경우 1개만 증가하고(쇠막대기 1개를 레이저가 3번 자르면 쇠막대기는 4조각으로 나눠지기 때문에...) 그렇지않으면 스택의 사이즈 만큼 증가한다 `result += (before == ')') ? 1 :stack.size();`
4. 이를 계속 반복.




## 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        ironBar();
    }

    public static void ironBar() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String s = in.readLine();
        int result = 0;
        char before = ' ';
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            switch (ch) {
                case '(':
                    stack.push(ch);
                    break;
                case ')':
                    stack.pop();
                    result += (before == ')') ? 1 :stack.size();
                    break;
            }
            before = ch;
        }
        out.write(result + "\n");
        out.flush();
        out.close();;
        in.close();
    }
}
```


*문제 사이트* : https://www.acmicpc.net/problem/10799

