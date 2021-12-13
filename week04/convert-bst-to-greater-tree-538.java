/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    private int sum;// 遍历累加
    /**
     * 解法：中序遍历
     * 正常的中序遍历，先访问根，而输入的数据时按 层排列，输出也是按层排列
     * 1.希望更大的数做累加，就跟正常的中序遍历相反，所以要对中序遍历反正。
     */
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        // 中序遍历的反转
        dfs(node.right);
        node.val += sum;
        sum = node.val;
        dfs(node.left);
    }

}