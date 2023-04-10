
# [BaekJoon-1406] 에디터(Editor)


## 문제

한 줄로 된 간단한 에디터를 구현하려고 한다. 이 편집기는 영어 소문자만을 기록할 수 있는 편집기로, 최대 600,000글자까지 입력할 수 있다.

이 편집기에는 '커서'라는 것이 있는데, 커서는 문장의 맨 앞(첫 번째 문자의 왼쪽), 문장의 맨 뒤(마지막 문자의 오른쪽), 또는 문장 중간 임의의 곳(모든 연속된 두 문자 사이)에 위치할 수 있다. 즉 길이가 L인 문자열이 현재 편집기에 입력되어 있으면, 커서가 위치할 수 있는 곳은 L+1가지 경우가 있다.

이 편집기가 지원하는 명령어는 다음과 같다.

| | |
|:---:|---|
|L |커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)|
|D |커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)|
|B |커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨) 삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임|
|P $|$라는 문자를 커서 왼쪽에 추가함|


초기에 편집기에 입력되어 있는 문자열이 주어지고, 그 이후 입력한 명령어가 차례로 주어졌을 때, 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 구하는 프로그램을 작성하시오. 단, 명령어가 수행되기 전에 커서는 문장의 맨 뒤에 위치하고 있다고 한다.

## 입력

첫째 줄에는 초기에 편집기에 입력되어 있는 문자열이 주어진다. 이 문자열은 길이가 N이고, 영어 소문자로만 이루어져 있으며, 길이는 100,000을 넘지 않는다. 둘째 줄에는 입력할 명령어의 개수를 나타내는 정수 M(1 ≤ M ≤ 500,000)이 주어진다. 셋째 줄부터 M개의 줄에 걸쳐 입력할 명령어가 순서대로 주어진다. 명령어는 위의 네 가지 중 하나의 형태로만 주어진다.

## 출력

첫째 줄에 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 출력한다.

### 예제 입력 1 

```
abcd
3
P x
L
P y
```

### 예제 출력 1 

```
abcdyx
```

### 예제 입력 2 

```
abc
9
L
L
L
L
L
P x
L
B
P y
```

### 예제 출력 2 

```
yxabc
```

### 예제 입력 3 

```
dmih
11
B
B
P x
L
B
B
B
P y
D
D
P z
```

### 예제 출력 3 
```
yxz
```

## 풀이

**커서(cursor : |)를 기준으로 왼쪽 스택(left) 오른쪽 스택(right)로 나누어서 문제를 해결할 수 있다.**

스택을 2개 만들고 문자열을 left에 초기화로 넣고 문제를 해결해 나가면 된다.

  1. L 일 경우 왼쪽 스택 데이터를 Pop 하여 오른쪽 스택으로 Push 한다.
  2. D 일 경우 오른족 스택 데이터를 Pop 하여 왼쪽으로 움긴다.
  3. B 일 경우 왼쪽에 있는 스택 데이터를 하나 삭제(Pop)한다.
  4. P $ 일 경우 $를 스택 왼쪽에 추가(Push) 하면된다. 



**알고리즘 분류를 보면 연결리스트라고 되어 있는데 연결리스트를 통해 문제를 풀면 시간초과로 Fail이 되버린다. **
  
<연결리스트 소스코드 시간초과>

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        editorLinkedList();
    }

    public static void editorLinkedList() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));


        String read = in.readLine();
        if (read.length() > 100000) {
            return;
        }
        int m = Integer.parseInt(in.readLine());

        if (m < 1 || m > 500000) {
            return;
        }

        List<Character> list = new LinkedList<>();
        for (char ch : read.toCharArray())
            list.add(ch);

        int idx = list.size();
        for (int i = 0; i < m; i++) {
            String input = in.readLine();
            char[] chs = input.toCharArray();
            switch (chs[0]) {
                case 'L':
                    idx = (idx == 0) ? 0 : --idx;
                    break;
                case 'D':
                    idx = (idx == list.size()) ? idx : ++idx;
                    break;
                case 'B':
                    if (idx != 0)
                        list.remove(idx- 1);
                    idx = (idx == 0) ? 0 : --idx;
                    break;
                case 'P':
                    list.add(idx++, chs[2]);
                    break;
            }
        }

        for (char ch : list) {
            out.write(ch + "");
        }

        out.flush();

        out.close();
        in.close();
    }
}
```


## 최종 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        editor();
    }

    public static void editor() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));


        String read = in.readLine();
        if (read.length() > 100000) {
            return;
        }
        int m = Integer.parseInt(in.readLine());

        if (m < 1 || m > 500000) {
            return;
        }

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        for (char ch : read.toCharArray())
            left.push(ch);

        for (int i = 0; i < m; i++) {
            String input = in.readLine();
            switch (input.charAt(0)) {
                case 'L':
                    if (!left.isEmpty())
                        right.push(left.pop());
                    break;
                case 'D':
                    if (!right.isEmpty())
                        left.push(right.pop());
                    break;
                case 'B':
                    if (!left.isEmpty())
                        left.pop();
                    break;
                case 'P':
                    left.push(input.charAt(2));
                    break;

            }
        }

        while (!left.isEmpty()) {
            right.push(left.pop());
        }
        while (!right.isEmpty()) {
            out.write("" + right.pop());
        }
        out.flush();

        out.close();
        in.close();
    }
}
```


*문제 사이트* : https://www.acmicpc.net/problem/1406