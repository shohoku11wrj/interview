/*
 * Whether a SingleList has a loop or not?
 * Refer: http://ostermiller.org/find_loop_singly_linked_list.html
 */
#include <iostream>

class Node {
	public:
		int data;
		Node *next;
		Node() {
			next = 0;	
		}
		Node(const int& a, Node *p = 0) {
			data = a;
			next = p;
		}
};

// O(n) -- up to 3 traversals of the list
bool hasLoop(Node &startNode) {
	Node *currentNode;
	currentNode = &startNode;
	Node *checkNode;
	int since = 0;
	int sinceScale = 2;
	do {
		std::cout << "cur: " << currentNode->data << " chk " << checkNode->data << std::endl;
//		std::cout << "cur.next: " << (currentNode->next)->data << " chk ";
//		std::cout << (checkNode->next==0 ? 0:(checkNode->next)->data) << std::endl;

		if(checkNode == currentNode) {
			return true;
		}
		if(since >= sinceScale) {
			checkNode = currentNode;
			since = 0;
			sinceScale = 2*sinceScale;
		}
		since++;
	} while (currentNode = currentNode->next);
	return false;
}

int main() {
	Node *node1 = new Node(1);
	Node *node2 = new Node(2);
	Node *node3 = new Node(3);
	Node *node4 = new Node(4);
	Node *node5 = new Node(5);
	(*node1).next = node2;
	(*node2).next = node3;
	(*node3).next = node4;
	(*node4).next = node5;
	(*node5).next = node3;
	if(hasLoop(*node1)) {
		std::cout << "has loop" << std::endl;
	}
	else {
		std::cout << "has no loop" << std::endl;
	}
}
