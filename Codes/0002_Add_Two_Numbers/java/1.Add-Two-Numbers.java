/*
insertion code:
	if(head == null) {
		current = new ListNode(val);
		head = current;//when adding first node to the LinkedList, it should point to head
	}

	current = head;
	while(current.next != null){
		current = current.next;//current will move on, we donot move head
	}
		
	current.next = new ListNode(val);
	
So very first element insertion is outside loop. we try the same in following code instead of 
checking the head == null each time, we define a dummy node outside so head point to it and 
we use 'current' variable for traversing Linkedlist.

carry:
max: 9 + 9 => 18 / 10 = 1.8 => 1

carry is defined in while loop's condition is because what if both linkedlist are null still the 
carry of last sum is 1. so we must add that element too.

sum:
09 % 10 => 9
18 % 10 => 8
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);//dummy node so that we donot have to use if head == null in loop
		ListNode current = head;
        int carry = 0;
		
        //use while when you dont know how many times the loop will run
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + carry;
            
            carry = sum / 10;
            
            current.next = new ListNode(sum % 10);
            current = current.next;
            
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        
        return head.next;
    }
}