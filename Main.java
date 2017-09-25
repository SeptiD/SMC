import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput Motor1A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,"Motor1a",PinState.LOW);
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

		gpio.shutdown();
	}

}
