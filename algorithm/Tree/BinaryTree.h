//
// Created by Paik Seung Cheol on 2017. 9. 11..
//

#include <stdio.h>
#include <stdlib.h>

#ifndef BINARYTREE_H
#define BINARYTREE_H

typedef char Element;
typedef struct _Node {
    struct _Node* left;
    struct _Node* right;
    Element data;
}TreeNode;

TreeNode* createNode(Element element);
void inOrderTree(TreeNode* node);
void preOrderTree(TreeNode* node);
void postOrderTree(TreeNode* node);

#endif //BINARYTREE_H
