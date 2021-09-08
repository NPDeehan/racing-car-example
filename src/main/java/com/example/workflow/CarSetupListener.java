package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("CarSetup")
public class CarSetupListener implements ExecutionListener {


    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("wheelCondition", 100);
        delegateExecution.setVariable("batteryPower", 100);
        delegateExecution.setVariable("repairsNeeded", false);
        delegateExecution.setVariable("numberOfRepairsNeeded", 0);
        delegateExecution.setVariable("lapsCompleted", 0);

    }
}
