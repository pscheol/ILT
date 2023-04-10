

# 이분그래프

## 문제

그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.

그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.

## 입력

입력은 여러 개의 테스트 케이스로 구성되어 있는데, 첫째 줄에 테스트 케이스의 개수 K(2≤K≤5)가 주어진다. 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V(1≤V≤20,000)와 간선의 개수 E(1≤E≤200,000)가 빈 칸을 사이에 두고 순서대로 주어진다. 각 정점에는 1부터 V까지 차례로 번호가 붙어 있다. 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데, 각 줄에 인접한 두 정점의 번호가 빈 칸을 사이에 두고 주어진다.

## 출력

K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.

### 예제 입력 1

```
2
3 2
1 3
2 3
4 4
1 2
2 3
3 4
4 2
```

### 예제 출력 1  복사
```
YES
NO
```

## 풀이

일단 이분 그래프가 뭔지 알아야한다..

https://ko.wikipedia.org/wiki/%EC%9D%B4%EB%B6%84_%EA%B7%B8%EB%9E%98%ED%94%84

그다음 DFS or BFS 를 이용하면 된다.

## 소스코드

```java
import java.util.*;

/**
 * 1707 이분그래프(Bipartite Graph)
 */
public class BipartiteGraphMain {
    private static List<List<Integer>> adjList;
    private static int[] colors;
    private static boolean isBipartite;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t-- != 0) {
            int v = scan.nextInt(); //vertex
            int e = scan.nextInt(); //edge

            //init
            adjList = new ArrayList<>();
            colors = new int[v + 1]; //이분그래프를 분리할 테이블
            isBipartite = true;

            for (int i = 0 ; i <= v; i ++) {
                adjList.add(new ArrayList<>());
                colors[i] = 0;
            }

            //무방향 그래프 값 삽입
            for (int i = 0; i < e; i++) {
                int from = scan.nextInt();
                int to = scan.nextInt();
                adjList.get(from).add(to);
                adjList.get(to).add(from);
            }


            //모든 정점의 길이만큼 수행.
            for (int i = 1; i <= v; i++) {
                if (!isBipartite) { // 이분그래프가 아니면 더 이상 루프를 돌 필요가 없으므로 Break
                    break;
                }
                if (colors[i] == 0) {
                    bfs(i, 1); //RED 1, GREEN -1 형식으로 분리
                }
            }
            System.out.println(isBipartite ? "YES":"NO");
        }
    }

    /**
    * DFS
    **/
    public static void dfs(int v, int color) {
        colors[v] = color;
        for (Integer vertex : adjList.get(v)) {
            //시작정점과 인접 정점의 색이 같으면 이분 그래프가 아니므로 리턴
            if (colors[vertex] == color) {
                isBipartite = false;
                return;
            }
            //해당 정점을 방문하지 않았으면
            if (colors[vertex] == 0) {
                dfs(vertex, -color);
            }
        }
    }
    /**
    * BFS
    **/
    public static void bfs(int v, int color) {
        Queue<Integer> queue = new LinkedList<>();
        colors[v] = color;
        queue.offer(v);
        int tmpColor = color;
        while (!queue.isEmpty()) {
            int dequeue = queue.poll();
            color = colors[dequeue] == 1 ? -1 : 1; //RED 1, GREEN -1 형식으로 분리;
            for (Integer vertex : adjList.get(dequeue)) {
                //시작정점과 인접 정점의 색이 같으면 이분 그래프가 아니므로 리턴
                if (colors[vertex] != 0 && colors[vertex] == colors[dequeue]) {
                    isBipartite = false;
                    return;
                }
                //해당 정점을 방문하지 않았을 경우
                if (colors[vertex] == 0) {
                    colors[vertex] = color;
                    queue.offer(vertex);
                }
            }
        }
    }
}

```