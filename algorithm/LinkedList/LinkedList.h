#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include <stdio.h>
#include <stdlib.h>


typedef struct _Node {
    int data;
    struct _Node *next;
}Node;

Node* createNode(int data);

void add(Node** head, Node* newNode);
void addAfter(Node* cur, Node* newNode);
void addHead(Node** head, Node* newHead);
int removeNode(Node** head, Node* remove);
Node* getNode(Node* head, int idx);
void destoryNode(Node *node);
int getLength(Node* head) {
    int cnt = 0;
    Node* cur = head;
    while(cur != NULL) {
        cur= cur->next;
        cnt++;
    }
    return cnt;
}

#endif