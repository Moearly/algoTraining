
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

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /**
     * 明确题意思：BST root不只是和左右节点比较，而是要和整颗左子树，右子树比较
     * 解题思路：递归。核心点，增加参数信息确定边界，约束比较判断
     * @return
     */
    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        // 1.base case 边界
        if (root == null)
            return true;
        // 2.处理当前层
        // 超出左边界
        if (min != null && root.val <= min.val)
            return false;
        // 超出右边界
        if (max != null && root.val >= max.val)
            return false;
        // 3.递归左右子树，缩小最大，最小范围
        return isValidBST(root.left, min, root)
                && isValidBST(root.right, root, max);
    }
}