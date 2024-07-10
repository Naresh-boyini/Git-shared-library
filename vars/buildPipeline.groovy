def call(Map pipelineParams) {
    def gitUrl = pipelineParams.gitUrl ?: error 'Git URL not provided'
    def gitBranch = pipelineParams.gitBranch ?: error 'Git branch not provided'
    
    org.example.BuildPipeline.run(gitUrl, gitBranch)
}
