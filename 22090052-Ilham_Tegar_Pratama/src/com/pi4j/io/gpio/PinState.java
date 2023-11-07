/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi4j.io.gpio;

import com.pi4j.io.gpio.PinState;

public class PinState {
    public static void main(String[] args) {
        // Menggunakan PinState HIGH
        PinState tinggi = PinState.HIGH;
        if (tinggi.isHigh()) {
            System.out.println("Keadaan tinggi (HIGH)");
        } else {
            System.out.println("Bukan keadaan tinggi (HIGH)");
        }

        // Menggunakan PinState LOW
        PinState rendah = PinState.LOW;
        if (rendah.isLow()) {
            System.out.println("Keadaan rendah (LOW)");
        } else {
            System.out.println("Bukan keadaan rendah (LOW)");
        }
    }
}
