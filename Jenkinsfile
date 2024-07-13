@Library('your-shared-library') _

pipeline {
    agent any

    parameters {
        string(name: 'GIT_URL', defaultValue: 'https://github.com/your-repo/your-project.git', description: 'Git repository URL')
        string(name: 'GIT_BRANCH', defaultValue: 'main', description: 'Git branch to checkout')
        string(name: 'PROJECT_DIR', defaultValue: '.', description: 'Project directory for build')
    }

    stages {
        stage('Initialize') {
            steps {
                script {
                    pipelineParams = [
                        projectDirectory: params.PROJECT_DIR
                    ]
                }
            }
        }
        stage('Checkout and Build') {
            steps {
                checkoutStage(pipelineParams)
            }
        }
    }
}
