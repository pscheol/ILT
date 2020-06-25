//
// Created by Paik Seung Cheol on 2017. 9. 20..
//

#ifndef ADJACENCYMATRIX_H
#define ADJACENCYMATRIX_H

#define MAX_VERTICES 50
typedef struct _GraphType {
    int n; //정점의 개수
    int adj_matrix[MAX_VERTICES][MAX_VERTICES];

}GraphType;

void graph_init(GraphType* graph);
void insertvertex(GraphType *g, int v);
void insert_edge(GraphType* g, int start, int end);
#endif //ADJACENCYMATRIX_H
