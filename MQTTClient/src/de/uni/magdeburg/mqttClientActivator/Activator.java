package de.uni.magdeburg.mqttClientActivator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;


public class Activator implements BundleActivator {
		
	public void start(BundleContext Context) throws Exception {
		System.out.println("Sample bundle starting");
		MqttClient mqttClient = null;
		
		try {mqttClient = new MqttClient (
					"ifatnode.et.uni-magedburg.de",
					"Devang/Thesis/testData");
		
			 MqttConnectOptions connOpts = new MqttConnectOptions();
			    connOpts.setCleanSession(true);			// clean the message session once disconnected
			    mqttClient.connect(connOpts);
			    System.out.println("Connected");}
		
		catch (MqttException me){
		}
	}
	
	public void connectionLost(Throwable cause) {
	    // TODO Auto-generated method stub

	}

	public void messageArrived(String topic, MqttMessage message)
	        throws Exception {
	 System.out.println(message);   
	 System.out.println(11);
	}
	
	public void deliveryComplete(IMqttDeliveryToken token) {
		 System.out.println(1);
	}
	
	public void stop(BundleContext Context) throws Exception {
		System.out.println("Sample bundle stopping");
	}
}
