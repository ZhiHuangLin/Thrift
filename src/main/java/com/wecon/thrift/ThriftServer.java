package com.wecon.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhl
 * @create 2021/1/28 15:15
 * @description
 */
public class ThriftServer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        ThriftServer thriftServer = new ThriftServer();
        thriftServer.start();
    }
    public void start(){
        UserService.Processor processor = new UserService.Processor<UserService.Iface>(new UserServiceImpl());
        try {
            TServerTransport transport = new TServerSocket(2345);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(transport);
            args.processor(processor);
            args.protocolFactory(new TBinaryProtocol.Factory());
            args.transportFactory(new TTransportFactory());
            args.minWorkerThreads(10);
            args.maxWorkerThreads(20);
            TServer tServer = new TThreadPoolServer(args);
            tServer.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
            logger.error("thrift服务启动失败",e);
        }
    }
}
