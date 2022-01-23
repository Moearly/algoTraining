class Solution {

    // 线段树
    public List<Integer> fallingSquares(int[][] positions) {
        // 创建返回值
        List<Integer> res = new ArrayList<>();
        Node root = null;
        // 目前最高的高度
        int maxH = 0;

        for (int[] position : positions) {
            int l = position[0]; // 左横坐标
            int r = position[0] + position[1]; // 右横坐标
            int e = position[1]; // 边长
            int curH = query(root, l, r); // 目前区间的最高的高度
            root = insert(root, l, r, curH + e);
            maxH = Math.max(maxH, curH + e);
            res.add(maxH);
        }
        return res;
    }

    private Node insert(Node root, int l, int r, int h) {
        if (root == null)
            return new Node(l, r, h);
        if (l <= root.l)
            root.left = insert(root.left, l, r, h);
        else
            root.right = insert(root.right, l, r, h);
        return root;
    }

    private int query(Node root, int l, int r) {
        if (root == null)
            return 0;
        // 高度
        int curH = 0;
        // 是否跟这个节点相交
        if (!(r <= root.l || root.r <= l))
            curH = root.h;
        curH = Math.max(curH, query(root.left, l, r));
        curH = Math.max(curH, query(root.right, l, r));
        return curH;
    }

    // 描述方块以及高度
    private class Node {
        int l, r, h;
        Node left, right;

        public Node(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
            this.left = null;
            this.right = null;
        }
    }

}