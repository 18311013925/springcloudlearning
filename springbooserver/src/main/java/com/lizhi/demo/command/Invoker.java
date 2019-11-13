package com.lizhi.demo.command;

/**
 * @author: lizhi
 * @Date: 2019/11/13 11:18
 * @Description: 客户端调用者
 */
public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void action() {
        this.command.execute();
    }
}
