package designPattern.futurePattern.jdk;

import java.util.concurrent.Callable;

/**
 * DESCRIPTION：TODO
 *
 * @author zhangyang 2018/4/28 08:37
 */
public class RealData implements Callable<String> {
	
	private String para;
	
	public RealData(String para) {
		this.para = para;
	}
	
	@Override
	public String call() throws Exception {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 50; i++) {
			sb.append(para);
			Thread.sleep(100);
		}
		
		return sb.toString();
	}
}
