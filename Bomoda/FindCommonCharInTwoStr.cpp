/*
 * ref: http://levicui.blogspot.com/2012/02/how-to-find-common-characters-among-two.html
 */
#include <iostream>
#include <string>
#include <set>
#include <bitset>

using namespace std;

class FindCommonCharInTwoStr {
public:
	string comchr(string &a, string &b)
	{
		string r;
		set<char> s;    //s to remove duplicate chars
		bitset<256> t;    // All ASCII char
		if (a.length()+b.length()<=1)    return "";
		if (a.compare(b)==0)    return a;
		for (string::size_type i=0; i<a.length(); i++)
			if (!t.test(a[i]))
				t.set(a[i]);
		for (string::size_type i=0; i<b.length(); i++)
			if (t.test(b[i]))
				s.insert(b[i]);
		for (set<char>::iterator it=s.begin(); it!=s.end(); it++)
			r.push_back(char(*it));
		return r;
	}
};

	int main(int argc, char **argv) {
		if(argc != 3) {
			cout << "Usage: cmd string1 string2" << endl;
			return 1;
		}

		cout << "argc: " << argc << endl;
		cout << "program_name: " << argv[0] << endl;
		cout << "argv1: " << argv[1] << endl;
		cout << "argv2: " << argv[2] << endl;
		
		FindCommonCharInTwoStr *findComChr = new FindCommonCharInTwoStr();
		std::string str1(argv[1]);
		std::string str2(argv[2]);
		cout << str1 << " " << str2 << endl;
		cout << (*findComChr).comchr(str1, str2) << endl;
	}
