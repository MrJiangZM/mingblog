package com.ming.blog.one;

/**
 * 测试wait  notify notifyAll 等方法
 *
 * @author Jiang Zaiming
 * @date 2020/1/14 3:43 下午
 */
public class MyThreadObject {

    public static void main(String[] args) {
        Object object = new Object();
        Shuzi shuzi = new Shuzi(object);
        Zimu zimu = new Zimu(object);
        Thread t = new Thread(shuzi);
        t.setName("shuzi");
        Thread t1 = new Thread(zimu);
        t1.setName("zimu");
        t.start();//数字线程先运行
        t1.start();
        System.out.println("++++++++++++++++++");
    }
}

class Shuzi implements Runnable {
    private Object object;

    //声明类的引用
    public Shuzi(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {//上锁

            for (int i = 1; i < 53; i++) {
                System.out.print(i + ",");
                System.out.println("");
                if (i % 2 == 0) {
                    object.notifyAll();//唤醒其它争夺权限的线程
                    try {
                        object.wait();//释放锁，进入等待
                        System.out.println("数字打印类打全打印当前对象拥有对象锁的线程    " + Thread.currentThread().getName());//输出当前拥有锁的线程名称
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

class Zimu implements Runnable {
    private Object object;

    public Zimu(Object object) {

        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            for (int j = 65; j < 91; j++) {
                char c = (char) j;
                System.out.print(c);
                System.out.println("");
                object.notifyAll();//唤醒其它争夺权限的线程
                try {
                    if (j != 90) {
                        object.wait();//释放锁，进入等待
                        System.out.println("字母打印类打全打印当前对象拥有对象锁的线程" + Thread.currentThread().getName());//输出当前拥有锁的线程名称
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
