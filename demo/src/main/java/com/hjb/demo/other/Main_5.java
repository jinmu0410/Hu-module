package com.hjb.demo.other;


import java.util.Arrays;

/**
 * 动态规划
 * 求最值
 *给你k种面值的硬币，面值分别为c1, c2 ... ck，每种硬币的数量无限，
 * 再给一个总金额amount，问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1
 */
public class Main_5 {

  public static int coinChange(int[] coins, int amount){

        int[] dp = new int[amount +1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int i=1;i<amount +1;i++){
            for(int coin : coins){
               if(i>=coin) {
                   dp[i] = Math.min(dp[i], dp[i - coin] + 1);
               }
            }
        }
        return dp[amount] == amount +1 ?-1:dp[amount];
    }

    public static int ff(int n){

      int[] arr = new int[n+1];
      arr[1] = arr[2] =1;

      for(int i=3; i<=n;i++){

          arr[i] = arr[i-1] + arr[i-2];
      }
      return arr[n];
    }

    public static void main(String[] args) {
       int[] arr = {1,2,5};
       System.out.println(coinChange(arr,11));
       System.out.println(ff(5));
    }

}
