package Simon.ButtonMatrix;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Listener {

	public static void main(String[] args) throws InterruptedException {
		
		final GpioController gpio = GpioFactory.getInstance();
		
		char[][] matrix = {	{'.', '0', '#', '/'},
							{'7', '8', '9', '*'},
							{'4', '5', '6', '-'},
							{'1', '2', '3', '+'}};
		
		//Rows
		final GpioPinDigitalInput row1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_21, PinPullResistance.PULL_UP);
		final GpioPinDigitalInput row2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_22, PinPullResistance.PULL_UP);
		final GpioPinDigitalInput row3 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_23, PinPullResistance.PULL_UP);
		final GpioPinDigitalInput row4 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_24, PinPullResistance.PULL_UP);
		GpioPinDigitalInput[] row = {row1, row2, row3, row4};
		
		//Columns
		final GpioPinDigitalOutput column1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, PinState.HIGH);
		final GpioPinDigitalOutput column2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, PinState.HIGH);
		final GpioPinDigitalOutput column3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, PinState.HIGH);
		final GpioPinDigitalOutput column4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, PinState.HIGH);
		GpioPinDigitalOutput[] column = {column1, column2, column3, column4};
		
		//Shutdown options
		row1.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		row2.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		row3.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		row4.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		
		column1.setShutdownOptions(true, PinState.LOW);
		column2.setShutdownOptions(true, PinState.LOW);
		column3.setShutdownOptions(true, PinState.LOW);
		column4.setShutdownOptions(true, PinState.LOW);
		
		while (true) {
			
			for (int i = 0; i < 4; i++) {
				
				column[i].low();
				
				for (int j = 0; j < 4; j++) {
					
					if(row[j].isLow()) {
						
						System.out.println(matrix[j][i]);
						while (row[j].isLow()) {
							;
						}
						
					}
					
				}
				
				column[i].high();
				
				
			}
			
		}
		
	}
	
}
