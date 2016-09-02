/**
 * @name Miriam Lee
 * @course Algorithms: Design and Analysis, Part 1
 * @date Sep 2, 2016 1:09:56 AM
 * @question Your task is to code up the algorithm from the video lectures for computing strongly
 *           connected components (SCCs), and to run this algorithm on the given graph.
 * @score 1/1 points earned (100%) Quiz passed!
 */
import java.io.File;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.StdOut;

/**
 * KosarajuSharirSCC.java implements the API with just a few lines of code added to CC.java, as
 * follows: Given a digraph G, use DepthFirstOrder.java to compute the reverse postorder of its
 * reverse, GR. Run standard DFS on G, but consider the unmarked vertices in the order just computed
 * instead of the standard numerical order. All vertices reached on a call to the recursive dfs()
 * from the constructor are in a strong component (!), so identify them as in CC.
 * 
 * @author mimi
 *
 */
public class SCCAlgs4 {
  private static Digraph newDigraph(Scanner in, int V, int E) {
    Digraph G = new Digraph(V);
    try {

      if (E < 0)
        throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
      for (int i = 0; i < E; i++) {
        // Vertices are labeled as positive integers from 1 to 875714.
        int v = in.nextInt() - 1;
        int w = in.nextInt() - 1;
        G.addEdge(v, w);
      }
    } catch (NoSuchElementException e) {
      throw new InputMismatchException("Invalid input format in Digraph constructor");
    }
    return G;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // I had Stack Overflows that I solved adding the -Xss8M option to get a 8MB stack (-Xss208m)
    Scanner in;
    try {
      in = new Scanner(new File("bin/_410e934e6553ac56409b2cb7096a44aa_SCC.txt"));
      int V = 875714, E = 5105043;

      // in = new Scanner(System.in);

      // int V = 9, E = 11;
      // int V = 8, E = 14;
      // int V = 8, E = 9;
      // int V = 8, E = 11;
      // int V = 12, E = 20;

      Digraph G = newDigraph(in, V, E);

      KosarajuSharirSCC scc = new KosarajuSharirSCC(G);

      // Output Format: You should output the sizes of the 5 largest SCCs in the given graph,
      // in decreasing order of sizes, separated by commas (avoid any spaces). e.g.
      // "500,400,300,200,100"

      // number of connected components
      int m = scc.count();
      StdOut.println(m + " components");

      // compute list of vertices in each strong component
      int components[] = new int[m < 5 ? 5 : m];
      for (int v = 0; v < G.V(); v++) {
        components[scc.id(v)]++;
      }
      Arrays.sort(components);
      StdOut.print("Answer: ");

      // print results
      for (int i = components.length - 1; i >= components.length - 5; i--) {
        StdOut.print(components[i]);
        if (i >= components.length - 4)
          StdOut.print(",");
      }
      StdOut.println();

    } catch (

    Exception e) {
      e.printStackTrace();
    }
  }

  // PA#4: some small test cases
  // 1 4
  // 2 8
  // 3 6
  // 4 7
  // 5 2
  // 6 9
  // 7 1
  // 8 5
  // 8 6
  // 9 7
  // 9 3
  // Answer: 3,3,3,0,0
  // ========================================
  // 1 2
  // 2 6
  // 2 3
  // 2 4
  // 3 1
  // 3 4
  // 4 5
  // 5 4
  // 6 5
  // 6 7
  // 7 6
  // 7 8
  // 8 5
  // 8 7
  // Answer: 3,3,2,0,0
  // ==========================================
  // 1 2
  // 2 3
  // 3 1
  // 3 4
  // 5 4
  // 6 4
  // 8 6
  // 6 7
  // 7 8
  // Answer: 3,3,1,1,0
  // =============================================
  // 1 2
  // 2 3
  // 3 1
  // 3 4
  // 5 4
  // 6 4
  // 8 6
  // 6 7
  // 7 8
  // 4 3
  // 4 6
  // Answer: 7,1,0,0,0
  // ===============================================
  // 1 2
  // 2 3
  // 2 4
  // 2 5
  // 3 6
  // 4 5
  // 4 7
  // 5 2
  // 5 6
  // 5 7
  // 6 3
  // 6 8
  // 7 8
  // 7 10
  // 8 7
  // 9 7
  // 10 9
  // 10 11
  // 11 12
  // 12 10
  // 6,3,2,1,0
}
