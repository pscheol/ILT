# Graph(그래프)

그래프는 연결되어 있는 객체간의 관계를 표현할 수 있는 자료구조로 vertex(정점)와 edge(간선)의 집합으로 이루어진다.

## 그래프 용어

-	수학적으로는 G = (V,E)로 표시한다.
-	V(G)는 그래프 G의 vertex들의 집합
-	E(G)는 그래프 G의 edge들의 집합
-	Vertex는 Node라고 불린다.
-   Edge는 link라고 불린다.
-	Vertex의 종류에 따라 무방향 그래프(Undirected Graph)와 방향 그래프(Directed Graph)로 구분된다.
	-	**무방향 그래프** :  'S---E' 화살표가 없는 선으로 이루어진 형태이다.
		-	무방향 그래프는 간선이 방향성이 없는 그래프로 양방향으로 갈 수 있다.
		-	정점의 차수(Degree)는 그 정점에 인접한 정점의 수를 말한다.
	-	**방향 그래프** : 'S--->E' 화살표가 있는 선으로 이루어진 형태다. 
		-	방향 그래프는 간선이 방향성이 있는 그래프로 한쪽방향으로만 갈 수 있다.
-	간선에 비용이나 가중치가 할당된 그래프는 가중치 그래프(Weighted graph) 또는 네트워크(network)라고 한다.
-	인접정점(adjacent vertex) : 간선에 의해 직접 연결된 정점을 뜻한다.
-	경로 중에서 반복되는 간선이 없는 경우 단순경로(Simple Path)라고 한다
-	시작정점과 종료정점이 동일하다면 이러한 경로를 사이클(cycle)이라고 한다.
-	완전 그래프(Complete Graph) : 그래프 속에 있는 모든 정점이 서로 연결되어 있는 그래프
	-	무방향 완전 그래프의 정점의 수를 n이라고 하면 하나의 정점은 n-1개의 다른 정점으로 연결되므로 간선의 수는 n*(n-1)/2가 된다.

## 그래프의 표현방법

### 1.	인접행렬 (adjacency matrix) O(n^2): 2차원 배열인 인접행렬 M의 각 원소는 다음 규칙에 의해 할당한다.

-	if(edge(i,j)가 그래프에 존재) M[i][j] = 1
-	otherwise M[i][j] = 0
-	그래프에서는 자체 간선을 허용하지 않으므로 인접행렬의 대각선 성분은 모두 0으로 표시한다.
-	무방향 그래프의 인접행렬을 대칭행렬이 된다.
-	방향 그래프의 인접행렬은 일반적으로 대칭이 아니다.
-	n개의 정점을 가지는 그래프를 인접행렬로 표현하기 위해서는 간선의 수에 무관하게 항상 n^2개의 메모리 공간이 필요하다.

#### 인접행렬 ADT

AdjacencyMatrix.h

```cs
#ifndef ADJACENCYMATRIX_H
#define ADJACENCYMATRIX_H


#include <stdio.h>
#include <stdlib.h>
#include <string.h>


#define MAX_VERTICES 10
typedef struct _GraphType {
	int n; //정점의 개수
	int adj_matrix[MAX_VERTICES][MAX_VERTICES];


}GraphType;


void graph_init(GraphType* graph);
void insertvertex(GraphType *g, int v);
void insert_edge(GraphType* g, int start, int end);
#endif //ADJACENCYMATRIX_H
```

AdjacencyMatrix.c

```cs
//
// Created by Paik Seung Cheol on 2017. 9. 20..
//


#include "AdjacencyMatrix.h"


void graph_init(GraphType* graph) {
	int r, c;
	graph->n = 0;
	for(r = 0; r<MAX_VERTICES; r++) {
		for(c = 0; c<MAX_VERTICES; c++) {
			graph->adj_matrix[r][c] = 0;
		}
	}
}


void insertvertex(GraphType *g, int v) {
	if ((g->n+1) > MAX_VERTICES) {
		fprintf(stderr, "graph Error vertex overflow\n");
		return;
	}
	g->n++;


}
void insert_edge(GraphType* g, int start, int end) {
	g->adj_matrix[start][end] = 1;
	g->adj_matrix[end][start] = 1;
}
```

main.c

