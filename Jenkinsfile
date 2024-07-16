@Library('your-shared-library') _

pipeline {
    agent any

    tools {
        // Define the Go tool installation
        go 'Go'
        // 'Go' here is the name of the Go installation configured in Jenkins
    }
    environment {
        DEPENDENCY_CHECK_HOME = tool 'Dependency-Check' // Define DEPENDENCY_CHECK_HOME for Dependency-Check
    }

parameters {
        string(name: 'GIT_URL', defaultValue: 'https://github.com/Naresh-boyini/employee-api.git', description: 'Git repository URL')
        string(name: 'GIT_BRANCH', defaultValue: 'main', description: 'Git branch to checkout')
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    buildPipeline.checkoutGitRepository(params.GIT_URL, params.GIT_BRANCH)
                }
            }
        }
        
        stage('Execute Go Commands') {
            steps {
                script {
                    buildPipeline.executeGoCommands()
                }
            }
        }
        
        stage('Run Unit Tests') {
            steps {
                script {
                    buildPipeline.runUnitTests()
                }
            }
        }
        
        stage('Dependency Check') {
            steps {
                script {
                    buildPipeline.dependencyCheck()
                }
            }

        }
    }
}
