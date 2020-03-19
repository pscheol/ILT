//
// Created by Paik Seung Cheol on 2017. 9. 9..
//

#include <stdio.h>
#include <stdlib.h>


#ifndef CIRCULARQUEUE_H
#define CIRCULARQUEUE_H

#define TRUE 1
#define FALSE 0

typedef int element;
typedef struct _Node {
    element data;
}Node;

typedef struct _Queue {
    int capacity;
    int front;
    int rear;
    Node* nodes;
}Queue;

void createQueue(Queue **queue, int capacity);
void enqueue(Queue *queue, element data);
void dequeue(Queue *queue);
int isEmpty(Queue *queue);
int isFull(Queue *queue);

#endif //CIRCULARQUEUE_H
