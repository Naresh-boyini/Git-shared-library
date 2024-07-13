@Library('my-shared-library') _

pipeline {
    agent any

    parameters {
        string(name: 'GIT_URL', defaultValue: 'https://github.com/your-repo/your-project.git', description: 'Git repository URL')
        string(name: 'GIT_BRANCH', defaultValue: 'main', description: 'Git branch to checkout')
    }

    stages {
        stage('Checkout') {
            steps {
                checkoutStage()
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project...'
                // Add your build steps here
            }
        }
    }
}
