package org.example

class GoCommands {
    static void execute(script) {
        // Define Go tool
       // def goTool = tool name: 'Go', type: 'hudson.plugins.go.GoInstallation'

        // Use Go tool
      //  script.withEnv(["PATH+GO=${goTool}/bin"]) {
          sh '''
                go mod tidy
                go mod download
                go build -o employee-api .
            '''
        }
    }
}
