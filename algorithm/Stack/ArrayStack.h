#ifndef ARRAYSTACK_H
#define ARRAYSTACK_H


#include <stdio.h>
#include <stdlib.h>


#define TRUE 1
#define FALSE 0

typedef struct _Node {
    int data;
}Node;

typedef struct _Stack {
    int top;
    int capacity;
    Node* nodes;
}Stack;

void createStack(Stack** stack, int capacity);
int isFull(Stack* stack);
int isEmpty(Stack* stack);
void push(Stack* stack, int data);
int popup(Stack* stack);
int peek(Stack* stack);
#endif //ARRAYSTACK_H
