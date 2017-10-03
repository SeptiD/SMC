import java.util.ArrayList;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Wheel {
	
	private static int wheelCounter =0;
	private GpioPinDigitalOutput motorA;
	private GpioPinDigitalOutput motorB;
	private GpioPinDigitalOutput motorE;
	
	private Wheel(GpioController gpioInstance){
		
		if(wheelCounter ==0){
			//give right wheel
			motorA = gpioInstance.provisionDigitalOutputPin(RaspiPin.GPIO_04,"MotorRa",PinState.LOW);
			motorB = gpioInstance.provisionDigitalOutputPin(RaspiPin.GPIO_05,"MotorRb",PinState.LOW);
			motorE = gpioInstance.provisionDigitalOutputPin(RaspiPin.GPIO_06,"MotorRe",PinState.HIGH);			
			
		}
		else if(wheelCounter == 1){
			//give left wheel
			motorA = gpioInstance.provisionDigitalOutputPin(RaspiPin.GPIO_12,"MotorLa",PinState.LOW);
			motorB = gpioInstance.provisionDigitalOutputPin(RaspiPin.GPIO_13,"MotorLb",PinState.LOW);
			motorE = gpioInstance.provisionDigitalOutputPin(RaspiPin.GPIO_14,"MotorLe",PinState.HIGH);
		}
	}
	
	public static ArrayList<Wheel> getBothWheels(GpioController gpioInstance){
		if(wheelCounter==0){
			ArrayList<Wheel> tempWheelList = new ArrayList<Wheel>();
			tempWheelList.add(new Wheel(gpioInstance));
			wheelCounter++;
			tempWheelList.add(new Wheel(gpioInstance));
			return tempWheelList;
		}
		else{
			return null;
		}
	}

	public void moveForward(){
		motorA.setState(PinState.HIGH);
		motorB.setState(PinState.LOW);
		motorE.setState(PinState.HIGH);
	}
	
	public void moveBackward(){
		motorA.setState(PinState.LOW);
		motorB.setState(PinState.HIGH);
		motorE.setState(PinState.HIGH);
	}
	
	public void stopWheel(){
		motorE.setState(PinState.LOW);
	}

}
