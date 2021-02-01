package com.wecon.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.omg.CORBA.TIMEOUT;

/**
 * @author zhl
 * @create 2021/1/28 15:23
 * @description
 */
public class ThriftClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 2345;
    private static final int TIME_OUT = 3000;

    public static void main(String[] args) {
        ThriftClient thriftClient = new ThriftClient();
        thriftClient.start("LINMOU");
    }
    public void start(String userName){
        TTransport tTransport = new TSocket(SERVER_IP, SERVER_PORT, TIME_OUT);
        TProtocol protocol = new TBinaryProtocol(tTransport);
        UserService.Client client = new UserService.Client(protocol);
        try {
            tTransport.open();
            System.out.println(client.getName(1));
            System.out.println(client.isExist(userName));
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }finally {
            tTransport.close();
        }
    }
}
