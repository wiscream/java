package com.sxt.st12.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 发送端
 */
public class TalkSend implements Runnable{
    private DatagramSocket client;
    private BufferedReader reader;
    private String toIp;
    private int toPort;

    public TalkSend(int port,String toIp,int toPort) {
        this.toIp=toIp;
        this.toPort=toPort;
        try {
            client=new DatagramSocket(port);
            reader=new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            String msg = null;
            try {
                msg = reader.readLine();
                byte[] datas = msg.getBytes();
//  3.封装成DatagramPacket包裹，需要指定目的地
                DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress(this.toIp, this.toPort));
//  4.发送包裹send（DatagramPacket p）
                client.send(packet);
                if (msg.equals("bye")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //  5.释放资源
        client.close();
    }
}
