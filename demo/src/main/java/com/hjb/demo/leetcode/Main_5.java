package com.hjb.demo.leetcode;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main_5 {
    public static int reverse(int x) {
        int temp=x;
        String temp_result = "";
        try{
            if (x >=0){
                while (temp >0){
                    temp_result = temp_result + temp%10;
                    temp = temp/10;
                }
                return Integer.valueOf(temp_result);
            }else if(temp ==0){
                return 0;
            } else {
                temp = Math.abs(temp);
                while (temp>0){
                    temp_result = temp_result + temp%10;
                    temp = temp/10;
                }
                return Integer.valueOf("-"+temp_result);
            }
        }catch (Exception e){
            return 0;
        }
    }

    public static void main(String[] args) {

        System.out.println(reverse(123));
    }
}
