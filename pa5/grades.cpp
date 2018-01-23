
// CSCI 455 PA5
// Spring 2017

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

// insert student profile including name and score entered by users,
// If this name was already present, print a message to that effect, 
// and don't do the insert.
void insert(Table *&table);

// change the score of student whose name is entered by user,
// If this name is not present, print a message to that effect, 
// and don't do the change.
void change(Table *&table);

// print a summary of commands
void printCmdSummary();

// print the content of table, one student profile per line
void print(Table *&table);

// look up the score of the student whose name is entered by users,
// and return the score of that student.
// If this name is not present, print a message to that effect.
void lookup(Table *&table);

// remove the student file from the table whose name is entered by user.
// If this name is not present, print a message to that effect.
void removeStudent(Table *&table);

// read in and process the input command from users
void readCommand(Table *&table, string command);


int main(int argc, char * argv[]) {

  // gets the hash table size from the command line

  int hashSize = Table::HASH_SIZE;

  Table * grades;  // Table is dynamically allocated below, so we can call
                   // different constructors depending on input from the user.

  if (argc > 1) {
    hashSize = atoi(argv[1]);  // atoi converts c-string to int

    if (hashSize < 1) {
      cout << "Command line argument (hashSize) must be a positive number" 
	   << endl;
      return 1;
    }

    grades = new Table(hashSize);

  }
  else {   // no command line args given -- use default table size
    grades = new Table();
  }

  grades->hashStats(cout);

  // add more code here
  // Reminder: use -> when calling Table methods, since grades is type Table*

  bool keepgoing = true;
  string command;


  do{
    cout<<"cmd> ";
    cin>>command;
    if(command == "quit"){
      keepgoing = false;
    }
    else{
      readCommand(grades, command);
    }
  }while(keepgoing);


  return 0;
}

void readCommand(Table *&grades, string command){

    if(command == "insert"){
      insert(grades);
    }
    else if(command == "change"){
      change(grades);
    }
    else if(command == "lookup"){
      lookup(grades);
    }
    else if(command == "remove"){
      removeStudent(grades);
    }
    else if(command == "print"){
      print(grades);
    }
    else if(command == "size"){
      cout<<grades->numEntries()<<endl;
    }
    else if(command == "stats"){
      grades->hashStats(cout);
    }
    else if(command == "help"){
      printCmdSummary(); 
    }
    else{
      cout<<"ERROR:invalid command"<<endl;
      printCmdSummary();
    }

}

void insert(Table *&table){
  string name;
  int score;
  cin>>name;
  cin>>score;
  bool inserted;
  inserted = table->insert(name, score);
  if(inserted == false){
    cout<<name<<" is already in the table."<<endl;
  }
};

void change(Table *&table){
  string name;
  int score;
  cin>>name;
  cin>>score;
  int* value = table->lookup(name);
  if(value == NULL){
    cout<<name<<" is not in the table."<<endl;
  }
  else{
    *value = score;
  }
};

void printCmdSummary(){
  cout<<"-------------------------"<<endl;
  cout<<"     Command Summary"<<endl;
  cout<<endl;
  cout<<"- insert [name] [score]"<<endl;
  cout<<"- change [name] [score]"<<endl;
  cout<<"- remove [name]"<<endl;
  cout<<"- lookup [name]"<<endl;
  cout<<"- size"<<endl;
  cout<<"- stats"<<endl;
  cout<<"- help"<<endl;
  cout<<"- quit"<<endl;
  cout<<"-------------------------"<<endl;
};

void print(Table *&table){
  table->printAll();
};

void lookup(Table *&table){
  string name;
  cin>>name;
  int* value = table->lookup(name);
  if(value == NULL){
    cout<<name<<" is not in the table."<<endl;
  }
  else{
    cout<<name<<"'s score: "<<*value<<endl;
  }
};

void removeStudent(Table *&table){
  string name;
  cin>>name;
  bool removed = table->remove(name);
  if(removed == false){
    cout<<name<<" is not in the table."<<endl;
  }
};

