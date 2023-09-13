/*
 * AtCoder Library Practice Contest
 * A - Disjoint Set Union
 * https://atcoder.jp/contests/practice2/tasks/practice2_a
 *
 * verified:
 * - https://atcoder.jp/contests/practice2/submissions/45508432
 *
 */

package contests.practice2.practice2_a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final StringTokenizer st = new StringTokenizer(br.readLine());
    final int n = Integer.parseInt(st.nextToken());
    final int q = Integer.parseInt(st.nextToken());
    final DisjointSetUnion dsu = new DisjointSetUnion(n);
    final PrintWriter pw = new PrintWriter(System.out);
    for (int cnt = 0; cnt < q; cnt++) {
      StringTokenizer st_q = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st_q.nextToken());
      int u = Integer.parseInt(st_q.nextToken());
      int v = Integer.parseInt(st_q.nextToken());
      switch (t) {
        case 0:
          dsu.merge(u, v);
          break;
        case 1:
          pw.println(dsu.isSame(u, v) ? 1 : 0);
          break;
        default:
          break;
      }
      if (t == 0) {
        dsu.merge(u, v);
      } else if (t == 1) {

      }
    }
    pw.close();
    br.close();
  }

  //DisjointSetUnionライブラリ
  static class DisjointSetUnion {

    private final int _n;

    private final int[] parent_or_size;

    public DisjointSetUnion(int n) {
      this._n = n;
      this.parent_or_size = new int[n];
      java.util.Arrays.fill(parent_or_size, -1);
    }

    public int merge(int a, int b) {
      assert (0 <= a && a < _n);
      assert (0 <= b && b < _n);
      int x = leader(a), y = leader(b);
      if (x == y) {
        return x;
      }
      if (-parent_or_size[x] < -parent_or_size[y]) {
        int temp = x;
        x = y;
        y = temp;
      }
      parent_or_size[x] += parent_or_size[y];
      parent_or_size[y] = x;
      return x;
    }

    public boolean isSame(int a, int b) {
      assert (0 <= a && a < _n);
      assert (0 <= b && b < _n);
      return leader(a) == leader(b);
    }

    public int leader(int a) {
      assert (0 <= a && a < _n);
      if (parent_or_size[a] < 0) {
        return a;
      } else {
        return parent_or_size[a] = leader(parent_or_size[a]);
      }
    }

    public int size(int a) {
      assert (0 <= a && a < _n);
      return -parent_or_size[leader(a)];
    }

    public java.util.List<java.util.List<Integer>> groups() {
      int k = 0;
      java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
      java.util.Map<Integer, Integer> map_leader = new java.util.HashMap<>();
      for (int i = 0; i < _n; i++) {
        int lead = leader(i);
        if (!map_leader.containsKey(lead)) {
          map_leader.put(lead, k++);
          result.add(new java.util.ArrayList<>());
        }
        result.get(map_leader.get(lead)).add(i);
      }
      return result;
    }
  }
}
