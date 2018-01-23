
// CSCI 455 PA5
// Spring 2017


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
  key = theKey;
  value = theValue;
  next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
  key = theKey;
  value = theValue;
  next = n;
}


//*************************************************************************
// put the function definitions for your list functions below

// add node(theKey, theValue) to the end of list
void listAdd(ListType &list, const string &theKey, int theValue){

	ListType newNode = new Node(theKey, theValue);
	if(list == NULL){
		list = newNode;
	}
	else{
		ListType node = list;
		while(node->next != NULL){
			node = node->next;
		}
		node->next = newNode;
	}
};

// remove node(theKey, theValue) from the list
// return false if this node is not in the list
bool listRemove(ListType &list, const string &theKey){
	
	if(list != NULL){
		// if list contains only one node
		if(list->next == NULL){
			if(list->key == theKey){
				list = NULL;
				return true;
			}
			else{
				return false;
			}
		}
		// if list contains more than one node
		else{
			ListType node = list;
			while(node->next != NULL){
				if(node->next->key == theKey){
					node->next = node->next->next;
					return true;
				}
				node = node->next;
			}
			return false;
		}
	}
	return false;
};

// look up node(theKey, theValue) in the list, return the pointer that
// points to the value of that node if this node is found, return false 
// if this node is not found
int * listLookup(ListType list, const string &key) {

	while(list != NULL){
		if(list->key == key){
			return &list->value;
		}
		list = list->next;
	}
  	return NULL;   
}

// return the size (no. of nodes) of list
int listSize(ListType list){
	
	int size = 0;
	if(list != NULL){
		while(list != NULL){
			size ++;
			list = list->next;
		}
	}
	return size;
};

// print every nodes in the list one by one
void listPrint(ListType list){

	while(list != NULL){
	cout <<list->key<<" "<<list->value<<endl;
	list = list->next;
	}

};

