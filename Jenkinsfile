@Library('your-shared-library') _

pipeline {
    agent any

    parameters {
        string(name: 'GIT_URL', defaultValue: 'https://github.com/your-repo/your-project.git', description: 'Git repository URL')
        string(name: 'GIT_BRANCH', defaultValue: 'main', description: 'Git branch to checkout')
        string(name: 'PROJECT_DIR', defaultValue: '.', description: 'Project directory for build')
    }

    stages {
        stage('Build and Checkout') {
            steps {
                buildAndCheckout([
                    GIT_URL: params.GIT_URL,
                    GIT_BRANCH: params.GIT_BRANCH,
                    projectDirectory: params.PROJECT_DIR
                ])
            }
        }
    }
}
