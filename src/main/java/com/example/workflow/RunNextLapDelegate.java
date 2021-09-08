package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.feel.syntaxtree.In;

import javax.inject.Named;
import java.util.Map;

@Named("NextLap")
public class RunNextLapDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String messageRef = delegateExecution.getBpmnModelElementInstance().getAttributeValue("messageRef");
        System.out.println("Message Ref: "+  messageRef);
        String messageName =
                delegateExecution.getBpmnModelInstance().getModelElementById(messageRef).getAttributeValue(
                "name");


        Map<String, Object> vars = delegateExecution.getVariables();
        Integer totalLaps = (Integer) vars.get("totalLaps");
        Integer lapsCompleted = (Integer) vars.get("lapsCompleted");
        vars.put("lapsCompleted", lapsCompleted+1);

        System.out.println("Current Laps Completed: " +lapsCompleted);
        System.out.println("Total Race Laps: " +totalLaps);

        delegateExecution.getProcessEngineServices().getRuntimeService().createMessageCorrelation(messageName)
                .setVariables(vars)
                .processInstanceBusinessKey(delegateExecution.getBusinessKey())
                .correlate();
    }
}
