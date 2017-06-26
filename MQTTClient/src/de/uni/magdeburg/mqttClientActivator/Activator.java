package de.uni.magdeburg.mqttClientActivator;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator, MqttCallback {

    private MqttClient mqttClient;

    private static final String broker = "m2m.eclipse.org";
    private static final String protocol = "tcp";
    private static final int port = 1883;
    private static final String clientId = UUID.randomUUID().toString();

    @Override
    public void start(final BundleContext Context) throws Exception {
        try {
            final String url = protocol + broker + ":" + port;
            mqttClient = new MqttClient(url, clientId);

            final MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setConnectionTimeout(60);
            connOpts.setKeepAliveInterval(60);
            mqttClient.connect(connOpts);
            mqttClient.setCallback(this);
            mqttClient.subscribe("Java/Paho/OSGi");
        } catch (final MqttException me) {
            me.printStackTrace();
        }
    }

    @Override
    public void connectionLost(final Throwable cause) {
        // no need
    }

    @Override
    public void messageArrived(final String topic, final MqttMessage message) throws Exception {
        System.out.println(message);
    }

    @Override
    public void deliveryComplete(final IMqttDeliveryToken token) {
    }

    @Override
    public void stop(final BundleContext Context) throws Exception {
        if (mqttClient != null) {
            mqttClient.close();
        }
    }
}
