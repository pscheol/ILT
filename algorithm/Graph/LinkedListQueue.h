//
// Created by Paik Seung Cheol on 2017. 9. 21..
//

#ifndef LINKEDLISTQUEUE_H
#define LINKEDLISTQUEUE_H

#include "Graph.h"

#define TRUE 1
#define FALSE 0

typedef struct _tagQueueNode {
    Vertex* vertex;
    struct _tagQueueNode* next;
}QueueNode;
typedef struct tagQueue {
    int length;
    QueueNode* front;
    QueueNode* rear;
}Queue;

Queue* createQueue();
QueueNode* createNode(Vertex* vertex);
void destoryQueueNode(QueueNode *node);
void destoryQueue(Queue* q);
void enqueue(Queue* queue, QueueNode* newNode);
int isEmpty(Queue* q);
QueueNode* dequeue(Queue* queue);

#endif //LINKEDLISTQUEUE_H
