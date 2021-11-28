class LRUCache {
    // 容量
    private int cap;
    // 头尾虚节点
    private Node head, tail;
    private HashMap<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.cap = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        // map 查找key
        Node node = map.get(key);
        // 删除之前位置
        remove(node);
        // 插入头
        add(head, node);
        return node.val;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            // 创建node加入
            Node node = new Node();
            node.key = key;
            node.val = value;
            // 插入hashmap
            map.put(key, node);
            // 插入头部
            add(head, node);
            if (map.size() > cap) {
                // hashmap删除
                map.remove(tail.pre.key);
                // 删除队尾
                remove(tail.pre);
            }
        } else {
            // 更新节点
            Node node = map.get(key);
            node.val = value;
            // 删除之前位置
            remove(node);
            // 插入头
            add(head, node);
        }
    }

    /**
     * 在 p 后面 添加 x
     */
    private void add(Node p, Node x) {
        // 在 p 和 p.next 直接 插入 x，每句话都需要 x 参与(先处理 p.next )
        p.next.pre = x;
        x.next = p.next;
        // 上面2句话，x 与 p.next 2条链建立
        p.next = x;
        x.pre = p;
        // 建立 p 和 x 的前后关系
    }

    private void remove(Node x) {
        // 跳过 x 让 x.pre 和 x.next 建立联系
        x.pre.next = x.next;
        x.next.pre = x.pre;
    }

    private class Node {
        public int key, val;
        // 双指针
        public Node pre, next;

        public Node() {
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */