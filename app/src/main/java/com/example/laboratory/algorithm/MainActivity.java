package com.example.laboratory.algorithm;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
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



    }

    //Log.e(TAG," addBinary  "+addBinary("1010","1011"));
    //Log.e(TAG," addBinary  "+addBinary("0","0"));
    //67. 二进制求和
    public String addBinary(String a, String b) {
        int len = a.length();
        int len2 = b.length();

        int large = len;
        if(len2 > len)
        {
            large = len2;
        }

        boolean plus = false;
        String value = "";
        String x = "0";
        String y = "0";
        for(int j=0;j<large;j++)
        {
            if(j < len)
            {
                x = ""+a.charAt(len-1-j);
            }
            else
            {
                x = "0";
            }
            if(j < len2)
            {
                y = ""+b.charAt(len2-j-1);
            }
            else
            {
                y = "0";
            }

            if(plus)
            {
                if(x.equals("0"))
                {
                    if(y.equals("0"))
                    {
                        value +="1";
                        plus = false;
                    }
                    else
                    {
                        value +="0";
                    }
                }
                else
                {
                    if(y.equals("0"))
                    {
                        value +="0";
                    }
                    else
                    {
                        value +="1";
                    }
                }
            }
            else
            {
                if(x.equals("0"))
                {
                    if(y.equals("0"))
                    {
                        value +="0";
                    }
                    else
                    {
                        value +="1";
                    }
                }
                else
                {
                    if(y.equals("0"))
                    {
                        value +="1";
                    }
                    else
                    {
                        value +="0";
                        plus = true;
                    }
                }
            }


        }

        if(plus)
        {
            value +="1";
        }
        String s = "";
        for(int k=value.length()-1;k>=0;k--)
        {
            s+=value.charAt(k);
        }
        return s;
    }


    //344. 反转字符串
    public void reverseString(char[] s) {

        char temp = ' ';
        int limit = s.length/2;
        for(int i=0;i<limit;i++)
        {
            temp = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = temp;
        }
    }

    //25. 验证回文串
    public boolean isPalindrome(String s) {
        if(s == null|| s.length() < 1)
        {
            return true;
        }

        int i = 0;
        int j = s.length()-1;
        s = s.toUpperCase();
        while(i < j)
        {

            //while(Character.isWhitespace(s.charAt(i)))
            while(i < j && !Character.isLetterOrDigit(s.charAt(i)))
            {
                i++;
            }
            while(j > i && !Character.isLetterOrDigit(s.charAt(j)))
            {
                j--;
            }
            if(i < j)
            {
                if(s.charAt(i) - s.charAt(j) != 0)
                {
                    return false;
                }
                i++;
                j--;

            }
            else
            {
                return true;
            }
        }
        return true;

    }

    //28. 实现 strStr()
    public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() < 1)
        {
            return 0;
        }

        if(haystack == null || haystack.length() < 1)
        {
            return -1;
        }

        if(haystack.length() < needle.length())
        {
            return -1;
        }
        int index = haystack.length() - needle.length();

        for(int i=0;i<=index;i++)
        {
            String temp = haystack.substring(i,i+needle.length());
            if(temp.equals(needle))
            {
                return i;
            }
        }
        return -1;
    }