```cs
#include "AdjacencyMatrix.h"


void adjMatrix() {
	GraphType* graph = (GraphType*)malloc(sizeof(GraphType));
	grapth_init(graph);
	insertvertex(graph, 5);
	insertvertex(graph, 5);
	insertvertex(graph, 5);
	insertvertex(graph, 5);
	insert_edge(graph,4,5);
	insert_edge(graph,2,3);
	insert_edge(graph,3,1);
	insert_edge(graph,2,5);
	insert_edge(graph,7,3);


	int r, c;
	graph->n = 0;
	for(r = 0; r<MAX_VERTICES; r++) {
		for(c = 0; c<MAX_VERTICES; c++) {
			printf("%d ",graph->adj_matrix[r][c]);
		}
		printf("\n");
	}
}
int main() {
	adjMatrix();
	return 0;
}
```

### 2.	인접 리스트(adjacency list) O(n+e) : 각각의 정점에 인접한 정점들을 연결리스트로 표시한 것으로 각 연결리스트이 노드들은 인접 정점을 저장한다.

-	연결리스트들은 헤드 포인터를 가지고 있고 이 헤드 포인터들은 하나의 배열로 구성되어 있어서 정점의 번호만 알면 이 번호를 배열의 인덱스로 하여 각 정점의 연결리스트에 쉽게 접근이 가능하다.

#### 인접리스트 ADT

AdjacencyList.h

```cs
//
// Created by Paik Seung Cheol on 2017. 9. 20..
//


#ifndef ADJACENCYLIST_H
#define ADJACENCYLIST_H


#include <stdio.h>
#include <stdlib.h>


enum VisitMode { Visited, NotVisited};
typedef int Element;


typedef struct tagVertex {
	Element Data;
	int visited;
	int index;
	struct tagVertex* next;
	struct tagEdge* AdjacencyList;
}Vertex;


typedef struct tagEdge {
	int weight;
	struct tagEdge* next;
	Vertex* from;
	Vertex* target;
}Edge;


typedef struct tagGraph {
	Vertex* vertices;
	int vertexCount;
}Graph;


Graph* createGraph();
void destoryGraph(Graph* g);


Vertex* createVertex(Element data);
void destoryVertex(Vertex* v);


Edge* createEdge(Vertex* from, Vertex* target, int weight);
void destoryEdge(Edge* e);


void addVertex(Graph* g, Vertex* v);
void addEdge(Vertex* v, Edge* e);
void printGraph(Graph* g);


#endif //ADJACENCYLIST_H
```

AdjacencyList.c

```cs
//
// Created by Paik Seung Cheol on 2017. 9. 20..
//


#include "AdjacencyList.h"


Graph* createGraph() {
	Graph* graph = (Graph*)malloc(sizeof(Graph));
	graph->vertices = NULL;
	graph->vertexCount = 0;
	return graph;
}
void destoryGraph(Graph* g) {
	while(g->vertices != NULL) {
		Vertex* verteices = g->vertices->next;
		destoryVertex(g->vertices);
		g->vertices = verteices;
	}
	free(g);
}


Vertex* createVertex(Element data) {
	Vertex* vertex = (Vertex*)malloc(sizeof(Vertex));


	vertex->Data = data;
	vertex->next = NULL;
	vertex->AdjacencyList = NULL;
	vertex->visited = NotVisited;
	vertex->index = -1;
	return vertex;
}
void destoryVertex(Vertex* v) {
	while(v->AdjacencyList != NULL) {
		Edge* edge = v->AdjacencyList->next;
		destoryEdge(v->AdjacencyList);
		v->AdjacencyList = edge;
	}
	free(v);
}


Edge* createEdge(Vertex* from, Vertex* target, int weight) {
	Edge* e = (Edge*)malloc(sizeof(Edge));
	e->from = from;
	e->target = target;
	e->weight = weight;
	e->next = NULL;
	return e;
}
void destoryEdge(Edge* e) {
	free(e);
}


void addVertex(Graph* g, Vertex* v) {
	Vertex* vertexList = g->vertices;
	if (vertexList == NULL) {
		g->vertices = v;
	}
	else {
		while(vertexList->next != NULL) {
			vertexList = vertexList->next;
		}
		vertexList->next = v;
	}
	v->index = g->vertexCount++;
}
void addEdge(Vertex* v, Edge* e) {
	if (v->AdjacencyList == NULL) {
		v->AdjacencyList = e;
	}
	else {
		Edge* adjList = v->AdjacencyList;
		while(adjList->next != NULL) {
			adjList = adjList->next;
		}
		adjList->next = e;
	}
}
void printGraph(Graph* g) {
	Vertex* v = NULL;
	Edge* e = NULL;
	if ((v = g->vertices) == NULL) {
		return;
	}


	while(v!= NULL) {
		printf("%c : ", v->Data);
		if ((e = v->AdjacencyList) == NULL) {
			v = v->next;
			printf("\n");
			continue;
		}


		while(e != NULL) {
			printf("%c[%d] ", e->target->Data, e->weight);
			e = e->next;
		}
		printf("\n");
		v = v->next;
	}
	printf("\n");
}
```

