/**
 * @name Miriam Lee
 * @course Algorithms: Design and Analysis, Part 1
 * @date Aug 21, 2016
 * @question Your task is to compute the total number of comparisons used to sort the given input
 *           file by QuickSort. As you know, the number of comparisons depends on which elements are
 *           chosen as pivots, so we'll ask you to explore three different pivoting rules.
 * @score
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickSort {

  double c = 0;// comparisons
  private int rule;

  public QuickSort(int i) {
    rule = i;// pivoting rule
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
      if (A[j] < A[p]) {
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
  private double quickSort(int[] A, int l, int r) {
    if (r - l < 1)
      return c;
    int p = choosePivot(l, r);
    p = partition(A, p, l, r);
    quickSort(A, l, p - 1);// left
    quickSort(A, p + 1, r);// right
    return c;
  }

  private int choosePivot(int l, int r) {
    switch (rule) {
    case 1:
      return l;
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
    //
    // https://dl.dropboxusercontent.com/u/20888180/AlgI_wk2_testcases/100.txt
    //
    // https://dl.dropboxusercontent.com/u/20888180/AlgI_wk2_testcases/1000.txt
    //
    // Answers are:
    //
    // size first last median
    //
    // 10 25 29 21
    //
    // 100 615 587 518
    //
    // 1000 10297 10184 8921

    Scanner in;
    try {
      // in = new Scanner(new File("bin/quiz/_32387ba40b36359a38625cbb397eee65_QuickSort.txt"));
      in = new Scanner(new File("bin/1000.txt"));

      int n = 1000;// all of the integers between 1 and 10,000 (inclusive, with no repeats) in
      // unsorted order
      int[] a = new int[n];

      for (int i = 0; i < n; i++) {
        a[i] = in.nextInt();
      }

      // For the first part of the programming assignment, you should always use the first element
      // of the array as the pivot element.
      System.out.println(new QuickSort(1).quickSort(a, 0, n - 1));// 3

    } catch (FileNotFoundException e) {
    }
  }

}