//22. 括号生成
    public List<String> generateParenthesis(int n) {

        List<String> list = new ArrayList<String>();
        backtrace(list,"",2*n);


        List<String> temp = new ArrayList<String>();
        for(int i=0;i<list.size();i++)
        {
            if(isValid(list.get(i)))
            {
                temp.add(list.get(i));
            }
        }
        return temp;
    }

    public void backtrace(List<String> list,String current,int size)
    {
        if(current.length() == size)
        {
            list.add(current);
        }
        else  if(current.length() < size)
        {
            current += "(";
            backtrace(list,current,size);
            current = current.substring(0,current.length()-1);
            current += ")";
            backtrace(list,current,size);
        }
    }

    public boolean isValid(String str)
    {
        Stack<String> stack = new Stack<String>();

        for(int i=0;i<str.length();i++)
        {
            if(stack.size() == 0)
            {
                if(")".equals(""+str.charAt(i)))
                {
                    return false;
                }
                stack.push(""+str.charAt(i));
            }
            else if("(".equals(""+str.charAt(i)))
            {
                stack.push(""+str.charAt(i));
            }
            else if(")".equals(""+str.charAt(i)))
            {
                String value = stack.pop();
                if(")".equals(value))
                {
                    return false;
                }
            }
        }

        if(stack.size() != 0)
        {
            return false;
        }

        return true;
    }

    public String simplifyPath(String path) {
        if(path.length() < 1)
        {
            return "/";
        }

        String dir[] = path.split("/");
        LinkedList<String> stack = new LinkedList<>();
        for(int i=0;i<dir.length;i++)
        {
            String val = dir[i];
            if("..".equals(val))
            {
                if(stack.size() < 1)
                {
                    continue;
                }
                else
                {
                    stack.removeLast();
                }
            }
            else if("".equals(val))
            {
                continue;
            }
            else if(".".equals(val))
            {
                continue;
            }
            else
            {
                stack.addLast(val);
            }

        }

        if(stack.size() < 1)
        {
            return "/";
        }
        if(stack.size() == 1)
        {
            return "/"+stack.removeLast();
        }

        String v = "";

        for(int i=0;i<stack.size();i++)
        {
            //v+="/"+stack.removeFirst();
            v+="/"+stack.get(i);
        }
        return v;
    }

//17. 电话号码的字母组合
    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<String>();
        String temp = "";
        backtrace(result,temp,digits,0);
        return result;
    }

    public void backtrace(List<String> result,String temp,String digits,int index)
    {
        if(index < digits.length())
        {
            if(temp.length() == digits.length())
            {
                result.add(temp);
            }
            else
            {
                String num = ""+digits.charAt(index);
                String let = getLetterForNumber(num);
                for(int i=0;i<let.length();i++)
                {
                    temp += let.charAt(i);
                    backtrace(result,temp,digits,++index);
                    index--;
                    temp = temp.substring(0,temp.length()-1);
                }
            }
        }
    }


    public String getLetterForNumber(String str)
    {
        String letter = "";
        switch(str)
        {
            case "2":
                letter = "abc";
                break;
            case "3":
                letter = "def";
                break;
            case "4":
                letter = "ghi";
                break;
            case "5":
                letter = "jkl";
                break;
            case "6":
                letter = "mno";
                break;
            case "7":
                letter = "pqrs";
                break;
            case "8":
                letter = "tuv";
                break;
            case "9":
                letter = "wxyz";
                break;
        }
        return letter;
    }

//20. 有效的括号
//    public boolean isValid(String s) {
//        if(s.length() < 1)
//        {
//            return true;
//        }
//        Stack<String> stack = new Stack<String>();
//        for(int i=0;i<s.length();i++)
//        {
//            String index = ""+s.charAt(i);
//            if(index.equals("}") ||index.equals(")") || index.equals("]") )
//            {
//                if(stack.size() < 1)
//                {
//                    return false;
//                }
//                if(index.equals("}"))
//                {
//                    String top = stack.pop();
//                    if(!top.equals("{"))
//                    {
//                        return false;
//                    }
//                }
//                if(index.equals(")"))
//                {
//                    String top = stack.pop();
//                    if(!top.equals("("))
//                    {
//                        return false;
//                    }
//                }
//                if(index.equals("]"))
//                {
//                    String top = stack.pop();
//                    if(!top.equals("["))
//                    {
//                        return false;
//                    }
//                }
//            }
//            else
//            {
//                stack.push(index);
//            }
//
//        }
//        if(stack.size() > 0)
//        {
//            return false;
//        }
//        return true;
//    }

//14. 最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
        {
            return "";
        }
        if(strs.length == 1)
        {
            return strs[0];
        }
        String first = strs[0];
        for(int i=first.length();i>0;i--)
        {
            String prefix = first.substring(0,i);
            boolean find = true;
            for(int j=1;j<strs.length;j++)
            {
                if(strs[j].length() < i || !strs[j].substring(0,i).equals(prefix))
                {
                    find = false;
                    break;
                }
            }
            if(find)
            {
                return prefix;
            }

        }
        return "";
    }

