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

    class Node implements Comparable<Node> {
        int val;
        ListNode p;

        Node(int val, ListNode p) {
            this.val = val;
            this.p = p;
        }

        public int compareTo(Node newNode) {
            // 按大小排列
            return this.val - newNode.val;
        }
    }

    /**
     * 按最小堆的方式
     * 
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        // 利用系统的 最小堆
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>();
        for (ListNode node : lists) {
            if (node != null) {
                // 遍历添加到 最小堆，同时构建单向列表
                minHeap.offer(new Node(node.val, node));
            }
        }
        // 虚头
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (!minHeap.isEmpty()) {
            // 最小
            Node node = minHeap.poll();
            // 加入链表
            tail.next = new ListNode(node.val);
            // 移动链表
            tail = tail.next;
            if (node.p.next != null) {
                minHeap.offer(new Node(node.p.next.val, node.p.next));
            }
        }
        return dummy.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
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
     * 
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