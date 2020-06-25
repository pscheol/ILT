//
// Created by Paik Seung Cheol on 2017. 9. 20..
//

#ifndef GRAPH_H
#define GRAPH_H

#include <stdio.h>
#include <stdlib.h>

enum Visited {visited, notVisited};

typedef char Element;
typedef struct tagVertex {
    Element data;
    int visited; //방문여부 확인
    int index;
    struct tagVertex* next;
    struct tagEdge* adjacencyList; //인접리스트
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

#endif //GRAPH_H
