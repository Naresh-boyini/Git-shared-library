package org.example

class GoTest {
    static void execute(script) {
        // Using the Go Plugin to execute Go commands
        script.sh '''
         go test ./... -v
        '''
    }
}
