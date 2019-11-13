package com.lizhi.demo.command;

/**
 * @author: lizhi
 * @Date: 2019/11/13 11:15
 * @Description:
 */
public class ConcreteCommand implements Command{


    private Receive receive;

    public ConcreteCommand(Receive receive) {
        this.receive = receive;
    }

    @Override
    public void execute() {
        this.receive.action();
    }
}
