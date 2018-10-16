#!/usr/bin/env groovy

def call(buildStatus = currentBuild.currentResult) {
    String colorCode

    switch (buildStatus) {
        case 'FAILURE':  colorCode = '#FF0000'; break
        case 'STARTED':  colorCode = '#0000FF'; break
        case 'SUCCESS':  colorCode = '#00FF00'; break
        case 'UNSTABLE': colorCode = '#FF0000'; break
        default:         colorCode = '#0000FF'
    }

    String subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    String summary = "${subject} (${env.BUILD_URL})"

    // Send notification

    slackSend(
        color: colorCode,
        message: summary
    )
}

this
