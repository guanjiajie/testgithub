package task;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    @Test
    public static int game(int[] guess, int[] answer){
        int count = 0;
        for (int i=0; i < 3; i++){
            if(guess[i] == answer[i]){
                count++;
            }
        }
        return count;
    }

    public static int maxIncreaseKeepingSkyline(int[][] grid){
        int[] maxLine = new int[grid[0].length];
        int[] maxColumn = new int[grid.length];
        int result = 0;
        for (int i = 0;i < grid.length;i++){
            for (int j = 0; j<grid[i].length;j++){
                if(grid[i][j]>maxLine[i]){
                    maxLine[i] = grid[i][j];
                }
                if(grid[i][j]>maxColumn[j]){
                    maxColumn[j] = grid[i][j];
                }
            }
        }
        for (int i = 0;i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                result += Math.min(maxLine[i],maxColumn[j]) - grid[i][j];
            }
        }
        return result;
    }

    static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        int index = maxIndex(nums);
        if(index == -1){
            return null;
        }

        TreeNode treeNode = new TreeNode(nums[index]);
        treeNode.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums,0,index));
        treeNode.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums,index+1,nums.length));

        return treeNode;
    }
    public static int maxIndex(int[] nums){
        List<Integer> list = new ArrayList(Arrays.asList(nums));
        int i = Arrays.stream(nums).max().getAsInt();
        System.out.println(i);
        for (int im: list){
            System.out.println(im);
        }
        int m = list.indexOf(i);
        return m;
    }

    public static void main1(String[] args){
        int[] guess = new int[]{1,2,3};
        int[] answer = new int[]{2,3,3};
        System.out.println(game(guess,answer));
        String shortUrl = "http://tinyurl.com/";
        System.out.println(shortUrl.replace(1+"http://tinyurl.com/", ""));
    }

    public static void main2(String[] args){
        int[][] grid = new int[][]{{3,0,8,4},{2,4,5,7},{9,2,6,3},{0,3,1,0}};
        int r = maxIncreaseKeepingSkyline(grid);
        System.out.println(r);
    }

    public static void main3(String[] args){
        int i = maxIndex(new int[]{3,2,1,6,0,5});
        System.out.println(i);
    }

    /**
     * 子集
     * @param nums
     * @return
     */
    @Test
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if (nums.length == 0 || nums == null){
              return res;
        }
        List list = new ArrayList();
        generate(nums,list,0,res);

        for (List temp:res){
            System.out.println(temp);
        }
        return res;
    }

    public static void generate(int[] nums,List list,int level,List res){
        if (level >= nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[level]); //选取
        generate(nums,list,level+1,res);
        list.remove(list.size()-1); //回溯
        generate(nums,list,level+1,res);

    }

    public static void main4(String[] args){
        subsets(new int[]{1,2,3});
    }

    /**
     * 删除链表中的节点
     * 4->5->1->9
     * 4->1->9
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void deleteNode(ListNode node,ListNode head) {
        ListNode pre = head;
        ListNode cur = head;
        while (true){
            if(cur == head && cur.val == node.val){
                head = head.next;
                break;
            }
            if (cur != null && cur.val == node.val ){
                pre.next = cur.next;
                break;
            }
            pre = cur;
            cur = cur.next;
        }

        ListNode temp = head;
        while (temp != null){
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    public static void main5(String[] args){
        ListNode head = new ListNode(4);
        head.next = new ListNode(5);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(9);
        deleteNode(new ListNode(9),head);
    }

    /**
     * 反转字符串
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     */
    public static void reverseString(char[] s) {
        int left = 0;
        int right = s.length-1;
        while (left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
        System.out.println(String.valueOf(s));
    }

    public static void main6(String[] args){
        reverseString(new char[]{'h','e','l','l','o'});
    }

    /**
     * 将有序数组转换为搜索二叉树
     * [-10,-3,0,5,9]
     *      0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        int m = nums.length/2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = generateBST(nums,0,m);
        root.right = generateBST(nums,m+1,nums.length-1);
        return root;
    }

    public static TreeNode generateBST(int[] nums,int start,int end){
        if (start == end) return null;
        int m = (start+end)/2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = generateBST(nums,start,m);
        root.right = generateBST(nums,m+1,end);
        return root;
    }

    public static void main7(String[] args){
        TreeNode treeNode = sortedArrayToBST(new int[]{-10,3,0,5,9});
    }

    /**
     * 反转链表
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;

            pre = cur;
            cur = temp;

        }
        return pre;
    }

    /**
     * Excel表序列号
     *     A -> 1
     *     B -> 2
     *     C -> 3
     *     ...
     *     Z -> 26
     *     AA -> 27
     *     AB -> 28
     *     ..
     *     ZY -> 701
     */
    @Test
    public  static int titleToNumber(String s) {
        int len = s.length();
        int result = 0;
        for (int i = 0; i < s.length(); i++){
            int c = s.charAt(i)-64;
            int r = (int)(Math.pow(26,len-1)*c);
            result += r;
            len--;
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(titleToNumber("ZY"));
    }

}
