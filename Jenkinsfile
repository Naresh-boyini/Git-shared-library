/*@Library('your-shared-library') _

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
*/


@Library('your-shared-library') _

node {
    // Define the Go tool installation
    def goTool = tool name: 'Go', type: 'go'
    // 'Go' here is the name of the Go installation configured in Jenkins

    // Define DEPENDENCY_CHECK_HOME for Dependency-Check
    def dependencyCheckHome = tool name: 'Dependency-Check'

    // Parameters
    def gitUrl = params.GIT_URL ?: 'https://github.com/Naresh-boyini/employee-api.git'
    def gitBranch = params.GIT_BRANCH ?: 'main'

    // Set environment variables
    env.DEPENDENCY_CHECK_HOME = dependencyCheckHome

    stage('Checkout') {
        buildPipeline.checkoutGitRepository(gitUrl, gitBranch)
    }
    
    stage('Execute Go Commands') {
        buildPipeline.executeGoCommands()
    }
    
    stage('Run Unit Tests') {
        buildPipeline.runUnitTests()
    }
    
    stage('Dependency Check') {
        buildPipeline.dependencyCheck()
    }
}
