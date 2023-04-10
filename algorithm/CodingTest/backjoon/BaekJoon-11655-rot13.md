
# ROT13

## 문제

ROT13은 카이사르 암호의 일종으로 영어 알파벳을 13글자씩 밀어서 만든다.

예를 들어, "Baekjoon Online Judge"를 ROT13으로 암호화하면 "Onrxwbba Bayvar Whqtr"가 된다. ROT13으로 암호화한 내용을 원래 내용으로 바꾸려면 암호화한 문자열을 다시 ROT13하면 된다. 앞에서 암호화한 문자열 "Onrxwbba Bayvar Whqtr"에 다시 ROT13을 적용하면 "Baekjoon Online Judge"가 된다.

ROT13은 알파벳 대문자와 소문자에만 적용할 수 있다. 알파벳이 아닌 글자는 원래 글자 그대로 남아 있어야 한다. 예를 들어, "One is 1"을 ROT13으로 암호화하면 "Bar vf 1"이 된다.

문자열이 주어졌을 때, "ROT13"으로 암호화한 다음 출력하는 프로그램을 작성하시오.

## 입력

첫째 줄에 알파벳 대문자, 소문자, 공백, 숫자로만 이루어진 문자열 S가 주어진다. S의 길이는 100을 넘지 않는다.

## 출력

첫째 줄에 S를 ROT13으로 암호화한 내용을 출력한다.

### 예제 입력 1

```
Baekjoon Online Judge
```

### 예제 출력 1

```
Onrxwbba Bayvar Whqtr
```
### 예제 입력 2

```
One is 1
```
### 예제 출력 2

```
Bar vf 1
```


## 해결

시저암호의 알고리즘은 secret key가 주어지면 문자를 secret key만큼 shift 하는 것으로

**$f(x) = (x + key) mod 26 $** 이다.

대문자 소문자 구분은 Ascii 코드를 이용하여 대소문자를 구별할 수 있으며 문제는 `N` 이후로는 13번 shift를 하면 `A` 로 넘어가버린다.

이 문제를 해결하기 위해 key가 13 이니까 N은 `N - A = 13` 이므로 $26 mod 26 = 0$ 이 되므로 

$(fx) = (x + (idx + key) modf 26$ 라는 수식을 얻을 수 있다.


## 소스코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        rot13();
    }

    public static void rot13() throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        for (char ch : str.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                out.write(rot('a', ch, 13));
            } else if (ch >= 'A' && ch <= 'Z') {
                out.write(rot('A', ch, 13));
            } else {
                out.write(ch);
            }
        }
        out.flush();
        out.close();
        in.close();
    }

    private static char rot(char ascii, char ch, int key) {
        return (char) (ascii + ((ch - ascii) + key) % 26);
    }
}
```
*문제 사이트* : https://www.acmicpc.net/problem/11655