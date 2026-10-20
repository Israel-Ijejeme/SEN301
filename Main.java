class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        ListNode left = partition(head, x);
        ListNode right = partition(slow, x);

        return merge(left, right, x);
    }

    private ListNode merge(ListNode left, ListNode right, int x) {
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);

        ListNode less = lessDummy;
        ListNode greater = greaterDummy;

        ListNode current = left;

        while (current != null) {
            ListNode next = current.next;
            current.next = null;

            if (current.val < x) {
                less.next = current;
                less = current;
            } else {
                greater.next = current;
                greater = current;
            }

            current = next;
        }

        current = right;

        while (current != null) {
            ListNode next = current.next;
            current.next = null;

            if (current.val < x) {
                less.next = current;
                less = current;
            } else {
                greater.next = current;
                greater = current;
            }

            current = next;
        }

        less.next = greaterDummy.next;

        return lessDummy.next;
    }
}