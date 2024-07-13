@Library('your-shared-library') _

pipeline {
    agent any
tools {
        // Define the Go tool installation
        go 'Go'
        // 'Go' here is the name of the Go installation configured in Jenkins
    }
    parameters {
        string(name: 'gitUrl', defaultValue: 'https://github.com/Naresh-boyini/employee-api.git', description: 'Git repository URL')
        string(name: 'gitBranch', defaultValue: 'main', description: 'Git branch')
        string(name: 'tasks', defaultValue: 'all', description: 'Tasks to execute: checkout, goCommands, dependencyCheck, or all')
    }

    stages {
        stage('Execute Pipeline') {
            steps {
                script {
                    buildPipeline(
                        gitUrl: params.gitUrl,
                        gitBranch: params.gitBranch,
                        tasks: params.tasks
                    )
                }
            }
        }
    }
}
