/**
 * @name Miriam Lee
 * @course Algorithms: Design and Analysis, Part 1
 * @date Sep 2, 2016 5:23:33 PM
 * @question In this programming problem you'll code up Dijkstra's shortest-path algorithm. Your
 *           task is to run Dijkstra's shortest-path algorithm on this graph, using 1 (the first
 *           vertex) as the source vertex, and to compute the shortest-path distances between 1 and
 *           every other vertex of the graph. If there is no path between a vertex v and vertex 1,
 *           we'll define the shortest-path distance between 1 and v to be 1000000.
 * @score 1/1 points earned (100%)
 */
import java.io.File;
import java.util.Scanner;

import edu.princeton.cs.algs4.DijkstraUndirectedSP;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author mimi
 *
 */
public class Dijkstra {
  private static EdgeWeightedGraph newEdgeWeightedGraph(Scanner in, int V) {
    EdgeWeightedGraph ewd = new EdgeWeightedGraph(V);

    // The file contains an adjacency list representation of an undirected weighted graph with 200
    // vertices labeled 1 to 200. Each row consists of the node tuples that are adjacent to that
    // particular vertex along with the length of that edge (vertex,length).

    while (in.hasNext()) {
      int v = in.nextInt() - 1;
      String s = in.nextLine();

      String[] edges = s.split("\\D");
      for (int i = 0; i < edges.length; i++) {
        if (edges[i].isEmpty())
          continue;

        int w = Integer.valueOf(edges[i]) - 1;
        if (v < 0 || v >= V)
          throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V - 1));
        if (w < 0 || w >= V)
          throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V - 1));

        i++;
        double weight = Integer.valueOf(edges[i]);

        ewd.addEdge(new Edge(v, w, weight));
      }
    }
    return ewd;
  }

  private static void print(int V, DijkstraUndirectedSP sp, int s) {
    System.out.println("output:");
    System.out.println("w l [path]");
    // You should report the shortest-path distances to the following ten vertices, in order:
    // 7,37,59,82,99,115,133,165,188,197.
    int t[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 7, 37, 59, 82, 99, 115, 133, 165, 188, 197 };

    StringBuilder sb = new StringBuilder();

    // print shortest path
    for (int i = 0; i < t.length && t[i] <= V; i++) {
      if (sp.hasPathTo(t[i] - 1)) {
        StdOut.printf("%d %.0f  [", t[i], sp.distTo(t[i] - 1));
        sb.append((int) sp.distTo(t[i] - 1)).append(",");
        for (Edge e : sp.pathTo(t[i] - 1)) {
          StdOut.print(e.either() + 1 + "-" + (e.other(e.either()) + 1) + ",");
        }
        StdOut.println("]");
      } else {// TODO If there is no path between a vertex v and vertex 1, we'll define the
              // shortest-path distance between 1 and v to be 1000000.

        StdOut.printf("%d %d no path\n", s, t[i]);
      }
    }

    System.out.println(sb.toString());
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    Scanner in;
    try {
      in = new Scanner(new File("bin/_dcf1d02570e57d23ab526b1e33ba6f12_dijkstraData.txt"));
      int V = 200;

      // in = new Scanner(System.in);
      // int V = 8;

      EdgeWeightedGraph G = newEdgeWeightedGraph(in, V);
      // using 1 (the first vertex) as the source vertex, and to compute the shortest-path distances
      // between 1 and every other vertex of the graph.
      int s = 0;
      // compute shortest paths
      DijkstraUndirectedSP sp = new DijkstraUndirectedSP(G, s);
      print(G.V(), sp, s);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

  }
  // PA#5 Test Cases
  // input
  // 1 2,1 8,2
  // 2 1,1 3,1
  // 3 2,1 4,1
  // 4 3,1 5,1
  // 5 4,1 6,1
  // 6 5,1 7,1
  // 7 6,1 8,1
  // 8 7,1 1,2
  // output:
  // 1 0 []
  // 2 1 [2]
  // 3 2 [2, 3]
  // 4 3 [2, 3, 4]
  // 5 4 [2, 3, 4, 5]
  // 6 4 [8, 7, 6]
  // 7 3 [8, 7]
  // 8 2 [8]

  // 1 2,3 3,2
  //
  // 2 4,4
  //
  // 3 2,1 4,2 5,3
  //
  // 4 5,2 6,1
  //
  // 5 6,2
  //
  // Distances from 1 to all other nodes:
  //
  // 1:0 2:3 3:2 4:4 5:5 6:5
}
