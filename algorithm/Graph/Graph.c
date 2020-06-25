
//
// Created by Paik Seung Cheol on 2017. 9. 20..
//

#include "Graph.h"



Graph* createGraph() {
    Graph* newGraph = (Graph*)malloc(sizeof(Graph));
    newGraph->vertexCount = 0;
    newGraph->vertices = NULL;
    return newGraph;
}

Vertex* createVertex(Element data) {
    Vertex* newVertex = (Vertex*)malloc(sizeof(Vertex));
    newVertex->data = data;
    newVertex->index = -1;
    newVertex->visited = notVisited;
    newVertex->next = NULL;
    newVertex->adjacencyList = NULL;
    return newVertex;
}
Edge* createEdge(Vertex* from, Vertex* target, int weight) {
    Edge* newEdge = (Edge*)malloc(sizeof(Edge));
    newEdge->weight = weight;
    newEdge->target = target;
    newEdge->from = from;
    newEdge->next = NULL;
    return newEdge;
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
    if (v->adjacencyList == NULL) {
        v->adjacencyList = e;
    }
    else {
        Edge* adjList = v->adjacencyList;
        while (adjList->next != NULL) {
            adjList = adjList->next;
        }
        adjList->next = e;
    }
}
void destoryGraph(Graph* g) {
    while(g->vertices != NULL) {
        Vertex* vertices = g->vertices->next;
        destoryVertex(vertices);
        g->vertices = vertices;
    }
    free(g);
}

void destoryVertex(Vertex* v) {
    while (v->adjacencyList != NULL) {
        Edge* edge = v->adjacencyList->next;
        destoryEdge(v->adjacencyList);
        v->adjacencyList = edge;
    }
    free(v);
}


void destoryEdge(Edge* e) {
    free(e);
}

void printGraph(Graph* g) {

}
