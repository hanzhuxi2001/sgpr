package com.bootstrapwithspringboot.webapp.code.algorithm;

import java.util.List;

public class Solution {
    int n=0;
    public List<String> generateParenthesis(int n){
        this.n = n;
        generate(0,0,"");
        return null;
    }

    private void generate2(int i, int max, String s) {
        //terminator
        if(i>=max) {
            System.out.println(s);
            return;
        }
        //process
        String s1 = s + "(";
        String s2 = s + ")";

        //drill down
        generate2(i+1,max,s1);
        generate2(i+1,max,s2);

        //reverse states
    }

    private void generate(int left, int right,String s) {
        //terminator
        if(left == n&& right == n) {
            System.out.println(s);
            return;
        }
        //process
        //drill down
        if(left<n)
            generate(left+1,right,s + "(");
        if(left>right)
            generate(left,right+1,s + ")");
        //reverse states
    }

    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.generateParenthesis(3));
    }
}
