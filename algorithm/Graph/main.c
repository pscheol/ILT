
#include "GraphTraversal.h"

void DFSandBFS() {
    int mode = 0;
    Graph* g = createGraph();

    Vertex* v1 = createVertex('1');
    Vertex* v2 = createVertex('2');
    Vertex* v3 = createVertex('3');
    Vertex* v4 = createVertex('4');
    Vertex* v5 = createVertex('5');
    Vertex* v6 = createVertex('6');
    Vertex* v7 = createVertex('7');

    addVertex(g, v1);
    addVertex(g, v2);
    addVertex(g, v3);
    addVertex(g, v4);
    addVertex(g, v5);
    addVertex(g, v6);
    addVertex(g, v7);

    addEdge(v1,createEdge(v1, v2, 0));
    addEdge(v1,createEdge(v1, v3, 0));


    addEdge(v2,createEdge(v2, v4, 0));
    addEdge(v2,createEdge(v2, v5, 0));

    addEdge(v3,createEdge(v3, v4, 0));
    addEdge(v3,createEdge(v3, v6, 0));

    addEdge(v4,createEdge(v4, v5, 0));
    addEdge(v4,createEdge(v4, v7, 0));

    addEdge(v5,createEdge(v5, v7, 0));

    addEdge(v6,createEdge(v6, v7, 0));

    printf("Enter traversal mode (0:DEF, 1:BFS) : ");
    scanf("%d", &mode);
    if (mode == 0) {
        DFS(g->vertices);
    } else {
        Queue* queue = createQueue();
        BFS(g->vertices, queue);
        destoryQueue(queue);
    }
}
int main() {
    DFSandBFS();
    return 0;
}
