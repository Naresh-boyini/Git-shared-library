@Library('your-shared-library') _

pipeline {
    agent any

    parameters {
        string(name: 'GIT_BRANCH', defaultValue: 'main', description: 'Git branch to checkout')
        string(name: 'GIT_URL', defaultValue: 'https://github.com/Naresh-boyini/Git-shared-library.git', description: 'Git repository URL')
        string(name: 'PROJECT_DIRECTORY', defaultValue: '.', description: 'Directory where project resides')
        string(name: 'PARAM_BUILD', defaultValue: 'checkout,build', description: 'Specify which stages to run (comma-separated)')
    }

    stages {
        stage('Run Stages') {
            steps {
                script {
                    echo "PARAM_BUILD: ${params.PARAM_BUILD}"
                    paramBuildPipeline(
                        projectDirectory: params.PROJECT_DIRECTORY,
                        paramBuild: params.PARAM_BUILD,
                        gitUrl: params.GIT_URL,
                        gitBranch: params.GIT_BRANCH
                    )()
                }
            }
        }

        // Add more stages as needed
    }
}
