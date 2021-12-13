class Solution {
    /**
    * 
    * 方案1：递归实现
    * 找到目标点：根据目标点结构特点 删除
    * 1. 目标节点没有左右子树，直接删除
    * 2. 目标节点只有左子树或只有右子树，原来指向目标节点的指针指向其左子树或右子树
    * 3. 目标节点有左右子树：2种方案
    */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        //1.找到 删除点
        if (root.val == key) {
            return newTree(root);
        }
        //向左 更小
        if (root.val > key) {
            TreeNode newTree = deleteNode(root.left, key);
            root.left = newTree;
        }
        //向右 更大
        if (root.val < key) {
            TreeNode newTree = deleteNode(root.right, key);
            root.right = newTree;
        }
        return root;
    }

    /**
    * 1. 目标节点没有左右子树，直接删除
    * 2. 目标节点只有左子树或只有右子树，原来指向目标节点的指针指向其左子树或右子树
    * 3. 目标节点有左右子树:
    * 方案一：找到左子树的最右节点，让原来指向这个左子树最右节点的指针指向其左子树，然后用这个左子树最右节点替换掉目标节点
    * 方案二：找到右子树的最左节点，让原来指向这个右子树最左节点的指向指向其右子树，然后用这个右子树最左节点替换掉目标节点
     * @param root
     * @return
     */
    private TreeNode newTree(TreeNode root) {
        if (root.left == null && root.right == null) {
            return null;
        }
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        
        TreeNode pre = root, cur = root.right;
        while (cur.left != null) {
            pre = cur;
            cur = cur.left;
        }
        if (cur != root.right) {
            pre.left = cur.right;
            cur.left = root.left;
            cur.right = root.right;
        } else {
            cur.left = root.left;
        }
        return cur;
    }
}