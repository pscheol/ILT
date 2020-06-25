//
// Created by Paik Seung Cheol on 2017. 9. 13..
//

#include "HeapNode.h"



extern Heap* init(int capacity);
extern void HEAP_InsertByMin(Heap* heap, HeapNode newData);
extern void HEAP_InsertByMax(Heap* heap, HeapNode newData);
extern void HEAP_DeleteByMin(Heap* heap, HeapNode* root);
extern void HEAP_DeleteByMax(Heap* heap, HeapNode* root);
extern int HEAP_GetParent(int curPosition);
extern void HEAP_SwapNode(Heap* heap, int idx1, int idx2);
extern int HEAP_GetLeftChild(int index);
extern int HEAP_GetRightChild(int index);
extern int isEmpty(Heap* heap);
extern void heapPrint(Heap* heap);



Heap* init(int capacity) {
    Heap* heap = (Heap*)malloc(sizeof(Heap));
    heap->nodes = (HeapNode*)malloc((sizeof(HeapNode) * capacity));
    heap->Capacity = capacity;
    heap->UsedSize = 0;
    return heap;
}


void HEAP_InsertByMin(Heap* heap, HeapNode newNode) {

    int curPosition = heap->UsedSize;
    int parentPosition = HEAP_GetParent(curPosition);

    if (heap->Capacity == heap->UsedSize) {
        heap->Capacity *= 2;
        heap->nodes = (HeapNode *) realloc(heap->nodes, sizeof(HeapNode) * heap->Capacity);
    }
    heap->nodes[curPosition] = newNode;
    while(curPosition > 0 && heap->nodes[curPosition].priority < heap->nodes[parentPosition].priority) {
        HEAP_SwapNode(heap,curPosition, parentPosition);
        curPosition = parentPosition;
        parentPosition = HEAP_GetParent(curPosition);
    }

    heap->UsedSize++;
}

void HEAP_InsertByMax(Heap* heap, HeapNode newNode) {
    int curPosition = heap->UsedSize;
    int parentPosition = HEAP_GetParent(curPosition);

    if (heap->Capacity == heap->UsedSize) {
        heap->Capacity *= 2;
        heap->nodes = (HeapNode*)realloc(heap->nodes, sizeof(HeapNode) *heap->Capacity);
    }
    heap->nodes[curPosition] = newNode;

    while(curPosition > 0 && heap->nodes[curPosition].priority > heap->nodes[parentPosition].priority) {
        HEAP_SwapNode(heap, curPosition, parentPosition);
        curPosition = parentPosition;
        parentPosition = HEAP_GetParent(curPosition);
    }
    heap->UsedSize++;
}

void HEAP_DeleteByMin(Heap* heap, HeapNode* root) {
    int parentPosition = 0;
    int leftPosition = 0;
    int rightPosition = 0;

    memcpy(root, &heap->nodes[0], sizeof(HeapNode));
    memset(&heap->nodes[0], 0, sizeof(HeapNode));


    heap->UsedSize--;
    HEAP_SwapNode(heap, 0,heap->UsedSize);
    leftPosition = HEAP_GetLeftChild(0);
    rightPosition = HEAP_GetRightChild(0);

    while(1) {
        int selectedChild = 0;
        if (leftPosition >= heap->UsedSize) {
            break;
        }
        if (rightPosition >= heap->UsedSize) {
            selectedChild = leftPosition;
        }
        else {
            if (heap->nodes[leftPosition].priority > heap->nodes[rightPosition].priority) {
                selectedChild = rightPosition;
            }
            else {
                selectedChild = leftPosition;
            }
        }

        if (heap->nodes[selectedChild].priority < heap->nodes[parentPosition].priority) {
            HEAP_SwapNode(heap, parentPosition, selectedChild);
            parentPosition = selectedChild;
        }
        else {
            break;
        }
        leftPosition = HEAP_GetLeftChild(parentPosition);
        rightPosition = HEAP_GetRightChild(parentPosition);
    }
    if (heap->UsedSize < (heap->Capacity/2)) {
        heap->Capacity /= 2;
        heap->nodes = (HeapNode*)realloc(heap->nodes, (sizeof(HeapNode) * heap->Capacity));
    }
}

void HEAP_DeleteByMax(Heap* heap, HeapNode* root) {
    int parentPosition = 0;
    int leftPosition = 0;
    int rightPosition = 0;

    memcpy(root, &heap->nodes[0], sizeof(HeapNode));
    memset(&heap->nodes[0], 0, sizeof(HeapNode));


    heap->UsedSize--;
    HEAP_SwapNode(heap, 0,heap->UsedSize);
    leftPosition = HEAP_GetLeftChild(0);
    rightPosition = HEAP_GetRightChild(0);


    while(1) {
        int selectedPosition = 0;
        if (leftPosition >= heap->UsedSize) {
            return;
        }
        if (rightPosition >= heap->UsedSize) {
            selectedPosition = leftPosition;
        }

        if (heap->nodes[leftPosition].priority > heap->nodes[rightPosition].priority) {
            selectedPosition = leftPosition;
        }
        else {
            selectedPosition = rightPosition;
        }

        if (heap->nodes[selectedPosition].priority > heap->nodes[parentPosition].priority) {
            HEAP_SwapNode(heap, selectedPosition, parentPosition);
        }
        else {
            return;
        }
        parentPosition = selectedPosition;
        leftPosition = HEAP_GetLeftChild(parentPosition);
        rightPosition = HEAP_GetRightChild(parentPosition);
    }

    if (heap->UsedSize < (heap->Capacity/2)) {
        heap->Capacity /= 2;
        heap->nodes = (HeapNode*)realloc(heap->nodes, (sizeof(HeapNode) * heap->Capacity));
    }
}

int HEAP_GetParent(int curPosition) {
    return (curPosition -1) /2;
}
int HEAP_GetLeftChild(int index) {
    return (index * 2) + 1;
}
int HEAP_GetRightChild(int index) {
    return (index * 2) + 2;
}
void HEAP_SwapNode(Heap* heap, int idx1, int idx2) {
    int size = sizeof(HeapNode);
    HeapNode* tmp = (HeapNode*)malloc(size);
    memcpy(tmp,&heap->nodes[idx1], size);
    memcpy(&heap->nodes[idx1], &heap->nodes[idx2], size);
    memcpy(&heap->nodes[idx2], tmp, size);
    free(tmp);
}
int isEmpty(Heap* heap) {
    return heap->UsedSize == 0;
}

void heapPrint(Heap* heap) {
    int i = 0;
    for (i = 0; i< heap->UsedSize; i++) {
        printf("%d ", heap->nodes[i].data);
    }
    printf("\n");
}