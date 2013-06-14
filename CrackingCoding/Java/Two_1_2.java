import java.util.Hashtable;


public class Two_1_2 {

	static class Node {
		Node next = null;
		int data;
		
		public Node(int d) {
			data = d;
		}
		
		public Node(Node n) {
			data = n.data;
			next = n.next;
		}
		
		void appendToTail(int d) {
			Node end = new Node(d);
			Node n = this;
			while(n.next != null) {
				n = n.next;
			}
			n.next = end;
		}
		
		public Node deleteNode(Node head, int d) {
			Node n = head;
			
			if(n.data == d) {
				return head.next; // moved head
			}
			
			while(n.next != null) {
				if(n.next.data == d) {
					n.next = n.next.next;
					return head; // head didn;t change
				}
				n = n.next;
			}
			return head;
		}
	}
	
	public Node createLinkedList(int[] array){
		if(array.length == 0) {
			return null;
		} else {
			Node head = new Node(array[0]);
			Node cur = head;
			for(int i = 1; i < array.length; i++) {
				cur.next = new Node(array[i]);
				cur = cur.next;
			}
			return head;
		}
	}
	
	public static void printLinkedList(Node head) {
		if(head == null) {
			System.out.println("Null");
		} else {
			Node cur = head;
			while(cur != null) {
				System.out.print(cur.data);
				if(cur.next != null)
					System.out.print( " -> ");
				cur = cur.next;
			}
			System.out.println();
		}
	}
	
/************************************** Question 1 *************************************/
	
	// O(N) time
	public static void deleteDups(Node n) {
		Hashtable table = new Hashtable();
		Node previous = null;
		while(n != null) {
			if(table.containsKey(n.data)) {
				previous.next = n.next;
			} else {
				table.put(n.data, true);
				previous = n;
			}
			n = n.next;
		}
	}
	
	// O(1) space, O(N^2) time
	public static void deleteDups2(Node n) {
		if(n == null)
			return;
		Node p = n;
		while(p.next != null) {
			Node q = p;
			while(q.next != null) {
				if(q.next.data == p.data) {
					q.next = (q.next.next != null? q.next.next : null);
				} else { // !! Must have else here because in if case, the next is deletedd, so no necessary to [q = q.next] again after if case
					q = q.next;
				}
			}
			p = p.next;
		}
	}
	
/*****************************************************************************************/
	
/************************************** Question 2 ***************************************/
	// get the kth to last element of singly linked list
	public static Node lastKth(Node head, int k) {
		if(head == null)
			return null;
		Node p = head, q = head;
		for(int i=1; i<k; i++) {
			p = p.next;
		}
		while(p.next != null) {
			p = p.next;
			q = q.next;
		}
		return q;
	}
	
	// recursive, use counter; whien it hits the end, the mothod passes back a counter set to 0;
	// Each parent call adds 1 to this counter;
	// *But, cannot pass back a node and a counter using normal return statements;
	public static int kthToLast(Node head, int k) {
		if(head == null)
			return 0;
		int i = kthToLast(head.next, k) + 1;
		if(i == k) {
			System.out.println(head.data);
		}
		return i;
	}
	
	public static class IntWrapper {
		public int value = 0;
	}
	
	public static Node kthToLastR2(Node head, int k, IntWrapper i) {
		if(head == null) {
			return null;
		}
		Node node = kthToLastR2(head.next, k, i);
		i.value = i.value + 1;
		if(i.value == k) { // We've found the kth element
			return head;
		}
		return node;
	}
	
/*****************************************************************************************/
	
/************************************** Question 3 ***************************************/
	
	public static void deleteMid(Node head) {
		if(head == null)
			return;
		if(head.next == null)
			return;
		if(head.next.next == null)
			return;
		Node p = head.next.next, q = head;
		while(p.next != null) {
			p = p.next.next;
			q = q.next;
		}
		q.next = q.next.next;
	}

/*****************************************************************************************/
	
/************************************** Question 4 ***************************************/
	
	public static Node partitionLinkedList(Node head, int x) {
		if(head == null)
			return head;

		Node p = new Node(0);
		Node q = null;
		p.next = head;
		head = p;
		p = head.next;
		Node tmp = null;
		while(p.next != null) {
			if(p.next.data < x) {
				tmp = p.next;
				//if(p.next.next != null)
					p.next = p.next.next;
				//else
				//	p.next = null;
				tmp.next = head.next;
				head.next = tmp;
			}
			if(p.next != null) {
				q = p;
				p = p.next;
			}
			else {
				break;
			}
		}
		if(p.data < x) {
			q.next = null;
			p.next = head.next;
			head.next = p;
		}
		head = head.next;
		return head;
		
	}
	
/*****************************************************************************************/	
	
/************************************** Question 5 ***************************************/
	
	 // my answer
	 public static Node addOperation(Node left, Node right) {
		 Node result = new Node(0), next = result;
		 int leftNum = 0, rightNum = 0, resultNum = 0;
		 int i = 1;
		 while(left != null) {
			 leftNum += (int) left.data * i;
			 i *= 10;
			 left = left.next;
		 }
		 i = 1;
		 while(right != null) {
			 rightNum += (int) right.data * i;
			 i *= 10;
			 right = right.next;
		 }
		 
		 resultNum = leftNum + rightNum;
		 i = 1;
		 if(resultNum > 0) {
			 while(resultNum / i != 0 ) {
				 next.next = new Node(0);
				 next = next.next;
				 next.data = resultNum % (i*10) / i;
				 resultNum = resultNum - resultNum % (i*10);
				 i *= 10;
			 }
			 result = result.next;
			 return result;
		 }
		 return null;
	 }
	 
