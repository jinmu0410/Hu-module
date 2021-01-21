package com.hjb.demo.leetcode;

import java.util.*;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main_3 {

    public static int lengthOfLongestSubstring(String s) {
        int start = 0;
        int max=0;
        HashMap<Character,Integer> hashMap = new HashMap<>();
        for(int end=0;end<s.length();end++){
            if(hashMap.containsKey(s.charAt(end))){
                start=Math.max(start,hashMap.get(s.charAt(end)) +1);
            }
            hashMap.put(s.charAt(end),end);

            max=Math.max(max,end-start+1);
        }
        return max;
    }

    public static int test(String s) {
       if(s.length() == 0){
           return 0;
       }
       int start = 0;
       int max = 0;
       Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i <s.length(); i++) {
            if(map.containsKey(s.charAt(i))){
                start = Math.max(start,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max=Math.max(i-start +1,max);
       }
        return max;
    }

    public static void main(String[] args) {
        String ss="pwwkew";
        System.out.println(lengthOfLongestSubstring(ss));
        System.out.println(test(ss));
    }

}
