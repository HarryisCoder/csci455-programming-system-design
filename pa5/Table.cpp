
// CSCI 455 PA5
// Spring 2017

// Table.cpp  Table class implementation


/*
 * Modified 11/22/11 by CMB
 *   changed name of constructor formal parameter to match .h file
 */

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {

	hashSize = HASH_SIZE;
	table = new ListType[hashSize];
	initializeTable(table);
    numOfEntries = 0;
  	usedBucketSize = 0;
}


Table::Table(unsigned int hSize) {

	hashSize = hSize;
	table = new ListType[hashSize];
	initializeTable(table);
  	numOfEntries = 0;
  	usedBucketSize = 0;
}


int * Table::lookup(const string &key) {

	ListType list = table[hashCode(key)];
  	return listLookup(list, key);   
}

bool Table::remove(const string &key) {

	int * target = lookup(key);
	if(target == NULL){
		return false;
	}
	else{
		numOfEntries--;
		listRemove(table[hashCode(key)], key);
		if(table[hashCode(key)] == NULL){
			usedBucketSize--;
		}
		return true;
	} 
}

bool Table::insert(const string &key, int value) {

	int * target = lookup(key);
	if(target == NULL){
		if(table[hashCode(key)] == NULL){
			usedBucketSize++;
		}
		listAdd(table[hashCode(key)], key, value);
		numOfEntries++;
		return true;
	}
	else{
  	return false;  // dummy return value for stub
	}
}

int Table::numEntries() const {
  	return numOfEntries;      
}

void Table::printAll() const {

	for(int i = 0; i < hashSize; i++){
		listPrint(table[i]);
	}
}

void Table::hashStats(ostream &out) const {

	out<<"number of buckets:"<<hashSize<<endl;
	out<<"number of entries:"<<numOfEntries<<endl; 
	out<<"number of non-empty buckets:"<<usedBucketSize<<endl; 
	int maxChainSize = getMaxChainSize();
	out<<"longest chain:"<<maxChainSize<<endl;
  
}


// add definitions for your private methods here
int Table::getMaxChainSize() const{

	int maxChainSize = 0;
	for(int i = 0; i < hashSize; i++){
		ListType currentList = table[i];
		int currentListSize = listSize(currentList);
		if(maxChainSize < currentListSize){
			maxChainSize = currentListSize;
		}
		currentListSize = 0;
	}

	return maxChainSize;
}

void Table::initializeTable(ListType *&table){
	for(int i = 0; i < hashSize; i++){
		table[i] = NULL;
	}
}
