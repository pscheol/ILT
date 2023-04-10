
# [BaekJoon-1935] 후위표기식2

## 문제

후위 표기식과 각 피연산자에 대응하는 값들이 주어져 있을 때, 그 식을 계산하는 프로그램을 작성하시오.

## 입력
첫째 줄에 피연산자의 개수(1 ≤ N ≤ 26) 가 주어진다. 그리고 둘째 줄에는 후위 표기식이 주어진다. (여기서 피연산자는 A~Z의 영대문자이며, A부터 순서대로 N개의 영대문자만이 사용되며, 길이는 100을 넘지 않는다) 그리고 셋째 줄부터 N+2번째 줄까지는 각 피연산자에 대응하는 값이 주어진다. (3번째 줄에는 A에 해당하는 값, 4번째 줄에는 B에 해당하는값 , 5번째 줄에는 C ...이 주어진다, 그리고 피연산자에 대응 하는 값은 정수이다)

## 출력

계산 결과를 소숫점 둘째 자리까지 출력한다.

### 예제 입력 1 

```
5
ABC*+DE/-
1
2
3
4
5
```

### 예제 출력 1 
```
6.20
```
### 예제 입력 2 
```
1
AA+A+
1
```

### 예제 출력 2 
```
3.00
```

## 문제해결

스택을 이용하여 문제를 해결해나간다.

피연산자일 경우 스택에 Push하고 연산자를 만나면 두 수를 pop()하여 연산을 하고 연산결과를 다시 Push를 한다

## 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {


    public static void main(String[] args) throws Exception {
        postfixNotation();
    }

    public static void postfixNotation() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        String postfix = in.readLine();
        double[] numbers = new double[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Double.parseDouble(in.readLine());
        }

        Stack<Double> stack = new Stack<>();
        for (char ch : postfix.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                stack.push(numbers[(ch - 'A')]);
            } else {
                double n2 = stack.pop();
                double n1 = stack.pop();
                switch (ch) {

                    case '+':
                        stack.push(n1 + n2);
                        break;
                    case '-':
                        stack.push(n1 - n2);
                        break;
                    case '*':
                        stack.push(n1 * n2);
                        break;
                    case '/':
                        stack.push(n1 / n2);
                        break;
                }
            }
        }
        out.write(String.format("%.2f\n", stack.pop()));
        out.flush();
        ;
        out.close();
        in.close();
    }
}
```

*문제 사이트* : https://www.acmicpc.net/problem/1935