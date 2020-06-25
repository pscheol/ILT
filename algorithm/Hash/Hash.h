//
// Created by Paik Seung Cheol on 2017. 9. 14..
//

#ifndef HASH_H
#define HASH_H

#include <Stdio.h>
#include <stdlib.h>
#include <memory.h>
#include <string.h>

typedef char* KeyType;
typedef char* ValueType;

typedef struct _tagNode {
    KeyType key;
    ValueType  value;
    struct _tagNode *next;
}Node;

typedef Node* List;


typedef struct _HashTable {
    int tableSize;
    List* table;

}_HashTable;

typedef struct tagHashTable {
    int tableSize;
    Node* table;
}HashTable;


HashTable* SHT_createHashTable(int tableSize);
void SHT_Put(HashTable* HT, KeyType key, ValueType value);
ValueType SHT_Get(HashTable* HT, KeyType key);
void SHT_DestoryHashTable(HashTable* HT);
int SHT_Hash(int input, int tblSize);
int Digit_Folding_Hash(char* key, int length, int tableSize);


HashTable* CHT_CreateHashTable(int tableSize);
void CHT_DestoryHashTable(_HashTable* HT);

Node* CHT_CreateNode(KeyType key, ValueType value);
void CHT_DestoryList(List List);
void CHT_DestoryNode(Node* node);
void CHT_Set(_HashTable* HT, KeyType key, ValueType value);
int CHT_Hash(KeyType key ,int length, int tableSize);
ValueType CHT_Get(_HashTable* HT, KeyType key);
#endif //HASH_H
