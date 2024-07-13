def call(Map pipelineParams) {
    script {
        org.example.Checkout.execute(this)
        org.example.BuildPipeline.execute(this, pipelineParams.projectDirectory)
    }
}
