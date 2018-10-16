#!/usr/bin/env groovy
import io.nicoherbig.jenkins.pipeline.library.docker.DockerImage

def call(List<DockerImage> dockerImages, Closure body) {
    def parallelStageMap = dockerImages.collectEntries {
        [(it.contextPath) : generateDockerImageScript(it, body)]
    }

    return parallelStageMap
}

this

def generateDockerImageScript(DockerImage dockerImage, Closure body) {
    return {
        script({
            body(dockerImage)
        })
    }
}
