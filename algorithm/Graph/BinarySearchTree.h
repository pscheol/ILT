//
// Created by Paik Seung Cheol on 2017. 9. 19..
//

#ifndef BINARYSEARCHTREE_H
#define BINARYSEARCHTREE_H

#include <stdio.h>
#include <stdlib.h>

typedef int Element;
typedef struct _BSTNode{
    struct _BSTNode* left;
    struct _BSTNode* right;
    Element data;
}BSTNode;


BSTNode* BST_CreateNode(Element newData);
void BST_DestoryNode(BSTNode* node);
void BST_DestoryTree(BSTNode* tree);
BSTNode* BST_SearchNode(BSTNode* tree, Element target);
BSTNode* BST_SearchMinNode(BSTNode* tree);
void BST_InsertNode(BSTNode* tree, BSTNode *child);
BSTNode* BST_RemoveNode(BSTNode* tree, BSTNode *parent, Element target);
void BST_InorderPrintTree(BSTNode* node);

#endif //BINARYSEARCHTREE_H
