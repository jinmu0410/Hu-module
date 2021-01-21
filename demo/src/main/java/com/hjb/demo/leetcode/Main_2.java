package com.hjb.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main_2 {

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        int num = 0;
        while (l1 != null || l2 != null || num !=0){
            int l1val = l1 != null ? l1.val:0;
            int l2val = l2 != null ? l2.val:0;

            int sum = l1val + l2val + num;
            num = sum/10;
            ListNode node = new ListNode(sum%10);
            tail.next = node;
            tail = node;
            if(l1 != null)
                l1= l1.next;
            if(l2 != null)
                l2=l2.next;
        }
        return head.next;
    }

    public static ListNode changeListNode(ListNode listNode){

        if(listNode == null || listNode.next == null){
            return listNode;
        }
        ListNode temp = changeListNode(listNode.next);
        listNode.next.next = listNode;
        listNode.next = null;
        return temp;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(8,new ListNode(3,new ListNode(5)));

        ListNode node1 = new ListNode(1,new ListNode(3,new ListNode(2)));
        System.out.println(test(node,node1));
    }

    public static ListNode test(ListNode l1 ,ListNode l2){
        ListNode temp = new ListNode(0);
        ListNode tail = temp;
        List<Integer> list = new ArrayList<>();
        int num = 0;
        while(l1 != null || l2 != null || num!=0){
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;

            int val = val1 + val2 + num;
            int x = val%10;
            num = val/10;
            list.add(x);
            ListNode listNode = new ListNode(x);
            tail.next = listNode;
            tail = listNode;
            if(l1 != null){
                l1=l1.next;
            }
            if(l2 != null){
                l2=l2.next;
            }
        }
        return temp.next;
    }
}
