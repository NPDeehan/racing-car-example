package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.Random;


@Named("ReplaceBattery")
public class ReplaceBatteryDelegate implements JavaDelegate {

    Random rando = new Random();

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Thread.sleep((long)(Math.random() * 1000));

        delegateExecution.setVariable("batteryPower", 100);

    }
}
