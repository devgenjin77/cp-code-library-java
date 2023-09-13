package library.atcoder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FenwickTreeTest {

  @Test
  void assign() {
    FenwickTree ft = new FenwickTree(10);
  }

  @Test
  void zero() {
    FenwickTree ft = new FenwickTree(0);
    assertEquals(0, ft.sum(0, 0));
  }

  @Test
  void naiveTest() {
    for (int n = 0; n <= 50; n++) {
      FenwickTree ft = new FenwickTree(n);
      for (int i = 0; i < n; i++) {
        ft.add(i, i * i);
      }
      for (int l = 0; l <= n; l++) {
        for (int r = l; r <= n; r++) {
          long sum = 0;
          for (int i = l; i < r; i++) {
            sum += i * i;
          }
          assertEquals(sum, ft.sum(l, r));
        }
      }
    }
  }

  @Test
  void naiveTestFromArray() {
    for (int n = 0; n <= 50; n++) {
      long[] array = new long[n];
      for (int i = 0; i < n; i++) {
        array[i] = i * i;
      }
      FenwickTree ft = new FenwickTree(array);
      for (int l = 0; l <= n; l++) {
        for (int r = l; r <= n; r++) {
          long sum = 0;
          for (int i = l; i < r; i++) {
            sum += i * i;
          }
          assertEquals(sum, ft.sum(l, r));
        }
      }
    }
  }

  @Test
  void testInvalid() {
    FenwickTree ft = new FenwickTree(10);
    assertThrows(AssertionError.class, () -> ft.add(-1, 0));
    assertThrows(AssertionError.class, () -> ft.add(10, 0));
    assertThrows(AssertionError.class, () -> ft.sum(-1, -1));
    assertThrows(AssertionError.class, () -> ft.sum(3, 2));
    assertThrows(AssertionError.class, () -> ft.sum(0, 11));
    assertThrows(AssertionError.class, () -> ft.sum(-1, 11));
  }

  @Test
  void bound() {
    FenwickTree ft = new FenwickTree(10);
    ft.add(3, Long.MAX_VALUE);
    ft.add(5, Long.MIN_VALUE);
    assertEquals(-1, ft.sum(0, 10));
    assertEquals(-1, ft.sum(3, 6));

    assertEquals(Long.MAX_VALUE, ft.sum(3, 4));
    assertEquals(Long.MIN_VALUE, ft.sum(4, 10));
  }
}