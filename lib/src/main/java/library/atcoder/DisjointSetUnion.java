/*
 * ac-library
 * DisjointSetUnion
 *
 * https://github.com/atcoder/ac-library/blob/master/document_ja/dsu.md
 *
 */

package library.atcoder;

//DisjointSetUnionライブラリ
public class DisjointSetUnion {

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
