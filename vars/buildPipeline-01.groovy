// vars/buildPipeline.groovy

import org.example.Checkout
import org.example.Build

def call(Map pipelineParams) {
    def projectDirectory = pipelineParams.projectDirectory ?: error('Missing projectDirectory parameter')

    return {
        script ->
        // Call Checkout execute method
        Checkout.execute(script)

        // Call Build execute method
        Build.execute(script, projectDirectory)
    }
}
