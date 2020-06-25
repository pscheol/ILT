//
// Created by Paik Seung Cheol on 2017. 9. 21..
//

#include "LinkedListQueue.h"


Queue* createQueue() {
    Queue* newQueue = (Queue*)malloc(sizeof(Queue));
    newQueue->length = 0;
    newQueue->front = NULL;
    newQueue->rear = NULL;
    return newQueue;
}
QueueNode* createNode(Vertex* vertex) {
    QueueNode* newNode = (QueueNode*)malloc(sizeof(QueueNode));
    newNode->vertex = vertex;
    return newNode;
}
void destoryQueueNode(QueueNode *node) {
    free(node);
}
void destoryQueue(Queue* q) {
    while(q->front != NULL) {
        destoryQueueNode(q->front);
        q->front = q->front->next;
    }
    free(q);
}
void enqueue(Queue* queue, QueueNode* newNode) {
    if (queue->front == NULL) {
        queue->front = newNode;
        queue->rear = newNode;
    }
    else {
        queue->rear->next = newNode;
        queue->rear = queue->rear->next;
    }
    queue->length++;
}
QueueNode* dequeue(Queue* queue) {
    QueueNode* remove = queue->front;
    if (queue->front->next == NULL) {
        queue->front = NULL;
        queue->rear = NULL;
    } else {
        queue->front = remove->next;
    }
    queue->length--;
    return remove;
}
int isEmpty(Queue* q) {
    if (q->length == 0) {
        return TRUE;
    }
    return FALSE;
}