/**
 * @name Miriam Lee
 * @course Algorithms: Design and Analysis, Part 1
 * @date Aug 27, 2016
 * @question Your task is to code up and run the randomized contraction algorithm for the min cut
 *           problem and use it on the above graph to compute the min cut.
 * @score 1/1 points earned (100%) Quiz passed!
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * @author mimi
 */
public class KargerMinCut {
  /**
   * There are 200 vertices labeled 1 to 200. The first column in the file represents the vertex
   * label, and the particular row (other entries except the first column) tells all the vertices
   * that the vertex is adjacent to. So for example, the 6th row looks like :
   * "6 155 56  52  120 ......". This just means that the vertex with label 6 is adjacent to (i.e.,
   * shares an edge with) the vertices with labels 155,56,52,120,......,etc
   */
  static LinkedHashMap<Integer, ArrayList<Integer>> adj = new LinkedHashMap<Integer, ArrayList<Integer>>();// multiset
  /*
   * (HINT: Note that you'll have to figure out an implementation of edge contractions. Initially,
   * you might want to do this naively, creating a new graph from the old every time there's an edge
   * contraction. But you should also think about more efficient implementations.)
   */
  /**
   * Number of edges
   */
  private static int m;
  private static int minCut = Integer.MAX_VALUE;
  private static LinkedHashMap<Integer, ArrayList<Integer>> minAdj;

