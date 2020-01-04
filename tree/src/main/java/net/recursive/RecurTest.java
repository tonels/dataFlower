package net.recursive;

import org.junit.Test;

import java.math.BigInteger;

public class RecurTest {


// -------------- 斐波那契数列 ---------------

    @Test
    public void BirthRabbit() {
        int i = 1;
        for (i = 1; i <= 20; i++) {
            System.out.println("兔子第" + i + "个月的总数为:" + f(i));
        }
    }

    public static int f(int x) {
        if (x == 1 || x == 2) {
            return 1;
        } else {
            return f(x - 1) + f(x - 2);
        }
    }
// -------------- 斐波那契数列 ----------------
// -------------- 1 - 100 相加----------------

    @Test
    public void plus() {
        System.out.println("计算结果：" + this.sum(100) + "!");

    }
    public int sum(int i) {
        if (i == 1) {
            return 1;
        }
        return i + sum(i - 1);
    }
// -------------- 1 - 100 相加----------------
// ----------------  阶 乘 -------------------

    @Test
    public void test3() {
        try {
            System.out.println("计算结果 ： " + sum2(10) + "...!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public BigInteger sum2(int i) {
        if (i == 1) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(i).multiply(sum2(i - 1));
    }
// ----------------  阶 乘 -------------------
// ----------------  有序数组a、b合并成一个新的有序数组 -------------------

    @Test
    public void test4() {
        int[] a = {1, 3, 4};
        int[] b = {2, 3, 5, 6};
        int[] c = mergeArray(a, b);
        for (int n : c) {
            System.out.print(n + " ");
        }
    }

    // 合并数组
    public static int[] mergeArray(int[] a, int[] b) {
        int result[] = new int[a.length + b.length];
        if (checkSort(a) && checkSort(b)) {
            // 说明ab数组都是有序的数组
            // 定义两个游标
            int i = 0, j = 0, k = 0;
            while (i < a.length && j < b.length) {
                if (a[i] <= b[j]) {
                    result[k++] = a[i++];
                } else {
                    result[k++] = b[j++];
                }
            }
            while (i < a.length) {
                // 说明a数组还有剩余
                result[k++] = a[i++];
            }
            while (j < b.length) {
                result[k++] = b[j++];
            }
        }
        return result;
    }

    // 检查一个数组是否是有序1 2 3
    public static boolean checkSort(int[] a) {
        boolean flag = false;// 默认不是有序的
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                // 说明不是有序的
                flag = false;
                break;
            } else {
                flag = true;
            }
        }
        return flag;
    }

// ----------------  有序数组a、b合并成一个新的有序数组 -------------------
// ----------------  归并排序算法实现 -------------------

    @Test
    public void sort(int[] data, int left, int right) {
        if (left < right) {
            // 首先找出中间的索引
            int center = (left + right) / 2;

            // 对中间索引左边的数组进行递归
            sort(data, left, center);

            // 对中间索引右边的数组进行递归
            sort(data, center + 1, right);
            // 合并
            merge(data, left, center, right);
        }
    }

    public static void merge(int[] data, int left, int center, int right) {
        int[] tmpArr = new int[data.length];
        int mid = center + 1;
        // third记录中间数组的索引
        int third = left;
        int tmp = left;
        while (left <= center && mid <= right) {
            // 将两个数组中取出最小的数放入中间数组
            if (data[left] <= data[mid]) {
                tmpArr[third++] = data[left++];
            } else {
                tmpArr[third++] = data[mid++];
            }
        }
        // 剩余部分依次放入中间数组
        while (mid <= right) {
            tmpArr[third++] = data[mid++];
        }
        while (left <= center) {
            tmpArr[third++] = data[left++];
        }
        while(tmp <= right){
            data[tmp] = tmpArr[tmp++];
        }
    }

    @Test
    public  void test6(String[] args) {
        int[] a = { 3, 2, 5, 4 };
        sort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

// ----------------  归并排序算法实现 -------------------



















}
