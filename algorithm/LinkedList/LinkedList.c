#include "LinkedList.h"

Node* createNode(int data) {
    Node *newNode = (Node*)malloc(sizeof(Node));
    newNode->data =data;
    newNode->next = NULL;
    return newNode;
}

void destoryNode(Node *node) {
    free(node);
}

void add(Node** head, Node* newNode) {
    if ((*head) == NULL) {
        *head = newNode;
    } else {
        Node* tail = (*head);
        while(tail->next != NULL) {
            tail = tail->next;
        }
        tail->next = newNode;
    }
}

void addAfter(Node* cur, Node* newNode) {
    newNode->next = cur->next;
    cur->next = newNode;
}
void addHead(Node** head, Node* newHead) {
    if (*head == NULL) {
        (*head) = newHead;
    } else {
        newHead->next = (*head);
        (*head) = newHead;
    }
}
int removeNode(Node** head, Node* remove) {
    if (*head == remove) {
        *head = remove->next;
    } else {
        Node* cur = *head;
        while(cur != NULL && cur->next != remove) {
            cur= cur->next;
        }
        if (cur != NULL) {
            cur->next = remove->next;
        }
    }
    destoryNode(remove);
}
Node* getNode(Node* head, int idx) {
    Node* cur = head;
    while(cur != NULL) {
        cur= cur->next;
    }
    return cur;
}
