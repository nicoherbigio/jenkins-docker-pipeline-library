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
    String details = """<p>${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>"""

    // Send notifications

    slackSend(
        color: colorCode,
        message: summary
    )

    emailext(
        recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']],
        subject: subject,
        body: details
    )
}

this
