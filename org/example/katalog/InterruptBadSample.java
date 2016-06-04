package org.example.katalog;

class HelloPrinter extends Thread {
  volatile boolean stop = false;

  @Override
  public void run() {
    while (!stop) {
      System.out.println("Hello world");
    }
  }
}

public class InterruptBadSample {
  public static void main(String[] args) {
    HelloPrinter t = new HelloPrinter();

    t.start();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    t.stop = true;

    try {
      t.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
