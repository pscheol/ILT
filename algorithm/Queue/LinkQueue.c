#include "LinkQueue.h"

void createQueue(LinkedQueue **queue) {
    (*queue) = (LinkedQueue*)malloc((sizeof(LinkedQueue)));
    (*queue)->front = NULL;
    (*queue)->rear = NULL;
    (*queue)->count = 0;
}
Node *createNode(element data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;
}
void enqueue(LinkedQueue *queue, Node* newNode) {
    if (queue->front == NULL) {
        queue->front = newNode;
        queue->rear = newNode;
        queue->count++;
    } else {
        queue->rear->next = newNode;
        queue->rear = newNode;
        queue->count++;
    }
}
void dequeue(LinkedQueue *queue) {
    Node* front = queue->front;
    if (queue->front->next == NULL) {
        queue->front = NULL;
        queue->rear = NULL;
    } else {
        queue->front =queue->front->next;
    }
    queue->count--;
}
int isEmpty(LinkedQueue *queue) {
    if (queue->count == 0) {
        return TRUE;
    }
    return FALSE;
}
