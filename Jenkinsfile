@Library('your-shared-library') _

pipeline {
    agent any

    parameters {
        string(name: 'GIT_BRANCH', defaultValue: 'main', description: 'Git branch to checkout')
        string(name: 'GIT_URL', defaultValue: 'https://github.com/Naresh-boyini/employee-api.git', description: 'Git repository URL')
        string(name: 'PROJECT_DIRECTORY', defaultValue: '.', description: 'Directory where project resides')
        string(name: 'STAGES_TO_RUN', defaultValue: 'checkout,build', description: 'Stages to run (comma-separated)')
    }

    stages {
        stage('Run Stages') {
            steps {
                buildPipeline(
                    projectDirectory: params.PROJECT_DIRECTORY,
                    stagesToRun: params.STAGES_TO_RUN
                )
            }
        }

        // Add more stages as needed
    }
}
