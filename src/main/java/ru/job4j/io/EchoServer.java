package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("?msg")) {
                            if (str.toLowerCase().contains("?msg=hello")) {
                                out.write("Hello".getBytes());
                            } else if (str.toLowerCase().contains("?msg=exit")) {
                                server.close();
                            } else {
                                out.write("What".getBytes());
                            }
                        }
                    }
                    out.flush();
                } catch (IOException e) {
                    LOG.error("Out/ input error", e);
                }
            }
        } catch (IOException e) {
            LOG.error("ServerSocket error", e);
        }
    }
}
