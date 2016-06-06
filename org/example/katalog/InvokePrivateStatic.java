package org.example.katalog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Add {
  private static int add(int a, int b) {
    return a + b;
  }
}

public class InvokePrivateStatic {

  static int invokeAdd(int a, int b) {
    Method m = null;
    try {
      // getMethod では private メソッドは取得できない。
      m = Add.class.getDeclaredMethod(
          "add", int.class, int.class);
    } catch (NoSuchMethodException | SecurityException e) {
      throw new RuntimeException(e);
    }

    // メソッドにアクセスできるようにする。
    // false の場合は、invoke した際に IllegalAccessException となる。
    m.setAccessible(true);

    int r;
    try {
      // インスタンスメソッドの場合は、第一引数はインスタンスとなる。
      r = (int)m.invoke(null, 1, 1);
    } catch (IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      throw new RuntimeException(e);
    }

    return r;
  }

  public static void main(String[] args) {
    int r = invokeAdd(1, 1);
    System.out.println(r);
  }
}
