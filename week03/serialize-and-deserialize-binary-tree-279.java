
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    String SEP = ",";
    String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder b = new StringBuilder();
        serialize(root, b);
        return b.toString();
    }

    private void serialize(TreeNode root, StringBuilder b) {
        if (root == null) {
            b.append(NULL).append(SEP);
            return;
        }
        // 前序遍历
        b.append(root.val).append(SEP);
        serialize(root.left, b);
        serialize(root.right, b);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) {
            nodes.add(s);
        }
        return deserialize(nodes);
    }

    private TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty())
            return null;
        // 最左边就是根
        String first = nodes.removeFirst();
        if (first.equals(NULL))
            return null;
        // 构建根
        TreeNode node = new TreeNode(Integer.parseInt(first));
        // 递归左右
        node.left = deserialize(nodes);
        node.right = deserialize(nodes);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));