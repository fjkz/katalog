package org.example.katalog;

public class SynchronizedClass {
  static int count = 0;

  public void run() {
    Thread[] ts = new Thread[5];
    for (int i = 0; i < ts.length; i++) {

      // count変数の値をプリントして、カウントアップすることを10回繰り返す。
      Thread t = new Thread() {
        @Override
        public void run() {
          for (int j = 0; j < 10; j++) {

            // SynchronizedClass クラスのロックを取る。
            synchronized (SynchronizedClass.class) {
              System.out.println(count);
              count++;
            }

          }
        }
      };

      t.start();
      ts[i] = t;
    }

    for (Thread t : ts) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    new SynchronizedClass().run();
  }
}
