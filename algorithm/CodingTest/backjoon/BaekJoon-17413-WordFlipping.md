

# 단어뒤집기 2(Word Flipping 2)


## 문제

문자열 S가 주어졌을 때, 이 문자열에서 단어만 뒤집으려고 한다.

먼저, 문자열 S는 아래와과 같은 규칙을 지킨다.

알파벳 소문자('a'-'z'), 숫자('0'-'9'), 공백(' '), 특수 문자('<', '>')로만 이루어져 있다.
문자열의 시작과 끝은 공백이 아니다.
'<'와 '>'가 문자열에 있는 경우 번갈아가면서 등장하며, '<'이 먼저 등장한다. 또, 두 문자의 개수는 같다.
태그는 '<'로 시작해서 '>'로 끝나는 길이가 3 이상인 부분 문자열이고, '<'와 '>' 사이에는 알파벳 소문자와 공백만 있다. 단어는 알파벳 소문자와 숫자로 이루어진 부분 문자열이고, 연속하는 두 단어는 공백 하나로 구분한다. 태그는 단어가 아니며, 태그와 단어 사이에는 공백이 없다.

## 입력

첫째 줄에 문자열 S가 주어진다. S의 길이는 100,000 이하이다.

## 출력

첫째 줄에 문자열 S의 단어를 뒤집어서 출력한다.

### 예제 입력 1

```
baekjoon online judge
```

### 예제 출력 1 

```
noojkeab enilno egduj
```

### 예제 입력 2 

```
<open>tag<close>
```

### 예제 출력 2 

```
<open>gat<close>
```

### 예제 입력 3 

```
<ab cd>ef gh<ij kl>
```

### 예제 출력 3 

```
<ab cd>fe hg<ij kl>
```

### 예제 입력 4 

```
one1 two2 three3 4fourr 5five 6six
```

### 예제 출력 4 

```
1eno 2owt 3eerht rruof4 evif5 xis6
```

### 예제 입력 5 

```
<int><max>2147483647<long long><max>9223372036854775807
```

### 예제 출력 5 

```
<int><max>7463847412<long long><max>7085774586302733229
```

### 예제 입력 6 

```
<problem>17413<is hardest>problem ever<end>
```

### 예제 출력 6 

```
<problem>31471<is hardest>melborp reve<end>
```

### 예제 입력 7 

```
<   space   >space space space<    spa   c e>
```

### 예제 출력 7 

```
<   space   >ecaps ecaps ecaps<    spa   c e>
```

## 풀이

1. 스택을이용하여 문제를 해결해 나갈 수 있다.
2. 괄호를 처리하기위해 `tag`를 스위칭 하여 true이면 스택에 담지않고 출력하고 false 이면 스택에 문자열을 쌓는다.


## 소스코드

```java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        replace();
    }

    public static void replace() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String s = in.readLine();

        boolean tag = false;
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            switch (ch) {
                case '<':
                    print(out, stack);
                    out.write(ch);
                    tag = true; // 괄호시작
                    break;
                case '>':
                    out.write(ch);
                    tag = false; //괄호 종료
                    break;
                default:
                    if (tag) {
                        out.write(ch);
                    } else if (ch == ' ') {
                        print(out, stack);
                        out.write(ch);
                    } else {
                        stack.push(ch);
                    }
                    break;
            }
        }
        print(out, stack);
        out.write('\n');
        out.flush();;
        out.close();
        in.close();;
    }

    public static void print(BufferedWriter out, Stack<Character> stack) throws Exception {
        while(!stack.isEmpty()) {
            out.write(stack.pop());
        }
    }
}
```

*문제 사이트* : https://www.acmicpc.net/problem/17413