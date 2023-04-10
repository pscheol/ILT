

# 연결요소의 개수

## 문제

방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.

## 출력

첫째 줄에 연결 요소의 개수를 출력한다.

### 예제 입력 1
```
6 5
1 2
2 5
5 1
3 4
4 6
```
### 예제 출력 1
```
2
```

### 예제 입력 2

```
6 8
1 2
2 5
5 1
3 4
4 6
5 4
2 4
2 3
```

### 예제 출력 2
```
1
```


## 풀이

모든 정점의 개수만큼 루프를 돌면서

연결된 하나의 그래프의 개수를 카운팅


## 소스코드 
```java
import java.util.*;

/**
 * 11724 연결요소
 */
public class ConnectedComponentMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); //정점의 개수
        int m = scan.nextInt(); //간선의 개수

        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] check = new boolean[n+1];
        int cnt = 0;
        for (int i = 1 ; i <= n; i ++) {
            if(!check[i]) {
                bfs(adjList, check, i);
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    /**
     * DFS
     * @param adjList
     * @param check
     * @param v
     */
    public static void dfs(List<List<Integer>> adjList, boolean[] check, int v) {
        if (check[v]) {
            return;
        }
        check[v] = true;
        for (Integer vertex : adjList.get(v)) {
            if (!check[vertex]) {
                dfs(adjList, check, vertex);
            }
        }
    }

    /**
     * BFS
     * @param adjList
     * @param check
     * @param v
     */
    public static void bfs(List<List<Integer>> adjList, boolean[] check, int v) {
        Queue<Integer> queue = new LinkedList<>();
        check[v] = true;
        queue.offer(v);
        while (!queue.isEmpty()) {
            int dequeue = queue.poll();
            for (int vertex : adjList.get(dequeue)) {
                if (!check[vertex]) {
                    check[vertex] = true;
                    queue.offer(vertex);
                }
            }
        }
    }
}

```