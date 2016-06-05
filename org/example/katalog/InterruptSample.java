package org.example.katalog;

public class InterruptSample {
  public static void main(String[] args) {
    Thread t = new Thread() {
      @Override
      public void run() {
        // interruptされるまで、"Hello world"を出力し続ける。
        // なお、isInterrupted()は呼ぶとフラグがクリアされてfalseになる。
        while (!isInterrupted()) {
          System.out.println("Hello world");
        }
      }
    };

    t.start();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // interruptするとスレッドのisInterrupted()のフラグが立つ。
    t.interrupt();

    // interruptしてもまだスレッドが止まっているわけではない。
    // スレッドが止まるまで待つ。
    try {
      t.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
