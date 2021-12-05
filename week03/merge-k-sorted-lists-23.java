/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        // 解法1：逐一合并两条链表, 时间复杂度 O(n*k)，k链表数组的长度
        ListNode res = null;
        // for(ListNode node: lists) {
        // res = merge2Lists(res, node);
        // }

        // 解法2：两两合并 - 递归 时间复杂度 O(NlogK)
        res = merge(lists, 0, lists.length - 1);
        return res;
    }
    /**
     * 实际就是归并排序，被排序节点自己有序，排序外层即可
     * @param lists
     * @param start
     * @param end
     * @return
     */
    public ListNode merge(ListNode[] lists, int start, int end) {
        // 递归终止条件
        if (start == end) {
            return lists[start];
        }
        // 本层，找中点，避免溢出
        int mid = start + (end - start) / 2;
        // 递
        ListNode startNode = merge(lists, start, mid);
        ListNode endNode = merge(lists, mid + 1, end); // 注意不要重复
        // 归，合并2个
        return merge2Lists(startNode, endNode);
    }

    public ListNode merge2Lists(ListNode l1, ListNode l2) {
        // 记录头方便返回
        ListNode dummyHead = new ListNode(0);
        // cur串联合并后的链表
        ListNode cur = dummyHead;
        // 循环，取小的
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                // 追加到合并的链
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;

        }
        if (l1 == null) {
            // 整个尾部追加到cur
            cur.next = l2;
        } else {
            // 整个尾部追加到cur
            cur.next = l1;
        }
        return dummyHead.next;
    }
}