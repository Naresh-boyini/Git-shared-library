package org.example
class GoCommands {
    static void execute(script) {
        // Using the Go Plugin to execute Go commands
        script.sh '''
            export GO111MODULE=on
            sed -i 's/go 1.20/go 1.18/g' go.mod
            go mod tidy
            go mod download
            go build -o employee-api .
        '''
    }
}
