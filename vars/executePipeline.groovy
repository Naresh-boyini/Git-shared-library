import org.example.Checkout
import org.go_shared_library.Step1
import org.go_shared_library.Step2
import org.go_shared_library.Step3
import org.go_shared_library.Step4
import groovy.yaml.YamlSlurper

def call(Map params) {
    String gitUrl = params.get('gitUrl', 'https://example.com/repository.git')
    String gitBranch = params.get('gitBranch', 'main')

    // Load the configuration file from resources (if needed)
    // def config = new YamlSlurper().parseText(libraryResource('globalConfig.yaml'))

    // Stage 1: Checkout Git Repository
    Checkout.execute(this, gitUrl, gitBranch)

    // Stage 2: Go Step 1
    step('Go Step 1') {
        Step1()
    }

    // Stage 3: Go Step 2
    step('Go Step 2') {
        Step2()
    }

    // Stage 4: Go Step 3
    step('Go Step 3') {
        Step3()
    }

    // Stage 5: Go Step 4 (Dependency Check)
    step('Go Step 4 (Dependency Check)') {
        Step4()
    }
}
