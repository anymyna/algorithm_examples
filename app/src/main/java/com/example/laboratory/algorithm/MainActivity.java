package com.example.laboratory.algorithm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private String TAG = "duck";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //int value·[] = new int[2];
        //value.length
        int max = Integer.MIN_VALUE;

        //.out.println("->->"+(josephus(41,3)+1));
        //System.out.println("->->"+(josephus3(41,3)));

        convert("PAYPALISHIRING",3);

        char s = 'c';


//        TextUtils.isEmpty()
//        Character.isLowerCase(s);
//        Character.isDigit()
//        Character.isAlphabetic()
//        Character.toLowerCase();
//        Character.toUpperCase()
//


                String t= "";
        t.toLowerCase();
        t.length();
        t = t.substring(0,t.length()-1);
//        t.contains()



//        t.equals()
        //49. 两个数组的交集
//        int[] nums1 = new int[3];
//        nums1[0] = 4;
//        nums1[1] =9;
//        nums1[2] = 5;
//        int[] nums2 = new int[5];
//        nums2[0] = 9;
//        nums2[1] =4;
//        nums2[2] = 9;
//        nums2[3] = 8;
//        nums2[4] = 4;
//        int arr [] = intersection(nums1,nums2);
//        for(int i= 0 ;i<arr.length;i++)
//        {
//            Log.e(Tag,""+arr[i]);
//        }

        int arr [][] = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};//20
        //int arr [][] = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};//15
