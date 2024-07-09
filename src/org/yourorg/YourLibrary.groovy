package org.yourorg

class YourLibrary {

    static def checkoutCode(script, gitBranch, gitUrl) {
        script.git branch: gitBranch, url: gitUrl
    }

    static def modifyGoMod(script) {
        script.sh '''
            export GO111MODULE=on
            sed -i 's/go 1.20/go 1.18/g' go.mod
        '''
    }

    static def buildProject(script) {
        script.sh '''
            go mod tidy
            go mod download
            go build -o employee-api .
        '''
    }

    static def runUnitTests(script) {
        script.sh 'go test ./... -v | tee unit-test-results.xml'
    }

    static def runSonarQubeAnalysis(script, scannerHome, sonarQubeEnvName) {
        script.withSonarQubeEnv(sonarQubeEnvName) {
            script.sh "${scannerHome}/bin/sonar-scanner"
        }
    }

    static def runDependencyScan(script, dependencyToolName) {
        script.dependencyCheck additionalArguments: '--scan . --format ALL', odcInstallation: dependencyToolName
    }

    static def archiveReports(script) {
        script.archiveArtifacts artifacts: 'unit-test-results.xml', allowEmptyArchive: true
        script.publishHTML([
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
