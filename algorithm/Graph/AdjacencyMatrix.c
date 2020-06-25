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
    if (start >= g->n || end>= g->n) {
        fprintf(stderr, "grapth : error vertex number\n");
    }
    g->adj_matrix[start][end] = 1;
    g->adj_matrix[end][start] = 1;
}
