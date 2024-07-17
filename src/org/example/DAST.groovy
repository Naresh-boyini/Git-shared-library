pipeline {
    agent any

    environment {
        // GITHUB_PAT = credentials('github-pat') // Replace with your GitHub PAT credential ID
        // SERVER = 'http://frontend-1224975439.us-east-1.elb.amazonaws.com:81/swagger/index.html' // Ensure the server IP address is correct
        server_new = 'http://3.108.196.71:8080/swagger/index.html'
        
    }
    

    stages {
        stage('DAST') {
            steps {
                sh "curl http://localhost:5555//JSON/spider/action/scan/?url=${server_new}&maxChildren=10"
                sh "curl http://localhost:5555//OTHER/core/other/htmlreport/ -o zap_report.html"
            }
        }
        stage('Publish Report') {
            steps {
                echo 'Publishing ZAP report...'
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: '.',
                    reportFiles: 'zap_report.html',
                    reportName: 'OWASP ZAP Report'
                ])
            }
        }

        stage('Build Status') {
            steps {
                script {
                    if (currentBuild.currentResult == 'SUCCESS') {
                        echo 'Build was successful!'
                    } else {
                        echo 'Build failed.'
                    }
                }
            }
        }
    }

}
