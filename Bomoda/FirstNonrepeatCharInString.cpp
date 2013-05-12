#include <iostream>
#include <unordered_map>
#include <queue>
#define NUM_OF_CHARS 256
using namespace std;

/* Returns an array of size 256 containg count
   of characters in the passed char array */
int *getCharCountArray(char *str)
{
	int count[NUM_OF_CHARS] = {};
	int i;
	for (i = 0; *(str+i);  i++)
		count[*(str+i)]++;
	return count;
}

/* The function returns index of first non-repeating
   character in a string. If all characters are repeating 
   then reurns -1 */
int firstNonRepeating(char *str)
{
	int *count = getCharCountArray(str);
	int index = -1, i;

	for (i = 0; *(str+i); i++)
	{
		if(count[*(str+i)] == 1)
		{
			index = i;
			break;
		}   
	}  
	return index;
}

/******************************
  Less time complexity and space complexity
 ******************************/
char firstNonRepeating_adv(char *s)
{
	char *strChars = s;
	unordered_map<char, int> *charMap = new unordered_map<char, int>();
	queue<char> strQueue;// = new LinkedList<char>();
	for(int i = 0; i < sizeof(strChars)/sizeof(char); i++)
	{
		if((*charMap).count(strChars[i]))
		{
			(*charMap).at(strChars[i]) = (*charMap).at(strChars[i]) + 1;
		}
		else
		{
			(*charMap).insert(pair<char,int>(strChars[i], 1));
			strQueue.push(strChars[i]);
		}
	}
	while(!strQueue.empty())
	{
		char firstUnique = strQueue.front();
		strQueue.pop();
		if((*charMap).at(firstUnique) == 1)
		{
			return firstUnique;
		}
	}
	return 0;
}

int main(int argc, char** argv) 
{
	char str[] = "total";
	int index =  firstNonRepeating(str);
	cout << "========== Normal algorithm ======== " << endl;
	if(index == -1)  
		cout << "Either all characters are repeating or string is empty" << endl;
	else
		cout << "First non-repeating character in '" << str << "' is: " << str[index] <<endl;
	cout << "========== Adv algorithm ===========" << endl;
	cout << "Adv algorithm answer is: " <<firstNonRepeating_adv(str) << endl;
	return 0;
}
