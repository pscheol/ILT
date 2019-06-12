# BAEKJOON:2292 벌집


#### 문제

![](https://www.acmicpc.net/JudgeOnline/upload/201009/3(2).png)
출처 : https://www.acmicpc.net/JudgeOnline/upload/201009/3(2).png

위의 그림과 같이 육각형으로 이루어진 벌집이 있다. 그림에서 보는 바와 같이 중앙의 방 1부터 시작해서 이웃하는 방에 돌아가면서 1씩 증가하는 번호를 주소로 매길 수 있다. 숫자 N이 주어졌을 때, 벌집의 중앙 1에서 N번 방까지 최소 개수의 방을 지나서 갈 때 몇 개의 방을 지나가는지(시작과 끝을 포함하여)를 계산하는 프로그램을 작성하시오. 예를 들면, 13까지는 3개, 58까지는 5개를 지난다.


#### 입력
첫째 줄에 N(1 ≤ N ≤ 1,000,000,000)이 주어진다.

#### 출력
입력으로 주어진 방까지 최소 개수의 방을 지나서 갈 때 몇 개의 방을 지나는지 출력한다.
예제 입력
13
예제 출력
3

#### 해결

첫번째 방을 기준으 범위가 늘어나는 기준이 1 ,7, 19, 37, 61 이다.

그러면
1 + 6 * 1 = 7
7 + 6 * 2 = 19
10 + 6 * 3 = 37
37 + 6 * 4 = 61

으로 6의 배수로 한 겹씩 쌓인다.

#### 코드

```java
import java.util.Scanner;

    public class Main {

        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);

            int n = scan.nextInt();
            int range = 1, sum = 1, result = 0;
            while (true) {
                if (n <= (sum += (6 * (range++)))) {
                    result = n == 1 ? 1 : range;
                    break;
                }
            }
            System.out.println(result);
        }
}

```
