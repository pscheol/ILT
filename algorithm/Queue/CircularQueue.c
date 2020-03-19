//
// Created by Paik Seung Cheol on 2017. 9. 9..
//

#include "CircularQueue.h"

void createQueue(Queue **queue, int capacity) {
    (*queue) = (Queue*)malloc(sizeof(Queue));
    (*queue)->capacity = capacity;
    (*queue)->front = 0;
    (*queue)->rear = 0;
    (*queue)->nodes = (Node*)malloc(sizeof(Node)* (capacity+1));
}
void enqueue(Queue *queue, element data) {
    int rear = (queue->rear) % queue->capacity;
    queue->nodes[rear].data = data;
    queue->rear = rear + 1;
}
void dequeue(Queue *queue) {
    int front = (queue->front) % queue->capacity;
    queue->nodes[front].data;
    queue->front = front+1;
}
int isEmpty(Queue *queue) {
    if (queue->front == queue->rear) {
        return TRUE;
    }
    return FALSE;
}
int isFull(Queue *queue) {
    if (queue->front == ()(queue->rear+1) % queue->capacity)) {
        return TRUE;
    }
    return FALSE;
}
