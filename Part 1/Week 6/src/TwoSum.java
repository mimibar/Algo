/**
 * @name Miriam Lee
 * @course Algorithms: Design and Analysis, Part 1
 * @date Sep 2, 2016 10:33:39 PM
 * @question The goal of this problem is to implement a variant of the 2-SUM algorithm (covered in
 *           the Week 6 lecture on hash table applications).
 * @score 2/2 points earned (100%)
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author mimi
 *
 */
public class TwoSum {

  /**
   * @param args
   */
  public static void main(String[] args) {
    long ti = System.currentTimeMillis();
    Scanner in;
    try {
      // The file contains 1 million integers, both positive and negative (there might be some
      // repetitions!)
      in = new Scanner(
          new File("test/_6ec67df2804ff4b58ab21c12edcb21f8_algo1-programming_prob-2sum.txt"));
      // in = new Scanner(System.in);
      int ans = 0;
      HashSet<Long> H = new HashSet<>();
      ArrayList<Long> A = new ArrayList<>();

      // 1.) insert elements of A into hash table H
      while (in.hasNext()) {
        long a = in.nextLong();
        if (!H.contains(a))
          A.add(a);
        H.add(a);
      }
      Collections.sort(A);
      TreeSet<Integer> ts = new TreeSet<Integer>();
      for (int t = -10000; t <= 10000; t++)
        ts.add(t);

      for (int i = 0; i < A.size(); i++) {
        // 2.) for each x in A, Lookup t-x
        Long x = A.get(i);
        Iterator<Integer> it = ts.iterator();
        while (it.hasNext()) {
          Integer t = it.next();

          long y = t - x;
          // TODO (NOTE: ensuring distinctness requires a one-line addition to the algorithm from
          // lecture.)
          if (x != y && y < x && H.contains(y)) {
            ans++;
            System.out.println("(" + x + " ," + y + ")");
            it.remove();
          }
        }
      }

      // (an integer between 0 and 20001)
      System.out
          .println("Answer: " + ans + " in " + (System.currentTimeMillis() - ti) / 1000.0 + "s");// 50195
    } // Answer: 427 in 661.104s
    catch (

    Exception e) {
      e.printStackTrace();
    }
  }

  // Test Cases for PA1 and PA2
  // For PA1:
  // 68037543430
  // -21123414637
  // 56619844751
  // 59688006695
  // 82329471587
  // 21123414637
  // 3
  // -60489726142
  // 2
  // 2
  // -32955448858
  // 32955438858
  // 53645918962
  // -44445057840
  // 10793991159
  //
  // Answer 3
  //
  // Valid pairs are
  //
  // (-21123414637, 21123414637) (3, 2) (-32955448858, 32955438858)
  //
  // Pairs 3,3 and 2,2 are assumed invalid due to "distinct" restriction.
}
