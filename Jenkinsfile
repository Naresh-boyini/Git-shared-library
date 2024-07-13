@Library('global-shared-library') _

pipeline {
    agent any

    parameters {
        string(name: 'gitUrl', defaultValue: 'https://github.com/Naresh-boyini/employee-api.git', description: 'Git repository URL')
        string(name: 'gitBranch', defaultValue: 'main', description: 'Git branch')
    }

    stages {
        stage('Execute Pipeline') {
            steps {
                script {
                    executePipeline(
                        gitUrl: params.gitUrl,
                        gitBranch: params.gitBranch
                    )
                }
            }
        }
    }
}
