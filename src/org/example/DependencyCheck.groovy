package org.example

class DependencyCheck {
    static void execute(script) {
        def dependencyCheckHome = tool name: 'Dependency-Check', type: 'org.jenkinsci.plugins.DependencyCheck.DependencyCheckToolInstaller'
        
        // Run Dependency-Check using the defined tool installation
        script.withEnv(['DEPENDENCY_CHECK_HOME': dependencyCheckHome]) {
            script.sh '''
                ${DEPENDENCY_CHECK_HOME}/bin/dependency-check.sh --scan . --format ALL
            '''
        }
    }
}
