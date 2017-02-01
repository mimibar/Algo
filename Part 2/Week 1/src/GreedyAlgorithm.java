import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @name Miriam Lee
 * @course Algorithms: Design and Analysis, Part 1
 * @date Jan 17, 2017 11:07:31 PM
 * @question code up the greedy algorithms from lecture for minimizing the weighted sum of
 *           completion times..
 * @score
 */
class Job {
  int w;
  int l;

  public Job(int w, int l) {
    this.w = w;
    this.l = l;
  }

}

/**
 * @author mimi
 *
 */
public class GreedyAlgorithm {
  PriorityQueue<Job> pq;

  public GreedyAlgorithm() {

    pq = new PriorityQueue<>(new Comparator<Job>() {

      /*
       * schedules jobs in decreasing order of the difference (weight - length).
       * 
       * (non-Javadoc)
       * 
       * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
       */
      @Override
      public int compare(Job j1, Job j2) {
        int d1 = j1.w - j1.l;
        int d2 = j2.w - j2.l;

        if (d1 < d2)
          return -1;
        if (d1 > d2)
          return 1;

        // if two jobs have equal difference (weight - length), you should schedule the job with
        // higher weight first.
        if (j1.w < j2.w)
          return -1;
        if (j1.w > j2.w)
          return 1;

        return 0;
      }
    });
  }

  /**
   * larger weight
   * 
   * @param args
   */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    GreedyAlgorithm ga = new GreedyAlgorithm();

    int n = in.nextInt();

    while (n-- > 0) {
      ga.pq.add(new Job(in.nextInt(), in.nextInt()));
    }
    // You should report the sum of weighted completion times of the resulting schedule --- a
    // positive integer --- in the box below.
  }

}
