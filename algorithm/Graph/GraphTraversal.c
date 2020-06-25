//
// Created by Paik Seung Cheol on 2017. 9. 20..
//

#include "GraphTraversal.h"


/**
 * 1. v 방문한다 표시
 * 2. v의 인접리스트 방문
 * 3. v인접 리스트에서 방문하지 않은 u 선택
 * 4. 위 조건 반복
 * @param v
 */
void DFS(Vertex* v) {
    Edge* edge = NULL;
    printf("%c ",v->data);
    v->visited = visited;
    edge = v->adjacencyList;
    while(edge != NULL) {
        if (edge->target != NULL && edge->target->visited == notVisited) {
            DFS(edge->target);
        }
        edge = edge->next;
    }

}
/**
 * 1. v방문한다 표시
 * 2. v를 큐에 삽입
 * 3. v의 인접리스트 방문
 * 4. 큐에 저장된 v를 가져옴
 * 5. 인접리스트에서 방문하지 않은 u 선택
 * 6. 방문하지 않은 데이터 큐에 삽입
 * 7. 다 찾을 때까지 반복
 * @param v
 * @param queue
 */
void BFS(Vertex* v, Queue* queue) {
    Edge* edge = NULL;
    printf("%c ", v->data);
    v->visited = visited;
    enqueue(queue, createNode(v));

    while(!isEmpty(queue)) {
        QueueNode* queueNode = dequeue(queue);
        v = queueNode->vertex;
        edge = v->adjacencyList;
        while (edge != NULL) {
            v = edge->target;
            if (v != NULL && v->visited == notVisited) {
                printf("%c ", v->data);
                v->visited = visited;
                enqueue(queue, createNode(v));
            }
            edge = edge->next;
        }

    }
}