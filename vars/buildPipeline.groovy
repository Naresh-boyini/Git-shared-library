package org.example

import org.example.Checkout
import org.example.GoCommands
import org.example.UnitTestStage
import org.example.DependencyCheck

def checkoutGitRepository(String gitUrl, String gitBranch) {
    Checkout.execute(this, gitUrl, gitBranch)
}

def executeGoCommands() {
    GoCommands.execute(this)
}

def runUnitTests() {
    UnitTestStage.execute(this)
}
def dependencyCheck() {
    DependencyCheck.execute(this)
}
