package com.hjb.demo.other;

/**
 * 有两个数组，实现数组内容合并，如【1,3,5】，【6,4】，结果为【1,6,3,4,5】
 */
public class  Main_2 {

    public static int[] hebing(int[] a1, int[] a2){
        int length_1 = a1.length;
        int length_2 = a2.length;
        if(length_1 == 0 && length_2 == 0){
            return a1;
        }
        if(length_1 ==0 && length_2 >0){
            return a2;
        }
        if(length_1 >0 && length_2 ==0){
            return a1;
        }
        int[] aa = new int[length_1 + length_2];
        int ii =0;
        int jj =1;
        for (int i = 0; i < a1.length; i++) {
            aa[ii] = a1[i];
            ii = ii+2;
        }
        for (int i = 0; i < a2.length; i++) {
            aa[jj] = a2[i];
            if(jj>ii-2){
                jj=jj+1;
            }else {
                jj = jj + 2;
            }
        }
        return aa;
    }
    public static void main(String[] args) {
        int[] a ={1,3,5};
        int[] b={4,6,8,9};

        int[] aa = hebing(a,b);
        for (int i = 0; i <aa.length ; i++) {
            System.out.println(aa[i]);
        }
    }
}
