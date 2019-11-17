package com.example.laboratory.algorithm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String Tag = "duck";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int value[] = new int[2];
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
//                String t= "";
//        t.toLowerCase();
//        t.length();
//        t = t.substring(0,t.length()-1);


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
