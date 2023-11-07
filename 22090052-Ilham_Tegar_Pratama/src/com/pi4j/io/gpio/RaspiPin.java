/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi4j.io.gpio;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class RaspiPin {
    public static void main(String[] args) {
        // Mendapatkan objek pin GPIO berdasarkan nomornya
        Pin pin17 = RaspiPin.GPIO_17;
        Pin pin18 = RaspiPin.GPIO_18;

        System.out.println("Nomor pin 17: " + pin17.getName());
        System.out.println("Nomor pin 18: " + pin18.getName());
    }
}
