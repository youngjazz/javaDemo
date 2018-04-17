package designPattern.observerPattern.impl;

import designPattern.observerPattern.Observer;
import designPattern.observerPattern.Subject;

/**
 * DESCRIPTION：TODO
 *
 * @author zhangyang 2018/3/6 13:53
 */
public class CurrentConditionsDisplay implements Observer {
	
	private Subject weatherData;
	
	public CurrentConditionsDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}
	
	@Override
	public void update(float temp, float humidity, float pressure) {
		System.out.println("CurrentConditionsDisplay.update"+temp+" "+humidity+" "+pressure);
	}
}
