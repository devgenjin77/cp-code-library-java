/*
 * ac-library
 * FenwickTree
 *
 * https://github.com/atcoder/ac-library/blob/master/document_ja/fenwicktree.md
 *
 */

package library.atcoder;

//FenwickTreeライブラリ
public class FenwickTree {

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
    assert(0 <= l && l <= r && r <= _n);
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
