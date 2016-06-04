package org.example.katalog;

public class InterruptSample {
  public static void main(String[] args) {
    Thread t = new Thread() {
      @Override
      public void run() {
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

    t.interrupt();

    try {
      t.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
