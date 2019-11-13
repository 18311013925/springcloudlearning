package com.lizhi.demo.command;

/**
 * @author: lizhi
 * @Date: 2019/11/13 11:19
 * @Description: 客户端调用者
 */
public class Client {
    public static void main(String[] args) {
        Receive receive = new Receive();
        Command command = new ConcreteCommand(receive);
        Invoker invoker = new Invoker();
        invoker.setCommand(command);

        invoker.action();   //客户端通过调用者命令来执行命令
    }
}
