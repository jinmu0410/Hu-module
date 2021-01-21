package com.hjb.demo.other;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/ceda1/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Main_3 {

    public static String longestCommonPrefix(String[] strs) {

        if(strs == null || strs.length == 0 ){
            return "";
        }
        String min = strs[0];

        for (int i = 1; i < strs.length; i++) {
            int j =0;
            for (; j <strs[i].length() ; j++) {
                if(min.charAt(j) != strs[i].charAt(j)){
                    break;
                }
            }
            min = min.substring(0,j);
        }
        return min;
    }

    public static void main(String[] args) {
        String[] ss = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(ss));
    }
}
