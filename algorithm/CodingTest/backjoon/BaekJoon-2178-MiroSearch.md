

# 미로탐색

## 문제

N×M크기의 배열로 표현되는 미로가 있다.

```
1	0	1	1	1	1
1	0	1	0	1	0
1	0	1	0	1	1
1	1	1	0	1	1
```

미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

## 입력

첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

## 출력

첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.

### 예제 입력 1
```
4 6
101111
101010
101011
111011
```

### 예제 출력 1
```
15
```
### 예제 입력 2
```
4 6
110110
110110
111111
111101
```
예제 출력 2
```
9
```
예제 입력 3
```
2 25
1011101110111011101110111
1110111011101110111011101
```
예제 출력 3
```
38
```
예제 입력 4
```
7 7
1011111
1110001
1000001
1000001
1000001
1000001
1111111
```
예제 출력 4
```
13
```

## 풀이

(1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수룰 구하는 문제

최소의 칸수를 이용하려면 최소 범위를 구해야하는데 DFS로는 최소 범위를 구할 수 없으므로 BFS를 이용하여 문제를 푼다.

위치 이동은 상하 좌우로 이동하므로
```java
final static int[][] PATH = {{0,1}, {0,-1}, {1,0}, {-1, 0}};
```
를 세팅 해준다.

그리고 범위 탐색을해야하므로 `dist[][]` 를 추가하여 방문범위를 추가한다.

아래 처럼 미로가있다면 
```
4 6
101111
101010
101011
111011
```
dist로 방문 범위를 찍어주면 아래와 같은 결과를 얻을 수 있다.

```
 1  0  9 10 11 12 
 2  0  8  0 12  0 
 3  0  7  0 13 14 
 4  5  6  0 14 15 
```

그러면 (N, M)의 위치값을 구하는 것으로 `dist[n-1][m-1]`의 값을 출력해준다.

### DFS 코드 
```java
public static void bfs(int n, int m) {
        Queue<Edge> queue = new LinkedList<>();
        queue.offer(new Edge(0, 0));
        dist[0][0] = 1;

        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            int x = e.x;
            int y = e.y;
            for (int k = 0; k < 4; k++) {
                int nx = x + PATH[k][0];
                int ny = y + PATH[k][1];
                if (0 <= nx && n > nx && 0 <= ny && m > ny) {
                    if (table[nx][ny] == 1 && dist[nx][ny] == 0) {
                        queue.offer(new Edge(nx, ny));
                        dist[nx][ny] = dist[x][y] + 1;
                    }
                }
            }
        }
    }
```

## 소스코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 2178 : 미로탐색
 */
public class Main {
    private static int[][] table;
    private static int[][] dist;
    final static int[][] PATH = {{0,1}, {0,-1}, {1,0}, {-1, 0}};
    public static class Edge {
        int x;
        int y;

        Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] splitnm = in.readLine().split(" ");
        int n = Integer.parseInt(splitnm[0]);
        int m = Integer.parseInt(splitnm[1]);

        table = new int[n][m];
        dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] splitLine = in.readLine().split("");
            for (int j = 0; j < splitLine.length; j++) {
                table[i][j] = Integer.parseInt(splitLine[j]);
            }
        }
        bfs(n, m);
        System.out.println(dist[n - 1][m - 1]);
        in.close();
    }

    public static void bfs(int n, int m) {
        Queue<Edge> queue = new LinkedList<>();
        queue.offer(new Edge(0, 0));
        dist[0][0] = 1;

        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            int x = e.x;
            int y = e.y;
            for (int k = 0; k < 4; k++) {
                int nx = x + PATH[k][0];
                int ny = y + PATH[k][1];
                if (0 <= nx && n > nx && 0 <= ny && m > ny) {
                    if (table[nx][ny] == 1 && dist[nx][ny] == 0) {
                        queue.offer(new Edge(nx, ny));
                        dist[nx][ny] = dist[x][y] + 1;
                    }
                }
            }
        }
    }
}

```