## 그래프 탐색 방법

### 1. 깊이 우선 탐색 (Depth First Search : DFS)

-	더 나아갈 길이 보이지 않을 때까지 깊이 들어간다.
-	한 방향으로 계속 가다가 더 이상 갈수 없게 되면 다시 가장 가까운 갈림길로 돌아와서 다른 방향으로 탐색을 진행  
-	길이 나오지 않을 때까지 그래프의 정점을 타고 깊이 들어가다 더 이상 방문해왔던 정점말고는 다른 이웃을 갖고 있지 않은 정점을 만나면 뒤로 돌아와 다른 경로로 뻗어 있는 정점을 타고 방문을 재개하는 방식
	

#### DFS 알고리즘 	
1.	시작 정점을 밟은 후 이 정점을 '방문했음'으로 표시
2.	이 정점과 이웃하고 있는 정점(인접정점)중에서 아직 방문하지 않은 곳을 선택하고 이를 시작 정점으로 삼아 다시 깊이 우선탐색을 시작(1단계를 다시하는 것)
3.	정점에 더 이상 방문하지 않은 인접 정점이 없으면 이전 정점으로 돌아가서 2단계를 수행
4.	이전 정점으로 돌아가도 더 이상 방문할 이웃이 없으면 그래프의 모든 정점을 방문했으므로 탐색을 종료

```cs
//u 아직방문하지 않은 정점
depth_first_search(v)
	v를 방문했다고 표시
	for v에 인접한 정점 탐색 do
		if (탐색하지 못한 정점이 있다면)
			then depth_first_search(u)
```

#### DFS 소스코드

```cs
void DFS(Vertex* v) {
	//1. 이미 방문햇음을 표시
	Edge* e = NULL;
	printf("%d ", v->data);
	v->visited = visited; //이미 방문했다는 것을 표시
	e = v->adjacencyList; //인접한 정점 리스트
	while(e != NULL) { // 현재 정점의 모든 인접정점에 대해 DFS를 재귀적으로 호출
		if (e->target != NULL && e->target->Visited == NotVisited) {
			//인접데이터가 방문하지 않았으면 재귀호출
				DFS(e->target);
		}
		e = e->next;
	}
}
```

### 2. 너비 우선 탐색 (Breadth First Search : BFS)

-	시작 정점부터 가까운 정점을 먼저 방문하고 멀리 떨어져 있는 정점을 나중에 방문하는 순회방법
-	너비 우선 탐색을 하기 위해서 방문한 정점들을 차례로 저장하고 꺼낼 수 있는 Queue가 필요
-	정점이 방문될 때마다 큐에 방문한 정점을 삽입하고 더이상 방문할 인접 정점이 없는 경우 큐 앞에서 정점을 꺼내어 그 정점과 인접한 정점들을 차례대로 방문

#### BFS 알고리즘
1.	시작 정점을 '방문했음'으로 표시 하고 큐에 삽입
2.	큐로부터 정점을 제거(dequeue)하고 제거한 정점이 이웃하고 있는 인접 정점 중 아직 방문하지 않은 곳들을 '방문했음'으로 표시하고 큐에 삽입
3.	큐가 비게 되면 탐색이 끝난 것이고 큐가 빌때까지 2를 반복

```cs
//u 방문하지 않은 정점
breadth_first_search(v)
	v를 방문되었다고 표시;
	queue <- v; //큐에 정점 v 삽입
	while (not is empty(queue)) do
		queue에서 정점 w를 삭제
		for 인접정점 탐색 do
		 if (아직 방문되지 않은 정점이 있다면)
		 	then u를 큐에 삽입
		 	 		 방문되었다고 표시
```

#### BFS 소스 코드

```cs
	void BFS(Vertex* v, LinkedQueue* queue) {
		Edge* e = NULL;
		printf("%d ",v->Data);
		v->Visited = Visited; //방문했음을 표시
		LQ_Enqueue(&queue, LQ_CreateNode(v)); //시작정점에 큐 삽입

		while(!LQ_isEmpty(queue)) {
			Node* popped = LQ_Dequeue(&queue); //큐 제거
			v = popped->Data;
			e = v->adjacencyList;
			while(e != NULL) { //인접한 정점 조사
				v = E->target;
				if (v != NULL && v->Visited == NotVisited) { //미방문 정점만 방문
					printf("%d ", v->data);
					v->Visited = Visited;
					LQ_Enqueue(&queue, LQ_CreateNode(v));
				}
				e = e->next;
			}
		}
	}
```
