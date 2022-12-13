package com.example.secretcommunicationdevice.communication

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class UdpClient(address: String, port: Int) {
    private val _connection: DatagramSocket
    private val _address: InetAddress
    private val _port: Int

    init {
        _port = port
        _address = InetAddress.getByName(address);
        _connection = DatagramSocket(port, _address)
    }

    fun sendPacket(str: String, destinationAddress: String, destinationPort: Int) {
        val _destinationAddress = InetAddress.getByName(destinationAddress);
        val packet = DatagramPacket(str.toByteArray(), str.length, _destinationAddress, destinationPort);
        _connection.send(packet)
    }

    fun recv(bufferSize: Int): String {
        val buffer = ByteArray(bufferSize)
        val packet = DatagramPacket(buffer, buffer.size)
        _connection.receive(packet)
        return packet.data.toString(Charsets.UTF_8)
    }

    fun close() {
        _connection.close()
    }
}