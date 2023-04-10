

## 문제

<그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 철수는 이 지도를 가지고 연결된 집들의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.


![](./BaekJoon-2667-PasteOfNumbering/img1.png)

## 입력

첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.

## 출력

첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.

### 예제 입력 1 
```
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000
```

### 예제 출력 1
```
3
7
8
9
```

## 소스코드 

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 2667 : 단지번호 붙이기
 */
public class PasteOfNumberingMain {
    static int[][] table;
    static int[][] dist;
    static int ret = 0;
    static int[][] path = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        table = new int[n][n];
        dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] splitLine = in.readLine().split("");
            for (int j = 0; j < splitLine.length; j++) {
                table[i][j] = Integer.parseInt(splitLine[j]);
            }
        }
        int cnt = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] == 1 && dist[i][j] == 0) {
                    ret = 0;
                    bfs(i, j, n, ++cnt);
                    list.add(ret);
                }
            }
        }
        list.sort(Integer::compareTo);
        StringBuilder sb = new StringBuilder();
        sb.append(cnt);
        sb.append("\n");
        list.sort(Integer::compareTo);
        for (Integer i : list) {
            sb.append(i);
            sb.append("\n");
        }
        System.out.println(sb.toString());
        in.close();
    }

    static class Edge {
        int x;
        int y;

        public Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void bfs(int i , int j, int n ,int cnt) {
        Queue<Edge> queue = new LinkedList<>();
        dist[i][j] = cnt;
        ret++;
        queue.offer(new Edge(i, j));

        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nx = e.x + path[k][0];
                int ny = e.y + path[k][1];
                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (table[nx][ny] == 1 && dist[nx][ny] == 0) {
                        dist[nx][ny] = cnt;
                        ret++;
                        queue.offer(new Edge(nx,ny));
                    }
                }
            }
        }

    }
    public static void dfs(int i, int j, int n, int cnt) {
        dist[i][j] = cnt;
        ret++;
        for (int k = 0; k < 4; k++) {
            int nx = i + path[k][0];
            int ny = j + path[k][1];
            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if (table[nx][ny] == 1 && dist[nx][ny] == 0) {
                    dfs(nx, ny ,n, cnt);
                }
            }
        }
    }
}

```