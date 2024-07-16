package org.example

class GoCommands {
    static void execute(script) {
        // Using the Go Plugin to execute Go commands
        script.sh '''
        // go mod tidy
        //    go mod download
            go build -o employee-api .
        '''
    }
}
