package com.mycompany.thriftme;

import org.apache.thrift.TException;

/**
 *
 * @author siddharthasingh
 */
public class ArithmeticServiceImpl implements ArithmeticService.Iface {

    @Override
    public long add(int num1, int num2) throws TException {
        return num1 + num2;
    }

    @Override
    public long multiply(int num1, int num2) throws TException {
        return num1 * num2;
    }

}
