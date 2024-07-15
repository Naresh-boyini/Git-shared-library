package org.example

import org.example.Checkout
import org.example.GoCommands
import org.example.UnitTestStage
import org.example.DependencyCheck

def call(Map params) {
    String tasksToRun = params.get('tasks', 'all') // Default to 'all' tasks if not specified

    switch (tasksToRun) {
        case 'checkout':
            checkoutGitRepository(params.gitUrl, params.gitBranch)
            break
        case 'goCommands':
            executeGoCommands()
            break
        case 'unitTests':
            runUnitTests()
            break
        case 'depenCheck':
            dependencyCheck()
            break
        case 'all':
        default:
            // Run all tasks
            checkoutGitRepository(params.gitUrl, params.gitBranch)
            executeGoCommands()
            runUnitTests()
            dependencyCheck()
            break
    }
}

def checkoutGitRepository(String gitUrl, String gitBranch) {
    Checkout.execute(this, gitUrl, gitBranch)
}

def executeGoCommands() {
    GoCommands.execute(this)
}

def runUnitTests() {
    UnitTestStage.execute(this)
}
def dependencyCheck(env.dependencyCheckHome) {
    DependencyCheck.execute(this)
}
