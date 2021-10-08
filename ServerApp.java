/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.zooregistergui;

// A Java program for a ServerSide
import java.net.*;
import java.io.*;

/**
Programmer: Sinoth Hlayisani Mabasa
Date: 23/09/2021
Description: The program will run the client application that will await for client to respond
 */
        
public class ServerApp {
// A Java program for a Server

    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
  
    // constructor with port
    public ServerApp(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");
  
            System.out.println("Waiting for a client ...");
  
            socket = server.accept();
            System.out.println("Client accepted");
  
            // takes input from the client socket
            in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
  
            String line = "";
  
            // reads message from client until "Over" is sent
            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    System.out.println(line);
  
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
  
            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
  
    public static void main(String args[])
    {
        ServerApp server = new ServerApp(16000);
    }
}