	 // my solution
	 public static Node addOperation2(Node left, Node right) {
		 Node result = new Node(0), cur = result;
		 int carrier = 0; // JIN WEI
		 while(left != null || right != null || carrier != 0) {
			 int temp = (left == null? 0 : left.data) + (right == null? 0 : right.data) + carrier;
			 carrier = temp / 10;
			 cur.data = temp % 10;
			 
			 if(left != null) {
				 if(left.next != null) {
					 left = left.next;
				 }
				 else {
					 left = null;
				 }
			 }
			 if(right != null) {
				 if(right.next != null) {
					 right = right.next;
				 } else {
					 right = null;
				 }
			 }
			 if (left != null || right != null || carrier != 0) {
				 cur.next = new Node(0);
				 cur = cur.next;
			 }
		 }
		 return result;
	 }
	 
	 // solution, recursive, for the 2nd question: Follow up 
	 public static Node addLists(Node l1, Node l2, int carry) {
		 
		 return null;
	 }

/*****************************************************************************************/	
	 
/************************************** Question 6 ***************************************/
	 
	 public static Node detectLoop(Node head) {
		 Node cur = head;
		 Node chk = new Node(0); // must be initialized
		 int since = 0;
		 int sinceScale = 2;
		 do {
			 System.out.println("cur: " + cur.data + " , chk: " + chk.data);
			 if(chk == cur) {
				 chk = head;
				 while(true) {
					 cur = cur.next;
					 chk = chk.next;
					 if(cur == chk) {
						 return cur;
					 }
				 }
			 }
			 if(since >= sinceScale) {
				 chk = cur;
				 since = 0;
				 sinceScale = 2 * sinceScale;
			 }
			 since++;
			 cur = cur.next;
		 } while(cur.next != null);
		 return null;
	 }
	 
	 // solution, fast & slow pointer + k steps from head as well as from meet position
	 public static Node FindBeginLoop(Node head) {
		 Node slow = head;
		 Node fast = head;
		 
		 /* Find meeting point. This will be LOOP_SIZE -k steps into the
		  * linked list.
		  */
		 while (fast != null && fast.next != null) {
			 slow = slow.next;
			 fast = fast.next.next;
			 if(slow == fast) {// Collision
				 break;
			 }
		 }
		 
		 /* Erroe check - no meeting point, and therefore no loop */
		 if (fast == null || fast.next == null) {
			 return null;
		 }
		 
		 /* Move slow to Head. Keep fast at Meeting Point. Each are k
		  * steps from the Loop Start. If they move at the same pace,
		  * they must meet at Loop Start.
		  */
		 slow = head;
		 while (slow != fast) {
			 slow = slow.next;
			 fast = fast.next;
		 }
		 
		 // Both now point to the start of the loop
		 return fast;
	 }
	 
/*****************************************************************************************/	
	 
/************************************** Question 7 ***************************************/
	 
	 public static boolean isPalindrome(Node head) {
		 if (head.next == null) {
			 return true;
		 }
		 if(head.next.next == null) {
			 if(head.next.data == head.data) {
				 return true;
			 }
			 else {
				 return false;
			 }
		 }/*
		 Node pre = head;
		 Node reverse = pre.next;
		 Node next = reverse.next;
		 while(next) {
			 reverse.next = pre;
			 pre = next.next;
			 
			 if(next != null) {
				 
			 }
		 }*/
		 
		 Node reverse = reverseList(head);
		 
		 // test whether equal
		 while(head.next != null) {
			 if (head.data != reverse.data) {
				 return false;
			 }
			 head = head.next;
			 reverse = reverse.next;
		 }
		
		 
		 return true;
	 }
	 
	 public static Node reverseList(Node head) {
		 if(head.next == null) {
			 return new Node(head);
		 }
		 Node reverse = new Node(head);
		 Node p = null;
		 if(reverse.next != null) {
			 p = new Node(reverse.next);
			 reverse.next = null;
		 }
		 Node q = null;
		 if(p.next != null) {
			 q= new Node(p.next);
		 }
		 while(p.next != null) {
			 p.next = reverse;
			 reverse = p;
			 p = q;
			 if(p.next != null) {
				 q = new Node(p.next);
			 } else {
				 break;
			 }
		 }
		 p.next = reverse;
		 reverse = p;
		 
		 System.out.print("reversed list: ");
		 printLinkedList(reverse);
		 
		 return reverse;
	 }

/*****************************************************************************************/	

	public static void main(String[] args) {
		Two_1_2 instance = new Two_1_2(); 
		Node head = instance.createLinkedList(new int[]{1,1});
		
		// printLinkedList(head);
		/*
		deleteDups2(head);
		printLinkedList(head);
		*/
		/*
		if (lastKth(head,1) != null) {
			System.out.println(lastKth(head,1).data);
		}
		kthToLast(head,2);
		System.out.println(kthToLastR2(head, 4, new IntWrapper()).data);
		*/
		//deleteMid(head);
		/*
		 * head = partitionLinkedList(head,3);
		 * printLinkedList(head);
		 */
		/*
		Node left = instance.createLinkedList(new int[]{7,1,6});
		Node right = instance.createLinkedList(new int[]{5,9,5});
		Node result = addOperation2(left,right);x
		printLinkedList(result);
		*/
		/*
		head.next.next.next.next.next.next.next.next.next = head.next.next.next;
		Node temp = detectLoop(head);
		if (temp == null )
			System.out.println("No loop");
		else
			System.out.println(temp.data);
		*/
		System.out.println(isPalindrome(head));
		System.out.print("original list: ");
		printLinkedList(head);
		
	}

}
