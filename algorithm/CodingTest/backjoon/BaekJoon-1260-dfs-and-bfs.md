
# DFS와 BFS

## 문제

그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

## 입력

첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

## 출력

첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.

### 예제 입력 1
```
4 5 1
1 2
1 3
1 4
2 4
3 4
```
### 예제 출력 1
```
1 2 4 3
1 2 3 4
```
### 예제 입력 2
```
5 5 3
5 4
5 2
1 2
3 4
3 1
```
### 예제 출력 2
```
3 1 2 5 4
3 1 4 2 5
```
### 예제 입력 3
```
1000 1 1000
999 1000
```
### 예제 출력 3

```
1000 999
1000 999
```

## 소스코드

```java
import java.util.*;

/**
 * 1260 : DFS와 BFS
 */
public class DFSAndBFSMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); //정점의 개수
        int m = scan.nextInt(); //간선의 개수
        int v = scan.nextInt(); //탐색을 시작할 정점의 번호

        //인접 리스트로 저장
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adjList.add(new LinkedList<>());
        }

        for (int i = 0; i < m; i++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        dfs(adjList, stack, v);
        System.out.println();
        bfs(adjList, queue, v);
        System.out.println();
    }

    /**
     * 깊이 우선 탐색 DFS
     * @param adjList
     * @param stack
     * @param v
    */
    public static void dfs(List<List<Integer>> adjList, Stack<Integer> stack, int v) {
        //방문했다고 표시
        stack.push(v);
        System.out.print(v);
        adjList.get(v).sort(Integer::compareTo);
        for (Integer vertex : adjList.get(v)) {
            if (!stack.isEmpty() && stack.search(vertex) == -1) {
                System.out.print(" ");
                dfs(adjList, stack, vertex);
            }
        }
    }

    /**
     * 너비 우선 탐색 BFS
     * @param adjList
     * @param queue
     * @param v
     */
    public static void bfs(List<List<Integer>> adjList, Queue<Integer> queue, int v) {
        boolean[] check = new boolean[adjList.size()+1];
        //1. 방문했음을 표시
        check[v] = true;
        System.out.print(v);
        //2. queue 에 정점 v삽입
        queue.offer(v);
        while (!queue.isEmpty()) {
            //4. queue에서 정점 v 삭제
            int vertex = queue.poll();
            //5. 인접 정점 탐색
            for (Integer ver : adjList.get(vertex)) {
                //6. 탐색하지 않았다면
                if (!check[ver]) {
                    //방문했다고 ququq에 삽입
                    queue.offer(ver);
                    check[ver] = true;
                    System.out.print(" ");
                    System.out.print(ver);
                }
            }
        }
    }
}

```