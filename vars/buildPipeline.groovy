// vars/buildPipeline.groovy

import org.example.Checkout
import org.example.Build

def call(Map pipelineParams) {
    def projectDirectory = pipelineParams.projectDirectory ?: error('Missing projectDirectory parameter')
    def paramBuild = pipelineParams.paramBuild ?: 'checkout,build'

    def stages = paramBuild.split(',')

    return {
        script ->
        stages.each { stage ->
            switch (stage.trim().toLowerCase()) {
                case 'checkout':
                    Checkout.execute(script)
                    break
                case 'build':
                    Build.execute(script, projectDirectory)
                    break
                default:
                    echo "Unknown stage: $stage"
            }
        }
    }
}
