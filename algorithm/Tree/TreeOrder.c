//
// Created by Paik Seung Cheol on 2017. 9. 11..
//
#include <stdio.h>
#include <stdlib.h>


typedef struct _Node {
    struct _Node *left;
    struct _Node *right;
    char data;
}Node;

void preorder(Node* node) {
    if (node ==  NULL){
        return;
    }
    printf("%c", node->data);
    preorder(node->left);
    preorder(node->right);
}

void postorder(Node* node) {
    if (node == NULL) {
        return;
    }
    postorder(node->left);
    postorder(node->right);
    printf("%c", node->data);
}

void inorder(Node* node) {
    if (node == NULL) {
        return;
    }
    inorder(node->left);
    printf("%c", node->data);
    inorder(node->right);
}

Node *search(Node* node, char data) {
    if (node != NULL) {
        if (node->data == data) {
            return node;
        }
        if (node->left != NULL) {
            Node* tmp = search(node->left, data);

            if (tmp != NULL && tmp->data == data) {
                return tmp;
            }
        }
        if (node->right != NULL){
            Node* tmp = search(node->right, data);

            if (tmp != NULL && tmp->data == data) {
                return tmp;
            }
        }
    }
    return NULL;
}

Node* createNode(char data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->right = NULL;
    newNode->left = NULL;
    newNode->data = data;
    return newNode;
}
int main(int argc, char* argv[]) {
    int n = 0, i = 0;
    char ch1, ch2, ch3;

    printf("N 1 to 26=> ");
    scanf("%d",&n);

    if (n < 1 && n > 26) {
        return 0;
    }

    scanf(" %c %c %c", &ch1, &ch2, &ch3);

    Node* root = (Node*)malloc(sizeof(Node));
    root->data = ch1;
    root->left = createNode(ch2);
    root->right = createNode(ch3);

    for (i=1; i<n; i++) {
        fflush(stdin);
        scanf(" %c %c %c", &ch1, &ch2, &ch3);
        Node* tmp = search(root, ch1);
        if (ch2 != '.') {
            tmp->left = createNode(ch2);
        }
        if (ch3 != '.') {
            tmp->right = createNode(ch3);
        }

    }
    printf("\n");
    preorder(root);
    printf("\n");
    inorder(root);
    printf("\n");
    postorder(root);
    return 0;
}
