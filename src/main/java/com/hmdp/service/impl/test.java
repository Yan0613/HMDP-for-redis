package com.hmdp.service.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test {
    class Solution {
        public int[][] merge(int[][] intervals) {
            List<int[]> res = new LinkedList<>();
            //按照左边界排序
            Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));
            int start = intervals[0][0];
            int rightmostRightBound = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                //如果左边界大于最大右边界
                if (intervals[i][0] > rightmostRightBound) {
                    //加入区间 并且更新start
                    res.add(new int[]{start, rightmostRightBound});
                    start = intervals[i][0];
                    rightmostRightBound = intervals[i][1];
                } else {
                    //更新最大右边界
                    rightmostRightBound = Math.max(rightmostRightBound, intervals[i][1]);
                }
            }
            res.add(new int[]{start, rightmostRightBound});
            return res.toArray(new int[res.size()][]);
        }


        public int t2(int[][] grid) {
            //如果grid数组为空
            if(grid == null || grid.length == 0 || grid[0].length == 0){
                return 0;
            }
            int rows = grid.length,clumns = grid[0].length;
            int[][] dp = new int[rows][clumns];
            dp[0][0] = grid[0][0];
            //第一行
            for(int i=1;i<rows;i++){
                dp[i][0] = dp[i-1][0] + grid[i][0];
            }
            //在第一列时
            for(int j=1;j<clumns;j++){
                dp[0][j] = dp[0][j-1] + grid[0][j];
            }
            //非第一行第一列
            for(int i=1;i<rows;i++){
                for(int j=1;j<clumns;j++){
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
                }
            }
            return dp[rows-1][clumns-1];

        }


    }
}
