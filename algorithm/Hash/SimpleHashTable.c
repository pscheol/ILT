//
// Created by Paik Seung Cheol on 2017. 9. 18..
//

#include "Hash.h"

HashTable* SHT_createHashTable(int tableSize) {
    HashTable* HT = (HashTable*)malloc(sizeof(HashTable));
    HT->table = (Node*)malloc(sizeof(Node)* tableSize);
    HT->tableSize = tableSize;
    return HT;
}
void SHT_Put(HashTable* HT, KeyType key, ValueType value) {
    int adr = SHT_Hash(key, HT->tableSize);
    HT->table[adr].key = key;
    HT->table[adr].value = value;
}
ValueType SHT_Get(HashTable* HT, KeyType key) {
    int adr = SHT_Hash(key, HT->tableSize);
    return HT->table[adr].value;
}
void SHT_DestoryHashTable(HashTable* HT) {
    free(HT->table);
    free(HT);
}
int SHT_Hash(int input, int tblSize) {
    return input % tblSize;
}

int Digit_Folding_Hash(char* key, int length, int tableSize) {
    int i = 0;
    int hashValue = 0;
    for (i = 0; i<length; i++) {
        hashValue += key[i];
    }
    return hashValue;
}