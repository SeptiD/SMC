import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		final GpioController gpio = GpioFactory.getInstance();
		Car myCar = Car.getCar(gpio);
		double distance;
		for(int i=0;i<=1;i++) {
			boolean checker = true;
			while (checker == true) {
				System.out.println("Going forwards");
				myCar.moveForward();

				Thread.sleep(100);

				if ((distance = myCar.getActualFrontDistance()) < 12.5 && myCar.getLastFrontDistance() < 12.5) {
					checker = false;
				}
				System.out.println("Distance" + distance);

				System.out.println("Stop car");
				myCar.stopCar();

				Thread.sleep(3000);

				System.out.println("Turn right");
				myCar.turnRight();
				Thread.sleep(5000);

				myCar.stopCar();

				Thread.sleep(3000);
			}
		}
	
		gpio.shutdown();
		
	}

}