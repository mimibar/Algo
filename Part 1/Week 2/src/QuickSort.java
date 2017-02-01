/**
 * @name Miriam Lee
 * @course Algorithms: Design and Analysis, Part 1
 * @date Aug 21, 2016
 * @question Your task is to compute the total number of comparisons used to sort the given input
 *           file by QuickSort. As you know, the number of comparisons depends on which elements are
 *           chosen as pivots, so we'll ask you to explore three different pivoting rules.
 * @score 100%
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {

  long c = 0;// comparisons
  private int rule;

  /**
   * @param r
   *          1 if first, 2 if last
   */
  public QuickSort(int r) {
    rule = r;// pivoting rule
  }

  /**
   * WARNING: The Partition subroutine can be implemented in several different ways, and different
   * implementations can give you differing numbers of comparisons. For this problem, you should
   * implement the Partition subroutine exactly as it is described in the video lectures (otherwise
   * you might get the wrong answer).
   * 
   * @return
   */
  int partition(int[] A, int p, int l, int r) {
    // You should not count comparisons one-by-one. Rather, when there is a recursive call on a
    // subarray of length m, you should simply add m-1 to your running total of comparisons. (This
    // is because the pivot element is compared to each of the other m-1 elements in the subarray in
    // this recursive call.)
    c += (r - l);
    int i = l + 1;
    for (int j = l + 1; j <= r; j++) {
      if (A[j] < p) {
        swap(A, j, i);
        i++;
      }
    }
    swap(A, l, i - 1);

    return i - 1;
  }

  private void swap(int[] A, int i, int j) {
    int temp = A[j];
    A[j] = A[i];
    A[i] = temp;
  }

  /**
   * l and r inclusive
   * 
   * @param A
   * @param l
   * @param r
   * @return
   */
  private long quickSort(int[] A, int l, int r) {
    if (r - l < 1)
      return c;
    int p = choosePivot(A, l, r);
    p = partition(A, p, l, r);
    quickSort(A, l, p - 1);// left
    quickSort(A, p + 1, r);// right
    return c;
  }

  /**
   * @param A
   * @param l
   *          left
   * @param r
   *          right
   * @return the value at the pivot index
   */
  private int choosePivot(int[] A, int l, int r) {
    switch (rule) {
      case 1:// first
        return A[l];
      case 2:// last
        // Recall from the lectures that, just before the main Partition subroutine, you should
        // exchange the pivot element (i.e., the last element) with the first element.
        swap(A, l, r);
        return A[l];

      case 3:// median-of-three
        // Consider the first, middle, and final elements of the given array. (If the array has odd
        // length it should be clear what the "middle" element is; identify which of these three
        // elements is the median (i.e., the one whose value is in between the other two), and use
        // this as your pivot.
        // max
        int max, min;// maxindex, minindex
        if (A[l] >= A[r]) {
          max = l;
          min = r;

        } else {
          max = r;
          min = l;
        }

        // int max = Math.max(A[l], A[r]);
        // int min = Math.min(A[l], A[r]);
        int m = l + (r - l) / 2;

        // return (m <=min) ? min : ((m > max) ? max : m);
        swap(A, l, (A[m] >= A[max]) ? max : (A[m] > A[min] ? m : min));
        // (including exchanging the pivot element with the first element just before the main
        // Partition subroutine).
        return A[l];

    }
    return -1;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {

    // PA#2 Test Cases
    // Tim RoughgardenInstructorWeek 2 · 2 months ago · Edited
    // Test cases previously posted by learner SzuHsien Lee.
    //
    // https://dl.dropboxusercontent.com/u/20888180/AlgI_wk2_testcases/10.txt
    // https://dl.dropboxusercontent.com/u/20888180/AlgI_wk2_testcases/100.txt
    // https://dl.dropboxusercontent.com/u/20888180/AlgI_wk2_testcases/1000.txt
    //
    // Answers are:
    //
    // size first last median
    // 10 25 29 21
    // 100 615 587 518
    // 1000 10297 10184 8921

    // 10000 162085? 164123? 138382?

    System.out.println("size\tfirst\tlast\tmedian");

    int n = 10;
    int[] a = read(n, "10");
    System.out.println(n + "\t" + new QuickSort(1).quickSort(Arrays.copyOf(a, n), 0, n - 1) + "\t"
        + new QuickSort(2).quickSort(Arrays.copyOf(a, n), 0, n - 1) + "\t"
        + new QuickSort(3).quickSort(a, 0, n - 1));// last

    n = 100;
    a = read(n, "100");
    System.out.println(n + "\t" + new QuickSort(1).quickSort(Arrays.copyOf(a, n), 0, n - 1) + "\t"
        + new QuickSort(2).quickSort(Arrays.copyOf(a, n), 0, n - 1) + "\t"
        + new QuickSort(3).quickSort(a, 0, n - 1));// last

    n = 1000;
    a = read(n, "1000");
    System.out.println(n + "\t" + new QuickSort(1).quickSort(Arrays.copyOf(a, n), 0, n - 1) + "\t"
        + new QuickSort(2).quickSort(Arrays.copyOf(a, n), 0, n - 1) + "\t"
        + new QuickSort(3).quickSort(a, 0, n - 1));// last

    // all of the integers between 1 and 10,000 (inclusive, with no repeats) in unsorted order
    n = 10000;
    a = read(n, "_32387ba40b36359a38625cbb397eee65_QuickSort");
    System.out.println(n + "\t" + new QuickSort(1).quickSort(Arrays.copyOf(a, n), 0, n - 1) + "\t"
        + new QuickSort(2).quickSort(Arrays.copyOf(a, n), 0, n - 1) + "\t"
        + new QuickSort(3).quickSort(a, 0, n - 1));// last
  }

  private static int[] read(int n, String s) {
    int[] a = new int[n];
    try {
      Scanner in = new Scanner(new File("test/" + s + ".txt"));
      for (int i = 0; i < n; i++) {
        a[i] = in.nextInt();
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return a;
  }

}
