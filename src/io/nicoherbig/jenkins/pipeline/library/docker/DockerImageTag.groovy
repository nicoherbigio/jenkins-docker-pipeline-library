#!/usr/bin/env groovy
package io.nicoherbig.jenkins.pipeline.library.docker

class DockerImageTag {

    String name

    DockerImageTag(String name) {
        this.name = name
    }

}
