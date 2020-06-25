//
// Created by Paik Seung Cheol on 2017. 9. 14..
//


#include "HeapNode.h"

void printNode(HeapNode* node) {
    printf("작업명 : %s (우선순위 : %d)\n",node->data, node->priority);
}
int main(int argc, char* argv[]) {
    Heap* heap = init(3);
    Heap delete;

    HeapNode Nodes[7] = {
            {34,(void*)"일하기"},
            {12,(void*)"이닦기"},
            {84,(void*)"밥먹기"},
            {35,(void*)"회의하기"},
            {66,(void*)"디버깅"},
            {45,(void*)"코딩"}
    };
    HEAP_InsertByMax(heap, Nodes[0]);
    HEAP_InsertByMax(heap, Nodes[1]);
    HEAP_InsertByMax(heap, Nodes[2]);
    HEAP_InsertByMax(heap, Nodes[3]);
    HEAP_InsertByMax(heap, Nodes[4]);
    HEAP_InsertByMax(heap, Nodes[5]);

    printf("큐에 남아있는작업수 : %d\n", heap->UsedSize);

    while(!isEmpty(heap)) {
        HEAP_DeleteByMax(heap, &delete);
        printNode(&delete);

    }
    free(heap);

    return 0;
}