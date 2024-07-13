// vars/paramBuildPipeline.groovy

import org.example.Checkout
import org.example.Build

def call(Map pipelineParams) {
    def projectDirectory = pipelineParams.projectDirectory ?: error('Missing projectDirectory parameter')
    def paramBuild = pipelineParams.paramBuild ?: 'checkout,build'
    def gitUrl = pipelineParams.gitUrl ?: error('Missing gitUrl parameter')
    def gitBranch = pipelineParams.gitBranch ?: error('Missing gitBranch parameter')

    echo "Received projectDirectory: ${projectDirectory}"
    echo "Received paramBuild: ${paramBuild}"
    echo "Received gitUrl: ${gitUrl}"
    echo "Received gitBranch: ${gitBranch}"

    def stages = paramBuild.split(',')

    return { script ->
        stages.each { stage ->
            echo "Executing stage: ${stage.trim().toLowerCase()}"
            switch (stage.trim().toLowerCase()) {
                case 'checkout':
                    Checkout.execute(script, gitUrl, gitBranch)
                    break
                case 'build':
                    Build.execute(script, projectDirectory)
                    break
                default:
                    script.echo "Unknown stage: ${stage}"
            }
        }
    }
}
