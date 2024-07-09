package org.example

import static org.example.Common.*

class Stages {
    static Closure checkout(Map params) {
        return {
            stage('Checkout') {
                when {
                    expression {
                        def stagesToRun = splitStages(params.STAGES_TO_RUN)
                        echo "Stages to run: ${stagesToRun}"
                        return shouldRunStage('Checkout', stagesToRun)
                    }
                }
                steps {
                    git branch: params.GIT_BRANCH, url: params.GIT_URL
                }
            }
        }
    }

    static Closure modifyGoMod(Map params) {
        return {
            stage('Modify go.mod') {
                when {
                    expression {
                        def stagesToRun = splitStages(params.STAGES_TO_RUN)
                        echo "Stages to run: ${stagesToRun}"
                        return shouldRunStage('Modify go.mod', stagesToRun)
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
    }

    static Closure build(Map params) {
        return {
            stage('Build') {
                when {
                    expression {
                        def stagesToRun = splitStages(params.STAGES_TO_RUN)
                        echo "Stages to run: ${stagesToRun}"
                        return shouldRunStage('Build', stagesToRun)
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
    }

    static Closure unitTest(Map params) {
        return {
            stage('Unit Test') {
                when {
                    expression {
                        def stagesToRun = splitStages(params.STAGES_TO_RUN)
                        echo "Stages to run: ${stagesToRun}"
                        return shouldRunStage('Unit Test', stagesToRun)
                    }
                }
                steps {
                    sh 'go test ./... -v | tee unit-test-results.xml'
                }
            }
        }
    }

    static Closure sonarQubeAnalysis(Map params) {
        return {
            stage('SonarQube Analysis') {
                when {
                    expression {
                        def stagesToRun = splitStages(params.STAGES_TO_RUN)
                        echo "Stages to run: ${stagesToRun}"
                        return shouldRunStage('SonarQube Analysis', stagesToRun)
                    }
                }
                steps {
                    withSonarQubeEnv(params.SONARQUBE_ENV_NAME) {
                        sh "${params.SCANNER_HOME}/bin/sonar-scanner"
                    }
                }
            }
        }
    }

    static Closure dependencyScan(Map params) {
        return {
            stage('Dependency Scan') {
                when {
                    expression {
                        def stagesToRun = splitStages(params.STAGES_TO_RUN)
                        echo "Stages to run: ${stagesToRun}"
                        return shouldRunStage('Dependency Scan', stagesToRun)
                    }
                }
                steps {
                    dependencyCheck additionalArguments: '--scan . --format ALL', odcInstallation: params.DEPENDENCY_TOOL_NAME
                }
            }
        }
    }

    static Closure archiveReport(Map params) {
        return {
            stage('Archive Report') {
                when {
                    expression {
                        def stagesToRun = splitStages(params.STAGES_TO_RUN)
                        echo "Stages to run: ${stagesToRun}"
                        return shouldRunStage('Archive Report', stagesToRun)
                    }
                }
                steps {
                    archiveArtifacts artifacts: 'unit-test-results.xml', allowEmptyArchive: true

                    echo "Publishing Reports"
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
    }
}
