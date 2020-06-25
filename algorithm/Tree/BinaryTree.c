//
// Created by Paik Seung Cheol on 2017. 9. 11..
//

#include "BinaryTree.h"


TreeNode* createNode(Element element) {
    TreeNode* newNode = (TreeNode*)malloc((sizeof(TreeNode)));
    newNode->data = element;
    newNode->left = NULL;
    newNode->right = NULL;
}
/**
 * left->root->right;
 * @param node
 */
void inOrderTree(TreeNode* node) {
    if (node == NULL)
        return;
    inOrderTree(node->left);
    printf("%c ");
    inOrderTree(node->right);
}
/**
 * root->left->right;
 * @param node
 */
void preOrderTree(TreeNode* node) {
    if (node == NULL)
        return;
    printf("%c ");
    preOrderTree(node->left);
    preOrderTree(node->right);
}
/**
 * left->right->root;
 * @param node
 */
void postOrderTree(TreeNode* node) {
    if (node == NULL)
        return;
    postOrderTree(node->left);
    inOrderTree(node->right);
    printf("%c ");

}

int main() {
    TreeNode* rootNode = createNode('A');
    TreeNode* BNode = createNode('B');
    TreeNode* CNode = createNode('C');
    TreeNode* DNode = createNode('D');
    TreeNode* ENode = createNode('E');
    TreeNode* FNode = createNode('F');
    TreeNode* GNode = createNode('G');

    rootNode->left = BNode;
    rootNode->right = CNode;
    BNode->left = DNode;
    BNode->right = ENode;
    CNode->left = FNode;
    CNode->right = GNode;

    printf("InOrder : ");
    inOrderTree(&rootNode);
    printf("\n");
    printf("PreOrder : ");
    preOrderTree(&rootNode);
    printf("\n");
    printf("PostOrder : ");
    postOrderTree(&rootNode);
    printf("\n");

}