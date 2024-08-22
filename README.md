# WF-CommandUtils:
## Maven:
`Java(min): 17`
```xml
  <dependency>
    <groupId>io.github.wf4java</groupId>
    <artifactId>WF-CommandUtils</artifactId>
    <version>1.1.0</version>
  </dependency>
```

## Example:
```java
StringCommandHandler commandHandler = new StringCommandHandler();

commandHandler.addSubcommand(
        SubCommand.builder(String.class)
                .setCommand("set.data")
                .setArguments(
                        new Argument("number", ArgumentType.INTEGER),
                        new Argument("boolean", ArgumentType.BOOLEAN, false, true)
                )
                .setRunnable((sender, command, result) -> {
                    System.out.println("Result: " + result[0]);
                    System.out.println("Result: " + result[1]);
                })
                .build()
);


commandHandler.addSubcommand(
        SubCommand.builder(String.class)
                .setCommand("remove.data")
                .setArguments(
                        new Argument("number", ArgumentType.INTEGER)
                )
                .setRunnable((sender, command, result) -> {
                    System.out.println("Result: " + result[0]);
                })
                .build()
);


commandHandler.onCommand("set data 5", message -> System.out.println("Error: " + message));
commandHandler.onCommand("remove data 4", message -> System.out.println("Error: " + message));
commandHandler.onCommand("remove data false", message -> System.out.println("Error: " + message));
```

