def call(Map params) {
    def stagesToRun = params.STAGES_TO_RUN.split(',').collect { it.trim() }
    def scannerHome = tool params.SCANNER_TOOL_NAME
    def dependencyCheckHome = tool params.DEPENDENCY_TOOL_NAME

    pipeline {
        agent any

        tools {
            go params.GO_TOOL_NAME
        }

        environment {
            SCANNER_HOME = scannerHome
            DEPENDENCY_CHECK_HOME = dependencyCheckHome
            GO_VERSION = '1.18'
        }

        stages {
            stage('Checkout') {
                when {
                    expression { stagesToRun.contains('Checkout') }
                }
                steps {
                    script {
                        org.yourorg.YourLibrary.checkoutCode(this, params.GIT_BRANCH, params.GIT_URL)
                    }
                }
            }

            stage('Modify go.mod') {
                when {
                    expression { stagesToRun.contains('Modify go.mod') }
                }
                steps {
                    script {
                        org.yourorg.YourLibrary.modifyGoMod(this)
                    }
                }
            }

            stage('Build') {
                when {
                    expression { stagesToRun.contains('Build') }
                }
                steps {
                    script {
                        org.yourorg.YourLibrary.buildProject(this)
                    }
                }
            }

            stage('Unit Test') {
                when {
                    expression { stagesToRun.contains('Unit Test') }
                }
                steps {
                    script {
                        org.yourorg.YourLibrary.runUnitTests(this)
                    }
                }
            }

            stage('SonarQube Analysis') {
                when {
                    expression { stagesToRun.contains('SonarQube Analysis') }
                }
                steps {
                    script {
                        org.yourorg.YourLibrary.runSonarQubeAnalysis(this, scannerHome, params.SONARQUBE_ENV_NAME)
                    }
                }
            }

            stage('Dependency Scan') {
                when {
                    expression { stagesToRun.contains('Dependency Scan') }
                }
                steps {
                    script {
                        org.yourorg.YourLibrary.runDependencyScan(this, params.DEPENDENCY_TOOL_NAME)
                    }
                }
            }

            stage('Archive Report') {
                when {
                    expression { stagesToRun.contains('Archive Report') }
                }
                steps {
                    script {
                        org.yourorg.YourLibrary.archiveReports(this)
                    }
                }
            }
        }

        post {
            success {
                echo 'Build successful!'
            }
            failure {
                echo 'Build failed!'
            }
        }
    }
}
