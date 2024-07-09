import org.example.Stages

def call(Map params) {
    pipeline {
        agent any

        parameters {
            string(name: 'STAGES_TO_RUN', defaultValue: 'Checkout, Modify go.mod, Build, Unit Test, SonarQube Analysis, Dependency Scan, Archive Report', description: 'Comma-separated list of stages to run')
            string(name: 'GO_TOOL_NAME', defaultValue: 'Go', description: 'Name of the Go tool as configured in Jenkins')
            string(name: 'SCANNER_TOOL_NAME', defaultValue: 'sonar', description: 'Name of the SonarQube Scanner tool as configured in Jenkins')
            string(name: 'DEPENDENCY_TOOL_NAME', defaultValue: 'Dependency-Check', description: 'Name of the dependency scanning tool as configured in Jenkins')
            string(name: 'GIT_BRANCH', defaultValue: 'main', description: 'Git branch to checkout')
            string(name: 'GIT_URL', defaultValue: 'https://github.com/Naresh-boyini/employee-api.git', description: 'Git repository URL')
            string(name: 'SONARQUBE_ENV_NAME', defaultValue: 'sonar-kumar', description: 'SonarQube environment name')
        }

        tools {
            go "${params.GO_TOOL_NAME}" // Use the name exactly as configured in Jenkins' Global Tool Configuration
        }

        environment {
            SCANNER_HOME = tool "${params.SCANNER_TOOL_NAME}" // Define SCANNER_HOME for SonarQube
            DEPENDENCY_CHECK_HOME = tool "${params.DEPENDENCY_TOOL_NAME}" // Define DEPENDENCY_CHECK_HOME for Dependency-Check
            GO_VERSION = '1.18' // Define GO_VERSION for Go
        }

        stages {
            stage('Shared Library Stages') {
                steps {
                    script {
                        Stages.checkout(params)
                        Stages.modifyGoMod(params)
                        Stages.build(params)
                        Stages.unitTest(params)
                        Stages.sonarQubeAnalysis(params)
                        Stages.dependencyScan(params)
                        Stages.archiveReport(params)
                    }
                }
            }
        }

        post {
            success {
                echo 'Build successful!'
                // Additional actions on success
            }
            failure {
                echo 'Build failed!'
                // Additional actions on failure
            }
        }
    }
}