//        int arr [][] = {{1,4},{2,5}};//2
        //Log.e(TAG," "+searchMatrix(arr,20));


        Vector<String> vector = new Vector<>();
        vector.add("a");
        vector.add("b");
        vector.add("c");
        vector.add("f");
        vector.add("e");
        vector.add("f");
        vector.add("g");
        vector.add("h");
        Log.e(TAG," vector before "+vector.toString());
         reverse(vector,3);
        Log.e(TAG," vector after"+vector.toString());

    }

    //编程珠玑：向量旋转
    private  void reverse( Vector<String> vector ,int position)
    {
        reverse(vector,0,position-1);
        reverse(vector,position,vector.size()-1);
        reverse(vector,0,vector.size()-1);
    }

    private  void reverse( Vector<String> vector,int start ,int end)
    {
        while(start < end)
        {
            String temp = vector.elementAt(start);
            vector.set(start,vector.elementAt(end));
            vector.set(end,temp);
            start++;
            end--;
        }
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        if(matrix.length == 0)
        {
            return false;
        }
        if(matrix[0].length == 0)
        {
            return false;
        }



        return searchMatrix(matrix,target,matrix.length-1,matrix[0].length-1);
    }


    private boolean searchMatrix(int[][] matrix, int target,int i,int j){

        if(matrix[i][j] < target)
        {
            return false;
        }

        if(matrix[0][0] > target)
        {
            return false;
        }

        if(i == 0)
        {
            for(int l=0;l<matrix[0].length;l++)
            {
                if(matrix[0][l] == target)
                {
                    return true;
                }
            }
            return false;
        }

        if(j == 0)
        {
            for(int l=0;l<matrix.length;l++)
            {
                if(matrix[l][0] == target)
                {
                    return true;
                }
            }
            return false;
        }


        if(j < 2 || i < 2)
        {
            for(int l=0;l<i+1;l++)
            {
                for(int m=0;m<j+1;m++)
                {
                    if(matrix[l][m] == target)
                    {
                        return true;
                    }
                }
            }
            return false;
        }


        int row = 0;

        int k = 0;
        while (matrix[i][j] > target && i - row > 1)
        {
            k = i;
            int base = (i - row) / 2;
            i = row + base;

            if(matrix[i][j] < target && k - row > 1)
            {
                row = i;
                i = k;
            }
            else if(matrix[i][j] == target)
            {
                return true;
            }

            if(matrix[row][j] < target && matrix[row+1][j]>target)
            {
                break;
            }

        }

        for(int l=0;l<matrix[0].length-1;l++)
        {
            if(matrix[row+1][l] == target)
            {
                return true;
            }
        }


        return false;
    }


    //189. 旋转数组
    public void rotate(int[] nums, int k) {

        for(int i=0;i<k;i++)
        {
            rotate(nums);
        }
    }

    private void rotate(int[] nums)
    {
        if(nums.length == 0 || nums.length == 1)
        {
            return;
        }
        int temp = nums[nums.length-1];
        for(int i = nums.length-1;i>0;i--)
        {
            nums[i] = nums[i-1];
        }
        nums[0] = temp;
    }

    // 77. 组合  递归
    public List<List<Integer>> combine(int n, int k) {

        List<Integer> list = new  ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>> ();
        if(n < 1 || n < k)
        {
            return result;
        }
        if(k < 1)
        {
            return result;
        }
        if( k == 1)
        {
            for (int i=1 ;i< n+1;i++)
            {
                list = new  ArrayList<Integer>();
                list.add(i);
                result.add(list);
            }
            return result;
        }
        else
        {
            List<List<Integer>> temp = combine(n-1,k-1);
            for(int j=0;j<temp.size();j++)
            {
                List<Integer> listTemp = temp.get(j);
                listTemp.add(n);
            }

            if(temp.size()>0)
            {
                result.addAll(temp);
            }

            temp = combine(n-1,k);
            if(temp.size()>0)
            {
                result.addAll(temp);
            }
            return result;
        }
    }



    //1021. 删除最外层的括号
    public String removeOuterParentheses(String S) {
        Stack stack = new Stack();
        int start = 0;
        for(int i=0;i<S.length();i++)
        {
            char ch = S.charAt(i);
            if(stack.empty())
            {
                start = i;
                stack.push(ch);
            }
            else if('(' - ch == 0)
            {
                stack.push(ch);
            }
            else if(')' - ch == 0)
            {
                stack.pop();
                if(stack.empty())
                {
                    String begin = S.substring(0,start);
                    String middle = S.substring(start+1,i);
                    String end = S.substring(i+1,S.length());
                    S = begin + middle + end;
                    if(S.length() > 1)
                    {
                        i = i - 2;
                    }
                }
            }
        }

        return S;

    }


    //49. 两个数组的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int size = len1 > len2 ? len1 : len2;
        int valSize = 0;
        int value[] = new int[size];
        for(int i=0;i<len1;i++)
        {
            for(int j = 0;j< len2;j++)
            {
                if(nums1[i]==nums2[j])
                {
                    boolean find = false;
                    for(int k = 0;k<valSize;k++)
                    {
                        if(value[k]==nums1[i])
                        {
                            find = true;
                        }
                    }

                    if(!find)
                    {
                        value[valSize] = nums1[i];
                        valSize++;
                    }
                    break;
                }
            }
        }
        int res[] = new int[valSize];
        for(int i=0;i<valSize;i++)
        {
            res[i] = value[i];
        }
        return res;

    }

    //242. 有效的字母异位词
    public boolean isAnagram(String s, String t) {


        if(s.length() != t.length())
        {
            return false;
        }
        int arrS[] = new int[26];
        int arrT[] = new int[26];


        for (int i = 0;i<s.length() ;i++)
        {
            int index = s.charAt(i)-'a';
            arrS[index]++;
        }

        for(int j = 0;j<t.length();j++)
        {
            int index = t.charAt(j)-'a';
            arrT[index] ++;
        }


        for(int i=0;i<arrS.length;i++)
        {
            if(arrS[i] != arrT[i])
            {
                return false;
            }
        }

        return true;

    }


    //784. 字母大小写全排列
    public List<String> letterCasePermutation(String S) {
        String temp = "";
        S = S.toLowerCase();
        List<String> list = new ArrayList<String>();
        backTrack(list,S,temp);
        return list;
    }

    private void backTrack(List<String>list,String S,String temp)
    {
        if(temp.length() == S.length())
        {
            list.add(temp);
        }
        else
        {
            if(temp.length() <  S.length())
            {
                if(Character.isDigit(S.charAt(temp.length())))
                {
                    temp += S.charAt(temp.length());
                    backTrack(list,S,temp);
                    temp = temp.substring(0,temp.length()-1);
                }
                else
                {
                    temp +=  Character.toLowerCase(S.charAt(temp.length()));
                    backTrack(list,S,temp);
                    temp = temp.substring(0,temp.length()-1);

                    temp +=  Character.toUpperCase(S.charAt(temp.length()));
                    backTrack(list,S,temp);
                    temp = temp.substring(0,temp.length()-1);
                }

            }
        }
    }

    //46. 全排列
    public List<List<Integer>> permute(int[] nums) {

        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        backTrace(list,temp,nums);
        return list;
    }

    private void backTrace(List<List<Integer>> list,List<Integer> temp,int[] nums)
    {
        if(temp.size() == nums.length)
        {
            list.add(new ArrayList<>(temp));
        }
        else
        {
            for(int i=0;i<nums.length;i++)
            {
                if(temp.contains(nums[i]))
                {
                    continue;
                }
                else
                {
                    temp.add(nums[i]);
                    backTrace(list,temp,nums);
                    temp.remove(temp.size()-1);
                }

            }
        }

    }

    //198. 打家劫舍
    public int rob(int[] nums) {
        if(nums.length < 1)
        {
            return 0;
        }
        int temp[] = new int[nums.length];
        if(nums.length == 1)
        {
            return nums[0];
        }
        if(nums.length == 2)
        {
            if( nums[0] > nums[1])
            {
                return nums[0];
            }
            else
            {
                return nums[1];
            }
        }

        temp[0] = nums[0];
        if( nums[0] > nums[1])
        {
            temp[1] = nums[0];
        }
        else
        {
            temp[1] = nums[1];
        }

        for(int i=2;i<nums.length;i++)
        {
            if(temp[i-1] > temp[i-2]+nums[i])
            {
                temp[i] = temp[i-1];
            }
            else
            {
                temp[i] = temp[i-2]+nums[i];
            }
        }

        return temp[nums.length-1];
    }

