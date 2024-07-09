@Library('your-shared-library') // Adjust 'your-shared-library' to your actual library name

node {
    def pipelineParams = [
        STAGES_TO_RUN: 'Checkout, Modify go.mod, Build, Unit Test, SonarQube Analysis, Dependency Scan, Archive Report',
        GO_TOOL_NAME: 'Go',
        SCANNER_TOOL_NAME: 'sonar', // Assuming 'sonar' is your SonarQube scanner tool name
        DEPENDENCY_TOOL_NAME: 'Dependency-Check',
        GIT_BRANCH: 'main',
        GIT_URL: 'https://github.com/Naresh-boyini/employee-api.git',
        SONARQUBE_ENV_NAME: 'sonar-kumar'
    ]

    // Call your custom pipeline method
    org.example.MyPipeline.runPipeline(pipelineParams)
}
