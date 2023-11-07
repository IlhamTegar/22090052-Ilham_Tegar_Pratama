/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartHome.KendaliSentral;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.event.GpioPinListenerAnalog;
import com.pi4j.io.gpio.event.GpioPinAnalogValueChangeEvent;
import com.pi4j.io.gpio.trigger.GpioPulseStateTrigger;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class CentralController {
    public static void main(String[] args) {
        // Inisialisasi GpioController
        GpioController gpio = GpioFactory.getInstance();

        // Inisialisasi pin GPIO untuk lampu dan pintu
        GpioPinDigitalOutput light = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Light", PinState.LOW);
        GpioPinDigitalOutput door = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Door", PinState.LOW);
        
        // Inisialisasi MQTT client
        String brokerUrl = "tcp://mqtt-broker-url:1883"; // Ganti dengan URL broker MQTT Anda
        String clientId = "CentralController";
        MemoryPersistence persistence = new MemoryPersistence();
        
        try {
            MqttClient client = new MqttClient(brokerUrl, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            // Koneksikan ke broker MQTT
            System.out.println("Menghubungkan ke broker: " + brokerUrl);
            client.connect(connOpts);
            System.out.println("Terhubung ke broker");

            // Berlangganan topik MQTT
            String topic = "smart_home/commands";
            client.subscribe(topic, (topic1, message) -> {
                String command = new String(message.getPayload());
                System.out.println("Perintah diterima: " + command);

                // Handle perintah sesuai kebutuhan Anda
                if (command.equals("light_on")) {
                    light.high(); // Hidupkan lampu
                } else if (command.equals("light_off")) {
                    light.low(); // Matikan lampu
                } else if (command.equals("door_open")) {
                    door.high(); // Buka pintu
                } else if (command.equals("door_close")) {
                    door.low(); // Tutup pintu
                }
            });

            // Tunggu hingga program dihentikan (Ctrl+C atau lainnya)
            System.out.println("Tekan Ctrl+C untuk menghentikan...");
            Thread.sleep(Long.MAX_VALUE);
        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Matikan GPIO dan lepaskan sumber daya
            gpio.shutdown();
        }
    }
}
