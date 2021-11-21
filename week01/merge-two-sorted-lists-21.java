/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 解法1：归并排序合并过程
        // 假头
        ListNode dummyHead = new ListNode(0);
        // cur串联合并后的链表
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                // 新链表选择 l1
                cur.next = l1;
                // 新链表移动
                cur = cur.next;
                // l1 移动
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }

        // 其中一条链表遍历空后，将另一条链表剩余元素追加到cur
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        // 假头指向的 才是真正的头部
        return dummyHead.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}