import java.util.ArrayList;

import com.pi4j.io.gpio.GpioController;

public class Car {
	private static int carCounter =0;
	private Wheel rightWheel;
	private Wheel leftWheel;
	private Sensor frontSensor;
	private double lastFrontDistance=0;
	private double actualFrontDistance=0;
	//private Sensor sideSensor;
	//private Camera frontCamera;
	
	private Car(GpioController gpioInstance){
		ArrayList<Wheel> wheels = Wheel.getBothWheels(gpioInstance);
		rightWheel = wheels.get(0);
		leftWheel = wheels.get(1);
		frontSensor = Sensor.getFrontSensor(gpioInstance);
	}
	
	public static Car getCar(GpioController gpioInstance){
		if(carCounter ==0){
			carCounter++;
			return new Car(gpioInstance);	
		}
		else{
			return null;
		}
	}
	
	public void moveForward(){
		rightWheel.moveForward();
		leftWheel.moveForward();
	}
	
	public void moveBackward(){
		rightWheel.moveBackward();
		leftWheel.moveBackward();
	}
	
	public void stopCar(){
		rightWheel.stopWheel();
		leftWheel.stopWheel();
	}
	
	public void turnRight(){
		rightWheel.moveBackward();
		leftWheel.moveForward();
	}
	
	public void turnLeft(){
		rightWheel.moveForward();
		leftWheel.moveBackward();
	}
	
	public double getLastFrontDistance(){
		return lastFrontDistance;
	}
	
	public double getActualFrontDistance(){
		lastFrontDistance = actualFrontDistance;
		try {
			actualFrontDistance = frontSensor.getDistance();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actualFrontDistance;
	}
	
}
