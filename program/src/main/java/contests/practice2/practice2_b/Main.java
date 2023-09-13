/*
 * AtCoder Library Practice Contest
 * B - Fenwick Tree
 * https://atcoder.jp/contests/practice2/tasks/practice2_b
 *
 * verified:
 * - https://atcoder.jp/contests/practice2/submissions/45511307
 *
 */

package contests.practice2.practice2_b;

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
    final long[] arr_a = new long[n];
    final StringTokenizer st_a = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr_a[i] = Long.parseLong(st_a.nextToken());
    }
    final FenwickTree ft = new FenwickTree(arr_a);
    final PrintWriter pw = new PrintWriter(System.out);
    for (int cnt = 0; cnt < q; cnt++) {
      StringTokenizer st_q = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st_q.nextToken());
      switch (t) {
        case 0:
          int p = Integer.parseInt(st_q.nextToken());
          long x = Long.parseLong(st_q.nextToken());
          ft.add(p, x);
          break;
        case 1:
          int l = Integer.parseInt(st_q.nextToken());
          int r = Integer.parseInt(st_q.nextToken());
          pw.println(ft.sum(l, r));
          break;
        default:
          break;
      }
    }
    pw.close();
    br.close();
  }

  //FenwickTreeライブラリ
  static class FenwickTree {

    final int _n;
    final long[] data;

    public FenwickTree(int n) {
      this._n = n;
      this.data = new long[n];
    }

    public FenwickTree(long[] data) {
      _n = data.length;
      this.data = new long[this._n];
      System.arraycopy(data, 0, this.data, 0, _n);
      for (int i = 1; i <= _n; i++) {
        int p = i + (-i & i);
        if (p <= _n) {
          this.data[p - 1] += this.data[i - 1];
        }
      }
    }

    public void add(int p, long x) {
      assert (0 <= p && p < _n);
      p++;
      while (p <= _n) {
        data[p - 1] += x;
        p += -p & p;
      }
    }

    public long sum(int l, int r) {
      assert (0 <= l && l <= r && r <= _n);
      return sum(r) - sum(l);
    }

    private long sum(int r) {
      long s = 0;
      while (r > 0) {
        s += data[r - 1];
        r -= -r & r;
      }
      return s;
    }
  }
}
