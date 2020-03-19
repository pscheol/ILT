//
// Created by Paik Seung Cheol on 2017. 9. 9..
//

#include <stdio.h>
#include <stdlib.h>

#ifndef LINKQUEUE_H
#define LINKQUEUE_H

#define TRUE 1
#define FALSE 0

typedef int element;
typedef struct _Node {
    element data;
    struct _Node* next;
}Node;

typedef struct _Queue {
    Node* front;
    Node* rear;
    int count;
}LinkedQueue;

Node *createNode(element data);
void createQueue(LinkedQueue **queue);
void enqueue(LinkedQueue *queue, Node* newNode);
void dequeue(LinkedQueue *queue);
int isEmpty(LinkedQueue *queue);

#endif //LINKQUEUE_H
