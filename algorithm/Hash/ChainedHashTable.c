//
// Created by Paik Seung Cheol on 2017. 9. 18..
//

#include "Hash.h"


int CHT_Hash(KeyType key ,int length, int tableSize) {
    int hashKey = 0;
    int i = 0 ;
    for (i = 0; i<length; i++) {
        hashKey = (hashKey << 3) + key[i];
    }
    return hashKey % tableSize;
}

void CHT_Set(_HashTable* HT, KeyType key, ValueType value) {
    int idx = CHT_Hash(key, strlen(key), HT->tableSize);
    Node* newNode = CHT_CreateNode(key,value);
    if (HT->table[idx] == NULL) {
        HT->table[idx] = newNode;
        printf("put : key(%s), idx(%d)\n", key,idx);
    } else {
        List list = HT->table[idx];
        newNode->next = list;
        HT->table[idx] = newNode;
        printf("Collision occured : key(%s), idx(%d)\n", key,idx);
    }
}

/**
 * get은 이미 충돌되었다는 것을 가정해야함
 * @param HT
 * @param key
 * @return
 */
ValueType CHT_Get(_HashTable* HT, KeyType key) {
    int idx = CHT_Hash(key, strlen(key), HT->tableSize);

    List list = HT->table[idx];
    List Target = NULL;

    if (list == NULL) {
        return NULL;
    }
    while(1) {
        if (strcmp(list->key, key) == 0) {
            Target = list;
            break;
        }
        if (list->next == NULL) {
            break;
        }
        else {
            list = list->next;
        }
    }
    return Target->value;

}

HashTable* CHT_CreateHashTable(int tableSize) {
    _HashTable* hashTable = (_HashTable*)malloc((sizeof(_HashTable)));
    hashTable->table = (List*)malloc((sizeof(List) * tableSize));
    memset(hashTable->table,0, sizeof(List) *tableSize);
    hashTable->tableSize = tableSize;
    return hashTable;
}


Node* CHT_CreateNode(KeyType key, ValueType value) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->value = value;
    newNode->key = key;
    newNode->next = NULL;
    return newNode;
}

void CHT_DestoryHashTable(_HashTable* HT) {
    int i =0;
    for (i=0;i<HT->tableSize; i++) {
        List L = HT->table[i];
        CHT_DestoryList(L);
    }
    free(HT->table);
    free(HT);
}

void CHT_DestoryNode(Node* node) {
    free(node->key);
    free(node->value);
    free(node);
}

void CHT_DestoryList(List list) {
    if (list == NULL) {
        return;
    }
    if (list->next != NULL) {
        CHT_DestoryList(list->next);
    }
    CHT_DestoryNode(list);
}