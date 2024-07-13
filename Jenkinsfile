@Library('your-shared-library') _

pipeline {
    agent any

    parameters {
        string(name: 'gitUrl', defaultValue: 'https://github.com/Naresh-boyini/employee-api.git', description: 'Git repository URL')
        string(name: 'gitBranch', defaultValue: 'main', description: 'Git branch')
        string(name: 'tasks', defaultValue: 'all', description: 'Tasks to execute: checkout, goCommands, dependencyCheck, or all')
    }

    stages {
        stage('Execute Pipeline') {
            steps {
                script {
                    executePipeline(
                        gitUrl: params.gitUrl,
                        gitBranch: params.gitBranch,
                        tasks: params.tasks
                    )
                }
            }
        }
    }
}
