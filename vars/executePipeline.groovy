package org.example

import org.example.Checkout
import org.example.GoCommands

def call(Map params) {
    String tasksToRun = params.get('tasks', 'all') // Default to 'all' tasks if not specified

    switch (tasksToRun) {
        case 'checkout':
            checkoutGitRepository(params.gitUrl, params.gitBranch)
            break
        case 'goCommands':
            executeGoCommands()
            break
        case 'all':
        default:
            // Run all tasks
            checkoutGitRepository(params.gitUrl, params.gitBranch)
            executeGoCommands()
            break
    }
}

def checkoutGitRepository(String gitUrl, String gitBranch) {
    Checkout.execute(this, gitUrl, gitBranch)
}

def executeGoCommands() {
    GoCommands.executeGoBuild()
}
