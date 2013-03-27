#include <iostream>
using namespace std;

typedef struct sortNode
{
	int number;
	int frequency;
}sortNode;

int main()
{
	// this is a simple array, do not contain 0 in testing
	int array[] = {4,3,2,2,4,1,5,6,9,9,8,3,1,6,7,3,1,4,5,5,6,7,2,3,1,2,1};

	int size = sizeof(array) / sizeof *(array);
	cout << "Original array is: " << endl;
	for(int i=0; i<size; i++ ) {
		cout << array[i] << " ";
	}
	cout << endl;

	sortNode *sort = new sortNode[size];
	
	for(int i=0; i<size; i++) {
		int j=0;
		bool hasFlag = false; // show whether the sort list already has counted the frequency of array[i]
		while(sort[j].number != 0) {
			if(sort[j].number == array[i]) {
				hasFlag = true;
				sort[j].frequency++;
				break;
			}
			j++;
		}
		if(!hasFlag) {
			sort[j].number = array[i];
			sort[j].frequency = 1;
		}
	}

	int sortSize=0;
	for(int i=0; i<size; i++) {
		if(sort[i].frequency != 0) {
			sortSize++;
		}
		else {
			break;
		}
	}

	// bubsort
	sortNode temp;
	int i,j,tag=1;
	for(i=0; i<sortSize; i++) {
		if(tag==0) {
			break;
		}
		tag=0;
		for(j = sortSize-1; j>i; j--) {
			if(sort[j].frequency > sort[j-1].frequency) {
				temp = sort[j-1];
				sort[j-1] = sort[j];
				sort[j] = temp;
				tag=1;
			}
		}
	}
	
	cout << "Frequency sort is: number(frequency)" << endl;
	for(int i=0; i<sortSize; i++ ) {
		cout << sort[i].number << "(" << sort[i].frequency << ") ";
	}
	cout << endl;
	
	cout << endl;
}






