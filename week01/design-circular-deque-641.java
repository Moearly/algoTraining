class MyCircularDeque {
    private final int[] elements;
    // 容量初始化为k+1(因为tail不是存放真是数据)
    private int size = 0;
    // head 指向数组中第一个数据位置
    // tail 指向数组中下一个可存储的空位置
    private int head = 0, tail = 0;

    public MyCircularDeque(int k) {
        size = k + 1;
        elements = new int[size];
    }

    public boolean insertFront(int value) {
        if (isFull())
            return false;
        // 前插，head向左，扩大
        head = (head - 1 + size) % size;
        elements[head] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull())
            return false;
        // 尾插，head向右，扩大
        elements[tail] = value;
        // 从新计算下一个位置，注意tail不存储数据
        tail = (tail + 1 + size) % size;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty())
            return false;
        // 右移，缩小elements数据范围
        head = (head + 1 + size) % size;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty())
            return false;
        // 左移，缩小elements数据范围
        tail = (tail - 1 + size) % size;
        return true;
    }

    public int getFront() {
        if (isEmpty())
            return -1;
        return elements[head];
    }

    public int getRear() {
        if (isEmpty())
            return -1;
        // tail上一个位置，存放实际的队尾
        return elements[(tail - 1 + size) % size];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        // tail再后移一位(右移)就碰到了front，因为是循环存储的
        return (tail + 1) % size == head;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k); boolean param_1 =
 * obj.insertFront(value); boolean param_2 = obj.insertLast(value); boolean
 * param_3 = obj.deleteFront(); boolean param_4 = obj.deleteLast(); int param_5
 * = obj.getFront(); int param_6 = obj.getRear(); boolean param_7 =
 * obj.isEmpty(); boolean param_8 = obj.isFull();
 */