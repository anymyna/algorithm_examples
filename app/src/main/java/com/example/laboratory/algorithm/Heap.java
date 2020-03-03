package com.example.laboratory.algorithm;

import java.util.Arrays;
import java.util.Random;


/**
 * 堆：
 * 1.完全二叉树；
 * 2.各结点满足堆次序属性（最大堆：每个节点中的数据项都大于或者等于其子女的数据项） 根节点从0开始编号， 完全二叉树的性质
 *         1.i的子结点2i+1，2i+2；父节点(i-1)/2
 *         2.最后一个非叶子结点(n-1)/2（就是最后一个叶子节点的父节点）
 *	Heap：
 *	方法：
 *	Heap()
 *	Heap(int[])
 *	isEmpty()
 *	insert()
 *	peek()
 *	delete()
 *	deleteTop()
 *	Heapify()
 *
 *	内部类：
 *	HeapSort 用于堆排序
 *
 *	注：堆由数组实现，这里暂时不考虑堆的动态扩展
 *
 *	一些方法的思路：
 * 1.下调
 * 作用于近似堆(仅有根节点不符合堆属性)
 * 1.1把根节点作为当前节点，
 * 1.2循环下调
 * 1.2.1找出左右子女中较大的那一个（还要考虑右子女是否存在）
 * 1.2.1.1右子女不存在，则较大的节点为左子女
 * 1.2.1.2右子女存在，比较两个节点的值，选出较大值的那个节点
 * 1.2.2将找出的节点和当前节点比较,
 * 1.2.2.1若当前节点<找出的节点，将两个节点值互换，当前节点的位置设为找出的节点位置
 * 1.2.2.2否则，退出循环
 * 结束条件:当前节点没有左右子女节点
 * 边界判断：
 * 当前节点有可能左子女节点不存在（即，右子女也会不存在）
 * 当前节点有可能右子女节点不存在，但是左子女存在
 * 当前节点左右子女节点均存在
 *
 * 2.上调
 * 作用于堆，在堆末尾插入一个元素
 * 2.1将插入节点设为当前节点，判断当前节点是否大于其父节点
 * 2.1.1若大于将其和父节点换位，当前节点设为父节点，继续执行2.1
 * 2.1.2否则，结束函数
 * 结束条件：
 * 当前节点为根节点
 *
 * 3.构建堆
 * 从最后一个非叶子节点（(n-1)/2个节点）往根节点遍历，对每个节点使用下调算法
 *
 * 4.堆排序
 * 4.1构建堆
 * 4.2 排序
 * 4.2.1 从n到1节点，取出节点作为当前节点i
 * 4.2.2 交换根节点和当前节点i，
 * 4.2.3 对0到i-1节点使用下调算法
 *
 */
public class Heap {

    private int[] data;
    private int mySize;

    /**
     * 构建一个空堆
     */
    public Heap() {
        data = new int[100];
        mySize = 0;
    }

    /**
     * 通过指定的数组构建堆
     * @param arr
     */
    public Heap(int[] arr){
        if (arr == null || arr.length == 0) {
            throw new RuntimeException("Invalid Parameters.");
        }
        data = new int[arr.length * 2];// 2倍扩容
        // 复制元素
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        //设置mySize
        mySize = arr.length;
        //构建堆
        Heapify(data,mySize-1);
    }

    /**
     * 返回堆中的数组
     * @return
     */
    public  int[] getData(){
        return Arrays.copyOfRange(data, 0, mySize);//返回一个副本
    }

    /**
     * 返回堆的大小
     * @return
     */
    public int size(){
        return mySize;
    }

    /**
     * 判断堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return mySize == 0;
    }

    /**
     * 在堆的末尾插入一个元素
     *
     * @return
     */
    public boolean insert(int elem) {
        data[mySize++]=elem;
        percolate_up(data,0,mySize-1);//上调
        return true;
    }

    /**
     * 返回堆顶的元素
     * @return
     */
    public int peek() {
        if (mySize == 0) {
            throw new RuntimeException("Heap is empty!");
        }
        return data[0];
    }

    /**
     * 从末尾删除
     * @return
     */
    public int delete() {
        if (mySize == 0) {
            throw new RuntimeException("Heap is empty!");
        }
        int temp=data[mySize-1];
        data[--mySize] = 0;
        return temp;
    }

    /**
     * 删除首节点
     *
     */
    public int deleteTop(){
        if (mySize == 0) {
            throw new RuntimeException("Heap is empty!");
        }
        swap(data,0,mySize-1);
        int temp=data[mySize-1];
        percolate_down(data, 0, mySize-2);//下调，将近似堆恢复成堆
        data[--mySize]=0;

        return temp;
    }

