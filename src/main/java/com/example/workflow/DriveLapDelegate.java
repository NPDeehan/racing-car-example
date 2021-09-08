package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.Random;

@Named("DriveLap")
public class DriveLapDelegate implements JavaDelegate {

    Random rando = new Random();

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Integer wheelCondition = (Integer) delegateExecution.getVariable("wheelCondition");
        Integer batteryPower = (Integer) delegateExecution.getVariable("batteryPower");
        Boolean repairsNeeded = (Boolean) delegateExecution.getVariable("repairsNeeded");
        Integer numberOfRepairsNeeded = (Integer)  delegateExecution.getVariable("numberOfRepairsNeeded");

        // Now we run the race

        wheelCondition = wheelCondition - rando.nextInt(20);
        batteryPower = batteryPower - rando.nextInt(30);

        if(rando.nextInt(10) < 3){
            repairsNeeded = true;
            numberOfRepairsNeeded = numberOfRepairsNeeded + rando.nextInt(5);
        }

        delegateExecution.setVariable("wheelCondition", wheelCondition);
        delegateExecution.setVariable("batteryPower", batteryPower);
        delegateExecution.setVariable("repairsNeeded", repairsNeeded);
        delegateExecution.setVariable("numberOfRepairsNeeded", numberOfRepairsNeeded);


    }


}
