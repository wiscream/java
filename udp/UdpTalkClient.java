package com.sxt.st12.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 发送端
 * 1.使用DatagramSocket 指定端口 创建发送端
 * 2.准备数据 一定转成字节数组
 * 3.封装成DatagramPacket包裹，需要指定目的地
 * 4.发送包裹send（DatagramPacket p）
 * 5.释放资源
 */
public class UdpTalkClient {
    public static void main(String[] args) throws IOException {
        System.out.println("发送方启动中");
//  1.使用DatagramSocket 指定端口 创建发送端
        DatagramSocket client=new DatagramSocket(8888);
//  2.准备数据 一定转成字节数组
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String msg = reader.readLine();
            byte[] datas = msg.getBytes();
//  3.封装成DatagramPacket包裹，需要指定目的地
            DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 5555));
//  4.发送包裹send（DatagramPacket p）
            client.send(packet);
            if (msg.equals("bye")){
                break;
            }
        }
//  5.释放资源
        client.close();

    }
}
