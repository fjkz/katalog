package org.example.katalog;

class BadSingleton1 {
  private static BadSingleton1 instance;

  // 他の人が勝手にインスタンスを作らないように private にする。
  private BadSingleton1() { };

  static BadSingleton1 getInstance() {
    // 同時に２つのスレッドが null チェックをするとユニークでなくなる。
    if (instance == null) {
      instance = new BadSingleton1();
    }
    return instance;
  }
}

class NotBadSingleton1 {
  private static NotBadSingleton1 instance;

  private NotBadSingleton1() { };

  // ユニーク性は保証されるが、同期コストがかかる。
  synchronized static NotBadSingleton1 getInstance() {
    if (instance == null) {
      instance = new NotBadSingleton1();
    }
    return instance;
  }
}


// Double-checked Locking
class BadSingleton2 {
  private static BadSingleton2 instance;

  private BadSingleton2() { };

  static BadSingleton2 getInstance() {
    // instance != null だとしても、インスタンスの初期化が終わっていない
    // 可能性が実はある。
    if (instance == null) {
      synchronized (BadSingleton2.class) {
        if (instance == null) {
          instance = new BadSingleton2();
        }
      }
    }
    return instance;
  }
}

// Initialization-on-demand Holder
class GoodSingleton1 {
  private static class Holder {
    private static GoodSingleton1 instance = new GoodSingleton1();
  }

  private GoodSingleton1() { };

  static GoodSingleton1 getInstance() {
    // Holder クラスが初めて呼ばれたときに、instance は生成され
    // ユニークであることは保証される。
    return Holder.instance;
  }
}

// 普通のシングルトン。
class GoodSingleton2 {
  private static GoodSingleton2 instance = new GoodSingleton2();

  private GoodSingleton2() { };

  static GoodSingleton2 getInstance() {
    return instance;
  }
}
