package com.hjb.demo.leetcode;

import java.util.HashMap;


/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *  
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class Main_1 {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i <nums.length ; i++) {
            if(hashMap.containsKey(nums[i])){
                return new int[]{hashMap.get(nums[i]), i};
            }
            hashMap.put(target-nums[i],i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] a ={2,3,4,1,5};

        int[] aa = test(a,6);
        for (int i = 0; i < aa.length; i++) {
            System.out.println(aa[i]);
        }

    }

    public static int[] test(int[] num,int target){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < num.length; i++) {
                if(hashMap.containsKey(target- num[i])){
                    return new int[]{i,hashMap.get(target-num[i])};
            }
                hashMap.put(num[i],i);
        }
        return null;
    }
}