    /**
     * 从给定数组的start位置开始往end位置下调
     * @param data
     * @param start
     * @param end
     *
     * 复杂度：O(lgn)
     */
    private void percolate_down(int[] data,int start,int end) {
        if (data == null || start < 0 || end >= data.length || start > end) {
            throw new RuntimeException("Invalid Parameters!");
        }

        int cur = start;
        int left_child = cur * 2 + 1;
        // 遍历的条件：当前节点的左子节点存在
        while (left_child <= end) {
            //1.找出左右子女中较大的节点
            int right_child=cur*2+2;
            int greater_child=left_child;//左右子女中较大的节点
            if(right_child<=end){
                if(data[left_child]<data[right_child])
                    greater_child=right_child;
            }
            //其他情况，都是greater_child=left_child;

            //2.拿较大的节点和当前节点比较
            if(data[cur]<data[greater_child]){
                swap(data,cur,greater_child);

                cur=greater_child;//更新节点位置
                left_child=cur*2+1;
            }else{
                break;
            }
        }
    }

    /**
     * end节点使得原来的堆属性无法维持，上调该节点，恢复堆属性
     * @param data
     * @param start 堆的根节点
     * @param end	末尾节点
     *
     * 复杂度：O(lgn)
     */
    private void percolate_up(int[] data,int start,int end) {
        if (data == null || start < 0 || end >= data.length || start > end) {
            throw new RuntimeException("Invalid Parameters!");
        }

        int cur = end;
        while (cur != start) {
            int parent = (cur - 1) / 2;
            if (data[cur] > data[parent]) {
                swap(data, cur, parent);
                cur = parent;
            }else{
                break;
            }

        }
    }

    /**
     * 构建堆
     *
     * @param data
     * @param end 数组的最后一个元素
     *
     * 复杂度O(nlgn)
     */
    public void Heapify(int[] data, int end) {
        if (data == null || data.length == 0 || end <= 0) {
            throw new RuntimeException("Invalid Parameters!");
        }

        for (int i = (end - 1) / 2; i >= 0; i--) {
            percolate_down(data, i, end);
        }
    }

    /**
     * 交换值
     * @param arr
     * @param index1
     * @param index2
     */
    public void swap(int[] arr,int index1,int index2){
        if (arr != null && index1 != index2 && index1 < arr.length
                && index2 < arr.length) {
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }
    }

    /**
     * 显示array的内容
     *
     * @param a
     *            数组
     */
    public static void showArray(int a[]) {
        for (int i = 0; i < a.length; i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(a[i] + "\t");
        }
    }

    /**
     *
     *用于堆排序算法
     *
     *O(nlgn)
     */
    public  class HeapSort{
        public void sort(int[] arr){
            //1.构建堆
            Heapify(arr, arr.length-1);
            for(int i=arr.length-1;i>=1;i--){
                swap(arr,0,i);
                percolate_down(arr, 0, i-1);
            }
        }
    }

    public static void test() {
        // int[] a = { 4, 9, 8, 7, 6, 5 };
        // Heap heap = new Heap();
        // 下调测试
        // heap.percolate_down(a, 0, a.length-1);
        // Heap.showArray(a);

        // 上调测试
        // int[] b={10,9,8,7,6,11};
        // heap.percolate_up(b, 0, b.length-1);
        // Heap.showArray(b);

        // 构建堆测试
        // heap.Heapify(a, a.length-1);
        // Heap.showArray(a);

        // 堆测试
        // int[] a = { 4, 5, 6, 7, 8, 9, 10 };
        // Heap heap = new Heap(a);
        // System.out.println("heap size:" + heap.size());
        // System.out.println("Is heap empty?" + heap.isEmpty());
        // int[] data = heap.getData();
        // Heap.showArray(data);
        // // 插入
        // heap.insert(11);
        // heap.insert(12);
        // System.out.println("\nheap size:" + heap.size());
        // data = heap.getData();
        // Heap.showArray(data);
        // System.out.println();
        // //删除
        // heap.delete();
        // data = heap.getData();
        // Heap.showArray(data);
        // heap.deleteTop();
        // data = heap.getData();
        // Heap.showArray(data);
        // System.out.println("\nheap size:" + heap.size());

        //堆排序测试
        int[] a=new int[100];
        Random random=new Random();
        for(int i=0;i<a.length;i++){
            a[i]=random.nextInt(101);
        }

        HeapSort sort=new Heap().new HeapSort();
        sort.sort(a);
        Heap.showArray(a);
    }

}
