import java.sql.Time;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Main {
	public static long startTime;
	public static long totalTime;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		final GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput trig = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27,"Trig",PinState.LOW);
		final GpioPinDigitalInput echo = gpio.provisionDigitalInputPin(RaspiPin.GPIO_25, PinPullResistance.PULL_DOWN);
		
		//long startTime;
		
		 echo.addListener(new GpioPinListenerDigital() {
	            @Override
	            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
	                // display pin state on console
	                if(event.getState().isHigh())
	                {
	                	Main.startTime = System.currentTimeMillis();
	                }
	                else
	                {
	                	Main.totalTime = System.currentTimeMillis()-Main.startTime;
	                	long distance = 17150*(Main.totalTime/1000);
	                	System.out.println("Distanta "+ distance);
	                }
	            }

	        });

		
		int counter = 0;
		while(counter<20)
		{
			trig.setState(PinState.LOW);
			Thread.sleep(1);
			trig.setState(PinState.HIGH);
			Thread.sleep((long) 0.01);
			trig.setState(PinState.LOW);
			
			Thread.sleep(100);
		}
		
		
		/*GpioPinDigitalOutput Motor1A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,"Motor1a",PinState.LOW);
		GpioPinDigitalOutput Motor1B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05,"Motor1b",PinState.LOW);
		GpioPinDigitalOutput Motor1E = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06,"Motor1e",PinState.HIGH);
		
		GpioPinDigitalOutput Motor2A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12,"Motor2a",PinState.LOW);
		GpioPinDigitalOutput Motor2B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13,"Motor2b",PinState.LOW);
		GpioPinDigitalOutput Motor2E = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14,"Motor2e",PinState.HIGH);
		
		System.out.println("Going forwards");
		Motor1E.setState(PinState.HIGH);
		Motor1A.setState(PinState.HIGH);
		Motor1B.setState(PinState.LOW);
		
		Motor2E.setState(PinState.HIGH);
		Motor2A.setState(PinState.HIGH);
		Motor2B.setState(PinState.LOW);
		
		
		Thread.sleep(5000);
		
		System.out.println("Going backwords");
		Motor1A.setState(PinState.LOW);
		Motor1B.setState(PinState.HIGH);
		Motor1E.setState(PinState.HIGH);
		
		Motor2A.setState(PinState.LOW);
		Motor2B.setState(PinState.HIGH);
		Motor2E.setState(PinState.HIGH);

		Thread.sleep(5000);
		
		
		System.out.println("Now stop");
		Motor1E.setState(PinState.LOW);
		Motor2E.setState(PinState.LOW);
*/
		gpio.shutdown();
		
	}

}