//
// Created by Paik Seung Cheol on 2017. 9. 13..
//

#ifndef HEAPNODE_H
#define HEAPNODE_H

#include <stdio.h>
#include <stdlib.h>
#include <memory.h>

typedef int PriorityType;
typedef struct _HeapNode {
    PriorityType priority;
    void* data;
}HeapNode;

typedef struct _Heap {
    HeapNode* nodes;
    int Capacity;
    int UsedSize;
}Heap;

Heap* init(int capacity);
void HEAP_InsertByMin(Heap* heap, HeapNode newNode);
void HEAP_InsertByMax(Heap* heap, HeapNode newNode);
void HEAP_DeleteByMin(Heap* heap, HeapNode* root);
void HEAP_DeleteByMax(Heap* heap, HeapNode* root);
int HEAP_GetParent(int curPosition);
int HEAP_GetLeftChild(int index);
int HEAP_GetRightChild(int index);
void HEAP_SwapNode(Heap* heap, int idx1, int idx2);
int isEmpty(Heap* heap);
void heapPrint(Heap* heap);
#endif //HEAPNODE_H
