class Node {
	Node next = null;
	Object data;

	public Node(Object d) {
		data = d;
	}

	public Node(Node n) {
		data = n.data;
		next = n.next;
	}

	void appendToTail(Object d) {
		Node end = new Node(d);
		Node n = this;
		while (n.next != null) {
			n = n.next;
		}
		n.next = end;
	}

	public Node deleteNode(Node head, Object d) {
		Node n = head;

		if (n.data == d) {
			return head.next; // moved head
		}

		while (n.next != null) {
			if (n.next.data == d) {
				n.next = n.next.next;
				return head; // head didn;t change
			}
			n = n.next;
		}
		return head;
	}
}
