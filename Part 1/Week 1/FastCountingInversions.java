/**
 * @name Miriam Lee
 * @course Algorithms: Design and Analysis, Part 1
 * @quiz Programming Assignment #1
 * @date Jul 17, 2016
 * @question Your task is to compute the number of inversions in the file given,
 *           where the ith row of the file indicates the ith entry of an array.
 * @grade 100%
 * @status Passed
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FastCountingInversions {

  /**
   * @param a
   * @param from
   * @param to
   *          inclusive
   * @return
   */
  private long MergeCount(int a[], int from, int to) {
    if (to - from == 0)
      return 0;
    if (to - from == 1)
      if (a[from] < a[to])
        return 0;
      else {
        int temp = a[to];
        a[to] = a[from];
        a[from] = temp;
        return 1;
      }
    // show(a);

    int mid = mid(from, to);
    long x = MergeCount(a, from, mid);
    // show(a);
    long y = MergeCount(a, mid + 1, to);
    // show(a);

    return x + y + MergeCountSplitInv(a, from, to, mid);
  }

  private static void show(int[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i]);
    }
    System.out.println();
  }

  private int mid(int from, int to) {
    return (to + from) / 2;// from + (to - from) / 2;
  }

  private int MergeCountSplitInv(int[] a, int from, int to, int mid) {

    int[] s = new int[a.length];
    System.arraycopy(a, 0, s, 0, a.length);

    int i = from;
    int j = mid + 1;
    int c = 0;

    for (int k = from; k <= to; k++) {
      if (j > to || (i <= mid && a[i] < a[j])) {
        s[k] = a[i++];
      } else // if (i >= mid(to, from) || a[j] < a[i])
      {// split inv
        s[k] = a[j++];
        if (i <= mid)
          c += mid + 1 - i;
      }

    }
    System.arraycopy(s, from, a, from, to - from + 1);
    // show(a);
    // System.out.println(c);
    return c;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // // TEST CASE - 1 A - {1,3,5,2,4,6} ANS - 3
    // System.out.println("3\t" + new
    // FastCountingInversions().MergeCount(new int[] { 1, 3, 5, 2, 4, 6 },
    // 0, 5));//3
    // /////////////// TEST CASE - 2 A- {1,5,3,2,4} ANS - 4
    System.out.println(
        "4\t" + new FastCountingInversions().MergeCount(new int[] { 1, 5, 3, 2, 4 }, 0, 4));// 4
    // TEST CASE - 3 A- {5,4,3,2,1} ANS - 10
    System.out.println(
        "10\t" + new FastCountingInversions().MergeCount(new int[] { 5, 4, 3, 2, 1 }, 0, 4));// 10
    // TEST CASE - 4 A - {1,6,3,2,4,5} ANS - 5
    System.out.println(
        "5\t" + new FastCountingInversions().MergeCount(new int[] { 1, 6, 3, 2, 4, 5 }, 0, 5));// 5

    // Test Case - #1 - 15 numbers A - { 9, 12, 3, 1, 6, 8, 2, 5, 14, 13,
    // 11, 7, 10, 4, 0 } Ans - 56
    System.out.println("56\t" + new FastCountingInversions()
        .MergeCount(new int[] { 9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0 }, 0, 14));// 56

    // Test Case - #2 - 50 numbers
    // A - { 37, 7, 2, 14, 35, 47, 10, 24, 44, 17, 34, 11, 16, 48, 1, 39, 6,
    // 33, 43, 26, 40, 4, 28, 5, 38, 41, 42, 12, 13, 21, 29, 18, 3, 19, 0,
    // 32, 46, 27, 31, 25, 15, 36, 20, 8, 9, 49, 22, 23, 30, 45 }
    // Ans - 590
    System.out.println(
        "590\t" + new FastCountingInversions().MergeCount(new int[] { 37, 7, 2, 14, 35, 47, 10, 24,
            44, 17, 34, 11, 16, 48, 1, 39, 6, 33, 43, 26, 40, 4, 28, 5, 38, 41, 42, 12, 13, 21, 29,
            18, 3, 19, 0, 32, 46, 27, 31, 25, 15, 36, 20, 8, 9, 49, 22, 23, 30, 45 }, 0, 49));// 590

    // Test Case - #3 - 100 numbers
    // { 4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49,
    // 28, 18, 46, 21, 39, 51, 7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83,
    // 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81,
    // 42, 95, 20, 25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59,
    // 64, 93, 17, 50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73, 41, 36, 57,
    // 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54 }
    // Ans - 2372
    System.out
        .println("2372\t" + new FastCountingInversions()
            .MergeCount(
                new int[] { 4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28, 18,
                    46, 21, 39, 51, 7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10,
                    26, 48, 3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81, 42, 95, 20, 25, 74, 24, 72,
                    91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50, 56, 94, 90, 89, 32, 37,
                    34, 65, 1, 73, 41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54 },
                0, 99));// 2372
    Scanner in;
    try {
      in = new Scanner(new File("test/_bcb5c6658381416d19b01bfc1d3993b5_IntegerArray.txt"));
      int n = 100000;
      int[] a = new int[n];

      for (int i = 0; i < n; i++) {
        a[i] = in.nextInt();
      }

      System.out.println(new FastCountingInversions().MergeCount(a, 0, n - 1));// 3

    } catch (FileNotFoundException e) {
      e.printStackTrace();

    }

  }

}
