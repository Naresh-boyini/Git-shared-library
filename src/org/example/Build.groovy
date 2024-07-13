package org.example

class Build {
    static void execute(script, String projectDirectory) {
        script.withEnv(['PATH+GO': "${tool name: 'Go', type: 'Go'}/bin"]) {
            script.dir(projectDirectory) {
                script.sh '''
                go mod tidy
                go mod download
                go build -o employee-api .
                '''
            }
        }
    }
}
