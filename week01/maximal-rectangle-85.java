class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int n = matrix[0].length; //宽度
        int m = matrix.length;  //高度
        int res = 0;
        int[] heights = new int[n]; //每一层作为底，当作一组heights，求组成柱子的，最大矩形

        for(int i = 0; i < m; i++) { //外层循环高度
            for(int j = 0; j < n; j++) { //内层是宽度：一层
                if(matrix[i][j] == '1'){
                    heights[j] += 1; //累加上层
                } else {
                    heights[j] = 0;
                }
            }
            res = Math.max(res, largestRectangleArea(heights));
        }
        return res;
    }

    public int largestRectangleArea(int[] heights) {
        //构建2边都是 0 的新数组
        int[] newHeight = new int[heights.length + 2];
        newHeight[0] = 0;
        newHeight[heights.length + 1] = 0;
        //单调栈，存放下标
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(int i = 1; i <= heights.length; i++){
            newHeight[i] = heights[i - 1];
        }

        //遍历新数组
        for(int i = 0; i < newHeight.length; i++) {
            //满足出栈，考察元素比栈顶元素小，那个栈顶元素就可以，计算面积
            while(!stack.isEmpty() && newHeight[stack.peek()] > newHeight[i]) {    
                int cur = stack.pop();
                int curHeight = newHeight[cur];
                //计算宽度
                int leftIndex = stack.peek();
                int rightIndex = i;//当前考察元素
                int curWidth = rightIndex - leftIndex - 1;
                res = Math.max(res, curHeight * curWidth); 
            }
            stack.push(i);
        }
        return res; 
    }
}