  private static void read() {
    try {
      Scanner in = new Scanner(new File("bin/_f370cd8b4d3482c940e4a57f489a200b_kargerMinCut.txt"));
      // Scanner in = new Scanner(System.in);

      while (in.hasNextLine()) {
        String s = in.nextLine();

        //// addEdge

        String[] ws = s.split("\\s");
        int v = Integer.valueOf(ws[0]);

        // add V
        ArrayList<Integer> vw;
        if (adj.containsKey(v))
          vw = adj.get(v);
        else {
          vw = new ArrayList<Integer>();
          adj.put(v, vw);
        }

        // add W
        for (int i = 1; i < ws.length; i++) {
          Integer w = Integer.valueOf(ws[i]);
          if (!adj.containsKey(v) || !adj.get(v).contains(w))
            m++;// if new edge, increase count

          vw.add(w);
        }

      }
      m /= 2;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  static int randomContraction(LinkedHashMap<Integer, ArrayList<Integer>> adjs, int edges) {
    // print(adjs);

    while (adjs.size() > 2) {
      // 1. pick a remaining edge (u,v) uniformly at random
      int vi = (int) (Math.random() * adjs.size());
      Integer v = (int) adjs.keySet().toArray()[vi];
      ArrayList<Integer> V = adjs.get(v);

      int wi = (int) (Math.random() * adjs.get(v).size());
      Integer w = (int) V.get(wi);
      ArrayList<Integer> W = adjs.get(w);

      // System.out.println(v + "-" + w);

      // 2. merge (or “contract” ) u and v into a single vertex

      // 'cut' edge
      V.remove(w);
      W.remove(v);
      edges--;

      // add remaining edges
      for (int u : W) {
        adjs.get(u).remove(w);
        if (u != v) {
          adjs.get(u).add(v);
          V.add(u);
        } else// 3. remove self-loops
          edges--;

        // System.out.println(w + " " + u);
      }

      adjs.remove(w);

      // print(adjs);
    }
    // 4. return cut represented by final 2 vertices.
    return edges;
  }

  private static void print(LinkedHashMap<Integer, ArrayList<Integer>> adjs) {
    System.out.print("[");
    for (int u : adjs.keySet()) {
      for (int v : adjs.get(u)) {
        if (v > u)
          System.out.print("(" + u + ", " + v + "), ");
      }

    }
    System.out.println("]");
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    read();
    // WARNING: As per the video lectures, please make sure to run the algorithm many times with
    // different random seeds, and remember
    // the smallest cut that you ever find.
    int t = 10000;// n - 1;
    while (t-- > 0) {
      LinkedHashMap<Integer, ArrayList<Integer>> map = new LinkedHashMap<Integer, ArrayList<Integer>>();
      for (Entry<Integer, ArrayList<Integer>> s : adj.entrySet()) {
        map.put(s.getKey(), new ArrayList<>(s.getValue()));
      }

      int cut = randomContraction(map, m);
      if (cut < minCut) {
        minCut = cut;
        minAdj = map;
      }
      System.out.println("cuts=" + cut);
    }
    System.out.println("result: " + minCut);
    System.out.print("cuts are ");
    print(minAdj);
  }

  // 1 2 3
  // 2 1 3 4
  // 3 1 2 4
  // 4 2 3
  //
  //
  // Test cases from previous sessions.
  //
  // 1 2 3 4 7
  // 2 1 3 4
  // 3 1 2 4
  // 4 1 2 3 5
  // 5 4 6 7 8
  // 6 5 7 8
  // 7 1 5 6 8
  // 8 5 6 7
  // expected result: 2
  // cuts are [(1,7), (4,5)]
  // (randomly permuting the adjacency list, should get same result):
  //
  // 1 4 2 7 3
  // 2 4 1 3
  // 3 1 2 4
  // 4 5 1 2 3
  // 5 8 7 6 4
  // 6 8 5 7
  // 7 6 8 5 1
  // 8 7 6 5
  // expected result: 2
  // cuts are [(1,7), (4,5)]
  //
  // --------------------------------
  //
  // 1 2 3 4
  // 2 1 3 4
  // 3 1 2 4
  // 4 1 2 3 5
  // 5 4 6 7 8
  // 6 5 7 8
  // 7 5 6 8
  // 8 5 6 7
  // expected result: 1
  // cut is [(4,5)]
  // (randomly permuting the adjacency list, should get same result):
  //
  // 1 3 4 2
  // 2 1 4 3
  // 3 1 2 4
  // 4 5 3 2 1
  // 5 4 8 6 7
  // 6 8 7 5
  // 7 5 8 6
  // 8 5 7 6
  // expected result: 1
  // cut is [(4,5)]
  //
  // --------------------------------
  //
  // 1 19 15 36 23 18 39
  // 2 36 23 4 18 26 9
  // 3 35 6 16 11
  // 4 23 2 18 24
  // 5 14 8 29 21
  // 6 34 35 3 16
  // 7 30 33 38 28
  // 8 12 14 5 29 31
  // 9 39 13 20 10 17 2
  // 10 9 20 12 14 29
  // 11 3 16 30 33 26
  // 12 20 10 14 8
  // 13 24 39 9 20
  // 14 10 12 8 5
  // 15 26 19 1 36
  // 16 6 3 11 30 17 35 32
  // 17 38 28 32 40 9 16
  // 18 2 4 24 39 1
  // 19 27 26 15 1
  // 20 13 9 10 12
  // 21 5 29 25 37
  // 22 32 40 34 35
  // 23 1 36 2 4
  // 24 4 18 39 13
  // 25 29 21 37 31
  // 26 31 27 19 15 11 2
  // 27 37 31 26 19 29
  // 28 7 38 17 32
  // 29 8 5 21 25 10 27
  // 30 16 11 33 7 37
  // 31 25 37 27 26 8
  // 32 28 17 40 22 16
  // 33 11 30 7 38
  // 34 40 22 35 6
  // 35 22 34 6 3 16
  // 36 15 1 23 2
  // 37 21 25 31 27 30
  // 38 33 7 28 17 40
  // 39 18 24 13 9 1
  // 40 17 32 22 34 38
  // expected result: 3
  //
  // ------------------------------
  //
  // 1 2 3 4 5
  // 2 3 4 1
  // 3 4 1 2
  // 4 1 2 3 8
  // 5 1 6 7 8
  // 6 7 8 5
  // 7 8 5 6
  // 8 4 6 5 7
  // mincut=2
}
