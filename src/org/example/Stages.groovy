// src/org/example/Stages.groovy

package org.example

class Stages {
    static def checkout(Map params) {
        stage('Checkout') {
            when {
                expression {
                    return shouldRunStage(params, 'Checkout')
                }
            }
            steps {
                git branch: "${params.GIT_BRANCH}", url: "${params.GIT_URL}"
            }
        }
    }

    static def modifyGoMod(Map params) {
        stage('Modify go.mod') {
            when {
                expression {
                    return shouldRunStage(params, 'Modify go.mod')
                }
            }
            steps {
                sh '''
                export GO111MODULE=on
                sed -i 's/go 1.20/go 1.18/g' go.mod
                '''
            }
        }
    }

    static def build(Map params) {
        stage('Build') {
            when {
                expression {
                    return shouldRunStage(params, 'Build')
                }
            }
            steps {
                sh '''
                go mod tidy
                go mod download
                go build -o employee-api .
                '''
            }
        }
    }

    static def unitTest(Map params) {
        stage('Unit Test') {
            when {
                expression {
                    return shouldRunStage(params, 'Unit Test')
                }
            }
            steps {
                sh 'go test ./... -v | tee unit-test-results.xml'
            }
        }
    }

    static def sonarQubeAnalysis(Map params) {
        stage('SonarQube Analysis') {
            when {
                expression {
                    return shouldRunStage(params, 'SonarQube Analysis')
                }
            }
            steps {
                withSonarQubeEnv("${params.SONARQUBE_ENV_NAME}") {
                    sh "${SCANNER_HOME}/bin/sonar-scanner"
                }
            }
        }
    }

    static def dependencyScan(Map params) {
        stage('Dependency Scan') {
            when {
                expression {
                    return shouldRunStage(params, 'Dependency Scan')
                }
            }
            steps {
                // Run OWASP Dependency-Check
                dependencyCheck additionalArguments: '--scan . --format ALL', odcInstallation: "${params.DEPENDENCY_TOOL_NAME}"
            }
        }
    }

    static def archiveReport(Map params) {
        stage('Archive Report') {
            when {
                expression {
                    return shouldRunStage(params, 'Archive Report')
                }
            }
            steps {
                // Archive the unit test results
                archiveArtifacts artifacts: 'unit-test-results.xml', allowEmptyArchive: true

                echo "Publishing Reports"
                // Publish OWASP Dependency-Check report in HTML format
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: '.',
                    reportFiles: 'dependency-check-report.html',
                    reportName: 'Dependency Check Report',
                    reportTitles: ''
                ])
            }
        }
    }

    static def shouldRunStage(Map params, String stageName) {
        def stagesToRun = params.STAGES_TO_RUN.split(',').collect { it.trim() }
        echo "Stages to run: ${stagesToRun}"
        return stagesToRun.contains(stageName)
    }
}
