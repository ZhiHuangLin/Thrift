package com.wecon.thrift;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhl
 * @create 2021/1/28 15:08
 * @description 服务端要必须实现UserService中的UserService.Iface接口,为其提供具体的业务逻辑.
 */
public class UserServiceImpl implements UserService.Iface {
    private final Logger logger = LoggerFactory.getLogger((this.getClass()));
    private final static String LINMOU = "LINMOU";

    @Override
    public String getName(int id) throws TException {
        logger.info("received getName,id = {}:",id);
        return LINMOU;
    }

    @Override
    public boolean isExist(String name) throws TException {
        logger.info("received isExist,name = {}:",name);
        return LINMOU.equals(name);
    }
}
