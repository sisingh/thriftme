#!/bin/bash

echo "generating java thrift file"

thrift --gen java arithmetic.thrift

echo "copying generated file"
cp -r gen-java/com/mycompany/thriftme/ArithmeticService.java ThriftMe/src/main/java/com/mycompany/thriftme/

echo "building mvn project"
cd ThriftMe
mvn clean install
