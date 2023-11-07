/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartHome.ProtokolKomunikasi;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class CommunicationProtocol {
    public static void main(String[] args) {
        String brokerUrl = "tcp://mqtt-broker-url:1883"; // Ganti dengan URL broker MQTT Anda
        String clientId = "DeviceClient";
        String topic = "smart_home/commands"; // Ganti dengan topik yang sesuai

        try {
            MqttClient client = new MqttClient(brokerUrl, clientId, new MemoryPersistence());
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            // Koneksikan ke broker MQTT
            System.out.println("Menghubungkan ke broker: " + brokerUrl);
            client.connect(connOpts);
            System.out.println("Terhubung ke broker");

            // Kirim pesan MQTT
            String message = "Turn on the lights"; // Pesan yang akan dikirim
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(0); // Quality of Service (0, 1, 2)

            client.publish(topic, mqttMessage);

            // Tunggu beberapa saat sebelum memutuskan koneksi (opsional)
            Thread.sleep(1000);

            // Putuskan koneksi MQTT
            client.disconnect();
            System.out.println("Terputus dari broker MQTT");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
