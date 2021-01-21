package com.hjb.demo.leetcode;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main_7 {
    public static boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        String temp = str;
        String trs = "";
        if(str.charAt(0) == '-'){
           return false;
        }
        trs = trs + new StringBuffer(str).reverse().toString();
        return trs.equals(temp);
    }

    public static boolean isPalindrome_(int x){
        if(x<0){
            return false;
        }
        int[] arr = new int[23];
        int temp =x;
        int index=0;
        while (x>0){
            int y = x%10;
            arr[index]= y;
            x=x/10;
            index++;
        }
        int result = 0;
        try {
            for (int i = 1; i <= index; i++) {
                if (index - i >= 0) {
                    int y = new Double(Math.pow(10, i - 1)).intValue();
                    result = result + arr[index - i] * y;
                }

            }
        }catch (Exception e){
            return false;
        }
        System.out.println(result);
        return result==temp;
    }


    public static void main(String[] args) {
        System.out.println(isPalindrome_(121));
    }
}
