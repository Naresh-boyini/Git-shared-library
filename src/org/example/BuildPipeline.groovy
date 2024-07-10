package org.example

class BuildPipeline {
    static void run() {
        pipeline {
            agent any

            stages {
                stage('Checkout') {
                    steps {
                        script {
                            Checkout.execute(script)
                        }
                    }
                }

                stage('Modify go.mod') {
                    steps {
                        script {
                            Checkout.execute(script)
                            sh '''
                            export GO111MODULE=on
                            sed -i 's/go 1.20/go 1.18/g' go.mod
                            '''
                        }
                    }
                }

                stage('Build') {
                    steps {
                        script {
                            Checkout.execute(script)
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
}