//804. 唯一摩尔斯密码词
    public int uniqueMorseRepresentations(String[] words) {

        if(words.length < 1)
        {
            return 0;
        }

        HashSet<String> set = new HashSet<String>();

        for(int i=0;i<words.length;i++)
        {

            String v = words[i].toLowerCase();
            if(v.length()<1)
            {
                continue;
            }
            String temp="";
            for(int j=0;j<v.length();j++)
            {
                temp += getValue(v.charAt(j));
            }
            set.add(temp);
        }
        return set.size();

    }




    public String getValue(char ch)
    {
        String val="";
        switch(ch)
        {
            case 'a':
                val = ".-";
                break;
            case 'b':
                val = "-...";
                break;
            case 'c':
                val = "-.-.";
                break;
            case 'd':
                val = "-..";
                break;
            case 'e':
                val = ".";//e
                break;
            case 'f':
                val = "..-.";
                break;
            case 'g':
                val = "--.";
                break;
            case 'h':
                val = "....";
                break;
            case 'i':
                val = "..";
                break;
            case 'j':
                val = ".---";
                break;
            case 'k':
                val = "-.-";
                break;
            case 'l':
                val = ".-..";
                break;
            case 'm':
                val ="--";
                break;
            case 'n':
                val ="-.";
                break;
            case 'o':
                val ="---";
                break;
            case 'p':
                val =".--.";
                break;
            case 'q':
                val ="--.-";
                break;
            case 'r':
                val =".-.";
                break;
            case 's':
                val = "...";
                break;
            case 't':
                val ="-";
                break;
            case 'u':
                val = "..-";
                break;
            case 'v':
                val ="...-";
                break;
            case 'w':
                val =".--";
                break;
            case 'x':
                val = "-..-";
                break;
            case 'y':
                val ="-.--";
                break;
            case 'z':
                val = "--..";
                break;
        }
        return val;
    }

    //709. 转换成小写字母
    public String toLowerCase(String str) {
        if(str.length()<0)
        {
            return str;
        }
        int temp = 'a'-'A';// 97 65
        for(int i =0;i<str.length();i++)
        {
            char ch = str.charAt(i);
            if(ch >= 'A' && ch <='Z')
            {
                char ch2 = (char)(ch + temp) ;
                //str = str.substring(0,i)+ch+str.substring(i);
                str = str.replace(ch,ch2);
            }
        }
        return str;
    }

    //788. 旋转数字
    public int rotatedDigits(int N) {
        int count = 0;
        for(int i=1;i<=N;i++)
        {
            if(isGoodNumber(i))
            {
                count++;
            }
        }
        return count;
    }



    boolean isGoodNumber(int num)
    {
        int base = num;
        int res = 0;
        int key = 1;
        while(num > 0)
        {
            int temp = num%10;
            int val = getReverse(temp) ;

            if( val == -1)
            {
                return false;
            }
            res += key* val;
            key = key *10;
            num = num/10;
        }

        if(res == base)
        {
            return false;
        }
        return true;
    }


    int getReverse(int num)
    {
        if(num == 0)
        {
            return 0;
        }
        if(num == 1)
        {
            return 1;
        }
        if(num == 2)
        {
            return 5;
        }
        if(num == 5)
        {
            return 2;
        }
        if(num == 6)
        {
            return 9;
        }
        if(num == 9)
        {
            return 6;
        }
        if(num == 8)
        {
            return 8;
        }
        return -1;
    }

    //796. 旋转字符串
    public boolean rotateString(String A, String B) {


        if(A.length() != B.length())
        {
            return false;
        }

        if(A.length() == B.length() && B.length()  == 0)
        {
            return true;
        }

        for(int i=0;i<A.length();i++)
        {
            A = rotate(A);
            //if(TextUtils.equals(A,B))
            if(A.contains(B))
            {
                return true;
            }
        }

        return false;

    }

    private String  rotate(String A)
    {
        if(A.length() < 2)
        {
            return A;
        }
        char temp = A.charAt(0);
        return  A.substring(1)+temp;
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
