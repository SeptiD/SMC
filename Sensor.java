import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Sensor {
	private static int sensorCounter =0;
	private GpioPinDigitalOutput trig ;
	private final GpioPinDigitalInput echo ;
	private long startTime;
	private long totalTime;
	private double distance = 0;
	
	private Sensor(GpioController gpioInstance){
		trig= gpioInstance.provisionDigitalOutputPin(RaspiPin.GPIO_27,"Trig",PinState.LOW);
		echo= gpioInstance.provisionDigitalInputPin(RaspiPin.GPIO_25, PinPullResistance.PULL_DOWN);
	}
	
	public static Sensor getFrontSensor(GpioController gpioInstance){
		if(sensorCounter == 0){
			sensorCounter++;
			return new Sensor(gpioInstance);
		}
		else{
			return null;
		}
	}
	
	public double getDistance() throws InterruptedException{
		echo.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                if(event.getState().isHigh()){
                	startTime = System.nanoTime();
                }
                else{
                	totalTime = System.nanoTime()-startTime;
                	distance = 0.017*(totalTime*1000);
                	System.out.println("Distanta "+ distance);
                }
            }

        });
		
		trig.setState(PinState.LOW);
		Thread.sleep(1);
		trig.setState(PinState.HIGH);
		Thread.sleep((long) 0.01);
		trig.setState(PinState.LOW);
		
		return distance;
	}
}
