// File: src/org/example/GoCommands.groovy

package org.example

class GoCommands {
    static void executeGoBuild() {
        def commands = """
            go mod tidy
            go mod download
            go build -o employee-api .
        """
        sh script: commands.trim(), label: 'Execute Go Build'
    }
}
