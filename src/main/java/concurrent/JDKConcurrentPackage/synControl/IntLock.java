package concurrent.JDKConcurrentPackage.synControl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * DESCRIPTION：中断响应
 *
 * @author zhangyang 2017/12/11 20:16
 */
public class IntLock implements Runnable {
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	int lock;
	
	/**
	 * 控制加锁顺序，方便构造死锁
	 * @param lock
	 */
	public IntLock(int lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		try {
			if(lock==1){
				lock1.lockInterruptibly();
				try {
					Thread.sleep(500);
				}catch (InterruptedException e){
					e.printStackTrace();
				}
				lock2.lockInterruptibly();
				
			}else{
				lock2.lockInterruptibly();
				try {
					Thread.sleep(500);
				}catch (InterruptedException e){
					e.printStackTrace();
				}
				lock1.lockInterruptibly();
			}
		}catch (InterruptedException e){
			e.printStackTrace();
		}finally {
			if(lock1.isHeldByCurrentThread()){
				lock1.unlock();
			}
			if(lock2.isHeldByCurrentThread()){
				lock2.unlock();
			}
			
			System.out.println(Thread.currentThread().getId()+"：线程退出");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntLock r1 = new IntLock(1);
		IntLock r2 = new IntLock(2);
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();t2.start();
		Thread.sleep(1000);
		
		//中断其中一个线程,这里t1真正执行完毕 t2线程放弃其任务而退出, 释放资源
		 t2.interrupt();
	}
}
