package org.example;

class Common {
    static List<String> splitStages(String stages) {
        return stages.split(',').collect { it.trim() }
    }

    static boolean shouldRunStage(String stageName, List<String> stagesToRun) {
        return stagesToRun.contains(stageName)
    }
}
