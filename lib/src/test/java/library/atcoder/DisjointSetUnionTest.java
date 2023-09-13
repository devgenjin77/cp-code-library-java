package library.atcoder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DisjointSetUnionTest {

  @Test
  void zero() {
    DisjointSetUnion uf = new DisjointSetUnion(0);
    assertEquals(0, uf.groups().size());
  }

  @Test
  void simple() {
    DisjointSetUnion uf = new DisjointSetUnion(2);
    assertFalse(uf.isSame(0, 1));
    int x = uf.merge(0, 1);
    assertEquals(x, uf.leader(0));
    assertEquals(x, uf.leader(1));
    assertTrue(uf.isSame(0, 1));
    assertEquals(2, uf.size(0));
  }

  @Test
  void line() {
    int n = 500000;
    DisjointSetUnion uf = new DisjointSetUnion(n);
    for (int i = 0; i < n - 1; i++) {
      uf.merge(i, i + 1);
    }
    assertEquals(n, uf.size(0));
    assertEquals(1, uf.groups().size());
  }

  @Test
  void lineReverse() {
    int n = 500000;
    DisjointSetUnion uf = new DisjointSetUnion(n);
    for (int i = n - 2; i >= 0; i--) {
      uf.merge(i, i + 1);
    }
    assertEquals(n, uf.size(0));
    assertEquals(1, uf.groups().size());
  }
}