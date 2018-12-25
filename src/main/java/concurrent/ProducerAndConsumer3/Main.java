package concurrent.ProducerAndConsumer3;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * DESCRIPTION：TODO
 *
 * @author zhangyang 2018/4/24 09:32
 */
public class Main {
    //建立缓冲区
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        PCDataFactory factory = new PCDataFactory();

        int bufferSize = 1024;
        Disruptor<PCData> disruptor = new Disruptor<PCData>(factory, bufferSize, es, ProducerType.MULTI,
                new BlockingWaitStrategy());

        disruptor.handleEventsWithWorkerPool(new Consumer(),new Consumer(),new Consumer(),new Consumer());
        disruptor.start();
	
	    RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
	    Producer producer = new Producer(ringBuffer);
	    ByteBuffer bb = ByteBuffer.allocate(8);
	    
	    for(long l=0;true;l++){
	    	bb.putLong(0,l);
	    	producer.pushData(bb);
	    	Thread.sleep(100);
		    System.out.println("add data "+l);
	    }
    }

}
