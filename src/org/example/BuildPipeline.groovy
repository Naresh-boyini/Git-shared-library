package org.example

def run() {
    pipeline {
        agent any

        stages {
            stage('Build') {
                steps {
                    script {
                        def goTool = tool name: 'Go', type: 'Go'
                        env.PATH = "${goTool}/bin:${env.PATH}"
                        
                        sh '''
                        go mod tidy
                        go mod download
                        go build -o employee-api .
                        '''
                    }
                }
            }
        }

        post {
            success {
                echo 'Build successful!'
                // Additional actions on success
            }
            failure {
                echo 'Build failed!'
                // Additional actions on failure
            }
        }
    }
}
