def formatStages(stagesToRun) {
    return stagesToRun.split(',').collect { it.trim() }.join(', ')
}
