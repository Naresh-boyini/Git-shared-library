// File: src/org/example/HelloWorld.groovy
package org.example

class HelloWorld {
    static void sayHello() {
        // Command to create a Python virtual environment
        def createEnvCommand = "python3 -m venv myenv"
        
        // Command to activate the virtual environment
        def activateEnvCommand = ". myenv/bin/activate"
        
        // Command to run Snyk security test
        def snykTestCommand = "snyk test --file=poetry.lock"
        
        // Execute commands sequentially
        executeShellCommand(createEnvCommand)
        executeShellCommand(activateEnvCommand)
        executeShellCommand(snykTestCommand)
    }

    static void executeShellCommand(String command) {
        def output = command.execute().text
        println "Shell Command: $command\nOutput: $output"
    }
}
