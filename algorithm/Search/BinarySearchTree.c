//
// Created by Paik Seung Cheol on 2017. 9. 19..
//

#include "BinarySearchTree.h"

BSTNode* BST_CreateNode(Element newData) {
    BSTNode* newNode = (BSTNode*)malloc(sizeof(BSTNode));
    newNode->data = newData;
    newNode->left = NULL;
    newNode->right = NULL;
    return newNode;
}
void BST_DestoryNode(BSTNode* node) {
    if (node != NULL) {
        free(node);
    }
}
void BST_DestoryTree(BSTNode* tree) {
    if (tree != NULL) {
        free(tree);
    }
}
BSTNode* BST_SearchNode(BSTNode* tree, Element target) {
     if (tree->data == target) {
         return tree;
     } else if (tree->data > target) {
         return BST_SearchNode(tree->left , target);
     } else {
         return BST_SearchNode(tree->right, target);
     }
}
BSTNode* BST_SearchMinNode(BSTNode* tree) {
    if (tree == NULL) {
        return NULL;
    }
    if (tree->left == NULL) {
        return tree;
    } else {
        return BST_SearchMinNode(tree->left);
    }
}
void BST_InsertNode(BSTNode* tree, BSTNode *child) {
    if (tree->data > child->data) {
        if (tree->left == NULL) {
            tree->left = child;
        }
        else {
            BST_InsertNode(tree->left, child);
        }
    } else {
        if (tree->right == NULL) {
            tree->right = child;
        }
        else {
            BST_InsertNode(tree->right, child);
        }
    }
}
BSTNode* BST_RemoveNode(BSTNode* tree, BSTNode *parent, Element target) {
    BSTNode* removed = NULL;

    if (tree == NULL) {
        return NULL;
    }
    if (tree->data > target) {
        removed = BST_RemoveNode(tree->left,tree, target);
    } else if(tree->data < target) {
        removed = BST_RemoveNode(tree->right, tree, target);
    } else {
        removed = tree;
        if (tree->left == NULL && tree->right == NULL) {
            if (parent->left == tree) {
                parent->left = NULL;
            }
            else {
                parent->right = NULL;
            }
        }
        else {
            if (tree->left != NULL && tree->right != NULL) {
                BSTNode* MinNode = BST_SearchMinNode(tree);
                removed = BST_RemoveNode(tree, NULL, MinNode->data);
                tree->data = MinNode->data;
            }
            else {
                BSTNode* temp = NULL;
                if (tree->left != NULL) {
                    temp = tree->left;
                }
                else {
                    temp = tree->right;
                }
                if (parent->left == tree) {
                    parent->left= temp;
                }
                else {
                    parent->right = temp;
                }
            }
        }
    }
    return removed;
}
void BST_InorderPrintTree(BSTNode* node) {
    if (node == NULL) {
        return;
    }
    BST_InorderPrintTree(node->left);
    printf("%d ", node->data);
    BST_InorderPrintTree(node->right);
}