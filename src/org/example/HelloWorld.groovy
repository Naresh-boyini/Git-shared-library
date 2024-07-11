// File: src/org/example/HelloWorld.groovy
package org.example

class HelloWorld {
    static void sayHello() {
        try {
            // Command to create a Python virtual environment
            def createEnvCommand = "python3 -m venv myenv"

            // Command to activate the virtual environment
            def activateEnvCommand = ". myenv/bin/activate"

            // Execute commands sequentially
            executeShellCommand(createEnvCommand)
            executeShellCommand(activateEnvCommand)
        } catch (Exception e) {
            println "Error executing command: ${e.message}"
            e.printStackTrace()
        }
    }

    static void executeShellCommand(String command) {
        try {
            def output = command.execute().text.trim()
            println "Shell Command: $command\nOutput: $output"
        } catch (Exception e) {
            println "Error executing command: ${e.message}"
            e.printStackTrace()
        }
    }
}
