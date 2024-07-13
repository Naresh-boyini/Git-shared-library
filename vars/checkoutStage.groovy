def call(Map pipelineParams) {
    stage('Checkout') {
        org.example.Checkout.execute(this)
    }

    stage('Build') {
        def projectDirectory = pipelineParams.projectDirectory ?: error('Missing projectDirectory parameter')
        org.example.Build.execute(this, projectDirectory)
    }
}
