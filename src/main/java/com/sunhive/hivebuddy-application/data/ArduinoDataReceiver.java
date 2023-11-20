package com.sunhive.hivebuddy.data;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
public class ArduinoDataReceiver implements SerialPortDataListener {
    private StringBuilder receivedDataBuffer = new StringBuilder();

    @Override
    public int getListeningEvents(){
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
            byte[] receivedData = serialPortEvent.getReceivedData();
            String newDataChunk = new String(receivedData);

            // Append the new data chunk to the buffer
            receivedDataBuffer.append(newDataChunk);

            // Check if the buffer contains a complete message (ends with a newline)
            if (receivedDataBuffer.toString().endsWith("\n")) {
                // Process the complete message
                String completeMessage = receivedDataBuffer.toString().trim();
                System.out.println("Received Data: " + completeMessage);

                // Clear the buffer for the next message
                receivedDataBuffer.setLength(0);
            }
        }
    }
}
