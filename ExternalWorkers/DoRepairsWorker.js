const { Client, logger } = require("camunda-external-task-client-js");
const { Variables } = require("camunda-external-task-client-js");

// configuration for the Client:
//  - 'baseUrl': url to the Process Engine
//  - 'logger': utility to automatically log important events
const config = { baseUrl: "http://localhost:8080/engine-rest", use: logger };

// create a Client instance with custom configuration
const client = new Client(config);

// susbscribe to the topic: 'MakeRepair'
client.subscribe("MakeRepair", async function({ task, taskService }) {
  // Put your business logic
  const numberOfRepairsNeeded = task.variables.get("numberOfRepairsNeeded");
  await new Promise(resolve => setTimeout(resolve, 5000));
  const processVariables = new Variables();
  processVariables.set("numberOfRepairsNeeded", numberOfRepairsNeeded-1);
  // complete the task
  await taskService.complete(task, processVariables);
});