package com.hjb.demo.leetcode;

import java.util.Arrays;

/**
 * 给你k种面值的硬币，面值分别为c1, c2 ... ck，每种硬币的数量无限，
 * 再给一个总金额amount，问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1
 */
public class Main_8 {
    public static int coinChange(int[] coins, int amount){

        int[] arr = new int[amount +1];
        Arrays.fill(arr, amount + 1);
        arr[0] = 0;
        for(int i=1;i<amount +1;i++){
            for(int coin : coins){
                if(i>=coin) {
                    arr[i] = Math.min(arr[i], arr[i - coin] + 1);
                }
            }
        }
        return arr[amount] == amount +1 ?-1:arr[amount];
    }
}
