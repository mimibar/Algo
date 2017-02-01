/**
 * @name Miriam Lee
 * @course Algorithms: Design and Analysis, Part 1
 * @date Sep 3, 2016 3:02:54 PM
 * @question The goal of this problem is to implement the "Median Maintenance" algorithm (covered in
 *           the Week 5 lecture on heap applications).
 * @score 2/2 points earned (100%)
 */
import java.io.File;
import java.util.Scanner;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

/**
 * @author mimi
 *
 */
public class Median {

  private static int k;

  /**
   * @param args
   */
  public static void main(String[] args) {
    long ti = System.currentTimeMillis();
    MaxPQ<Integer> hLow = new MaxPQ<Integer>();
    MinPQ<Integer> hHigh = new MinPQ<Integer>();
    Long ans = 0L;
    Scanner in;
    try {
      in = new Scanner(new File("test/_6ec67df2804ff4b58ab21c12edcb21f8_Median.txt"));
      // in = new Scanner(System.in);
      while (in.hasNext()) {

        int x = in.nextInt();
        k++;
        // So, if k is odd, then mk is ((k+1)/2)th smallest number among x1,…,xk; if k is even, then
        // mk is the (k/2)th smallest number among x1,…,xk.)
        if (k == 1 || x < hLow.max()) {
          if (k > 1 && k % 2 == 0)
            hHigh.insert(hLow.delMax());
          hLow.insert(x);
        } else {
          hHigh.insert(x);
          if (k > 1 && k % 2 != 0)
            hLow.insert(hHigh.delMin());
        }

        System.out.println(hLow.max());
        ans += hLow.max();
      }
      System.out.println("Answer: " + ans + " mod " + (ans % 10000) + " in "
          + (System.currentTimeMillis() - ti) / 1000.0 + "s");// Answer: 46831213 mod 1213 in 0.209s

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // For PA2: head Median.txt > testMedian.txt
  // 6331
  // 2793
  // 1640
  // 9290
  // 225
  // 625
  // 6195
  // 2303
  // 5685
  // 1354
  // Answer 9335
  //
  // My output
  // 6331 Median is 6331
  // 2793 Median is 2793
  // 1640 Median is 2793
  // 9290 Median is 2793
  // 225 Median is 2793
  // 625 Median is 1640
  // 6195 Median is 2793
  // 2303 Median is 2303
  // 5685 Median is 2793
  // 1354 Median is 2303
  // The last 4 digits of the average are 9335

}
