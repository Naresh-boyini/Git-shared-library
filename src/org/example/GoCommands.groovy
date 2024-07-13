package org.example

class GoCommands {
    static void execute(script) {
        def goHome = tool name: 'Go', type: 'hudson.plugins.go.GoInstallation'
        
        // Execute Go commands using the defined Go tool installation
        script.withEnv(['PATH+GO': "${goHome}/bin"]) {
            script.sh '''
                go mod tidy
                go mod download
                go build -o employee-api .
            '''
        }
    }
}
