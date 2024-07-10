package org.example

class Checkout {
    static void execute(script) {
        script.stage('Checkout') {
            script.when {
                script.expression {
                    def stagesToRun = script.params.STAGES_TO_RUN.split(',').collect { it.trim() }
                    script.echo "Stages to run: ${stagesToRun}"
                    return stagesToRun.contains('Checkout')
                }
            }
            script.steps {
                script.git branch: "${script.params.GIT_BRANCH}", url: "${script.params.GIT_URL}"
            }
        }
    }
}
