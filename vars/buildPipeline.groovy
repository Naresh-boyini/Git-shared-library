
def call(Map pipelineParams) {
    def projectDirectory = pipelineParams.projectDirectory ?: error('Missing projectDirectory parameter')

    return {
        script ->
        org.example.Build.execute(script, projectDirectory)
    }
}
