/**
 * @name Miriam Lee
 * @course Algorithms: Design and Analysis, Part 1
 * @date Sep 2, 2016 10:33:39 PM
 * @question The goal of this problem is to implement a variant of the 2-SUM algorithm (covered in
 *           the Week 6 lecture on hash table applications).
 * @score
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author mimi
 *
 */
public class TwoSum {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // The file contains 1 million integers, both positive and negative (there might be some
    // repetitions!)
    // Scanner in = new Scanner(System.in); //
    long t = System.currentTimeMillis();
    Scanner in;
    try {
      // in = new Scanner(
      // new File("bin/_6ec67df2804ff4b58ab21c12edcb21f8_algo1-programming_prob-2sum.txt"));
      in = new Scanner(System.in);
      int ans = 0;
      HashSet<Long> H = new HashSet<>();
      // 1.) insert elements of A into hash table H
      while (in.hasNext()) {

        H.add(in.nextLong());
      }
      // 2.) for each x in A, Lookup t-x
      Iterator<Long> it = H.iterator();
      while (it.hasNext()) {
        Long x = it.next();
        for (long y = -10000 - x; y < 10000 - x; y++) {
          if (y == 0)
            continue;
          if (x != y && H.contains(y)) {// TODO (NOTE: ensuring distinctness requires a one-line
                                        // addition to the algorithm from lecture.)
            ans++;
            System.out.println("( " + x + "," + y + ") ");
          }
        }
      }
      System.out.println(
          "Answer: " + ans / 2 + " in " + (System.currentTimeMillis() - t) / 1000.0 + "ms");// 50195
    } catch (Exception e) {
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