//121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        if(prices.length < 1)
        {
            return 0;
        }
        int buy[] = new int[prices.length];
        buy[0] = prices[0];
        int max = 0;
        for(int i=1;i<prices.length;i++)
        {
            if(prices[i] < buy[i-1])
            {
                buy[i] = prices[i];
            }
            else
            {
                buy[i] = buy[i-1];
            }
            int tempProfit = prices[i] - buy[i-1];
            if(tempProfit > max)
            {
                max = tempProfit;
            }
        }
        return max;

    }

//70. 爬楼梯
    public int climbStairs(int n) {
        int arr[] = new int[n+1];

        if(n < 1)
        {
            return 0;
        }

        if(n == 1)
        {
            return 1;
        }

        if(n == 2)
        {
            return 2;
        }

        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;

        for(int i=3;i<=n;i++)
        {
            arr[i] = arr[i-1]+arr[i-2];
        }

        return arr[n];


    }

    //53. 最大子序和
    public int maxSubArray(int[] nums) {

        if(nums.length < 1)
        {
            return -1;
        }

        int max = Integer.MIN_VALUE;
        int current = Integer.MIN_VALUE;

        for(int i=0;i<nums.length;i++)
        {
            for(int j=i;j<nums.length;j++)
            {
                if(j == i)
                {
                    current = nums[i];

                    if(current > max )
                    {
                        max = current;
                    }
                }
                else
                {
                    current = current + nums[j];
                    if(current > max )
                    {
                        max = current;
                    }
                }

            }
        }

        return max;
    }

    //6. Z 字形变换          convert("PAYPALISHIRING",3);
    public String convert(String s, int numRows) {

        String val="";
        if(numRows == 1)
        {
            return s;
        }

        int length = s.length();
        int arrNumRows = ((length /(2*numRows-2 ))+1)*(numRows-1);
        char arr[][] = new char[numRows][arrNumRows];

        for(int i=0;i<numRows;i++)
        {
            for(int j=0;j<arrNumRows;j++)
            {
                arr[i][j] = 0;
            }
        }

        int row;
        for(int i=0;i<s.length();i++)
        {
            int j = (i/(2*numRows-2));
            row = (numRows-1)*j;

            int t = i%(2*numRows-2);

            if(t<numRows)
            {
                arr[t][row]=s.charAt(i);
            }
            else
            {
                arr[2*numRows-2-t][row+t-numRows+1]=s.charAt(i);
            }
        }

        for(int i=0;i<numRows;i++)
        {
            for(int j=0;j<arrNumRows;j++)
            {
                if(arr[i][j] != 0)
                {
                    val+=arr[i][j];
                }
            }
        }

        return val;
    }

    //循环链表解法  约瑟夫环问题
//n个人排成一圈，按顺时针方向依次编号1，2，3…n。从编号为1的人开始顺时针"一二三...."报数，报到m的人退出圈子。这样不断循环
//下去，圈子里的人将不断减少。最终一定会剩下一个人。试问最后剩下的人的编号。
    //Collection解法
    private void josephus1() {
        int a=41;
        int b=3;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < a; i++) {
            list.add(i+1);
        }
        //  将前端2个移到后端，删除第三个
        while (list.size()>1){
            for (int i = 0; i < b-1; i++) {
                list.add(list.remove());
            }
            System.out.print("->"+list.getFirst());
            list.remove();//remove head
        }
        System.out.println("->"+list.getFirst());

        //->3->6->9->12->15->18->21->24->27->30->33->36->39->1->5->10->14->19->23->28->32->37->41->7->13->20->26->34->40->8->17->29->38->11->25->2->22->4->35->16->31
    }

  //递归解法
    private  int josephus(int n,int m) {
        if (n == 1) {
            return 0;
        } else {
            return (josephus(n - 1, m) + m) % n;
        }
    }
    //递归解法
    int josephus3(int n, int m){
        return n == 1 ? n : (josephus3(n - 1, m) + m - 1) % n + 1;
    }

}
