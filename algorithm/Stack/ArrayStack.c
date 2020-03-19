#include "ArrayStack.h"

void createStack(Stack** stack, int capacity) {
    (*stack) = (Stack*)malloc(sizeof(Stack));
    (*stack)->capacity = capacity;
    (*stack)->top = -1;
    (*stack)->nodes = (Node*) malloc(sizeof(Node)* capacity);
}

int isFull(Stack* stack) {
    if ((stack)->top == (stack)->capacity-1) {
        return TRUE;
    }
    return FALSE;
}
int isEmpty(Stack* stack) {
    if ((stack)->top == -1) {
        return TRUE;
    }
    return FALSE;
}
void push(Stack* stack, int data) {
    if (isFull(stack)) {
        printf("stack overflow\n");
        exit(1);
    }
    stack->nodes[++stack->top].data = data;
}
int popup(Stack* stack) {
    if (isEmpty(stack)) {
        printf("is empty\n");
        exit(1);
    }
    return stack->nodes[stack->top--].data;
}

int peek(Stack* stack) {
    if (isEmpty(stack)) {
        printf("is empty\n");
        exit(1);
    }
    return stack->nodes[stack->top].data;
}
