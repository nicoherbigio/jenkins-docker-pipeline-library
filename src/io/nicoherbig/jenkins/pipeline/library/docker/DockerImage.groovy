#!/usr/bin/env groovy
package io.nicoherbig.jenkins.pipeline.library.docker

class DockerImage {

    Object theImage = null

    String name
    String contextPath
    String dockerfileName

    DockerImageTag buildTag = new DockerImageTag('latest')
    List<DockerImageTag> pushTags = new ArrayList<DockerImageTag>()

    /**
     *
     * @param name
     * @param contextPath
     * @param dockerfileName
     */
    DockerImage(String name, String contextPath = '.', String dockerfileName = 'Dockerfile') {
        this.name = name
        this.contextPath = contextPath
        this.dockerfileName = dockerfileName
    }

    /**
     *
     * @return
     */
    String getFullQualifiedBuildTag() {
        return this.name + ':' + this.buildTag.name
    }

    /**
     *
     * @param buildNumber
     * @return
     */
    String getFullQualifiedBuildTagWithBuildNumber(String buildNumber, String buildNumberPrefix = '-build-') {
        return getFullQualifiedBuildTag() + buildNumberPrefix + buildNumber
    }

    /**
     *
     * @param gitCommitHash
     * @return
     */
    String getFullQualifiedBuildTagWithGitCommitHash(String gitCommitHash, String gitCommitHashPrefix = '-') {
        return getFullQualifiedBuildTag() + gitCommitHashPrefix + gitCommitHash
    }

    /**
     *
     * @param buildTag
     * @return
     */
    DockerImage setBuildTag(String buildTag) {
        this.buildTag = new DockerImageTag(buildTag)
        return this
    }

    /**
     *
     * @param pushTags
     * @return
     */
    DockerImage addPushTags(List<String> pushTags) {
        for (pushTag in pushTags) {
            addPushTag(pushTag)
        }
        return this
    }

    /**
     *
     * @param pushTag
     * @return
     */
    DockerImage addPushTag(String pushTag) {
        addPushTag(new DockerImageTag(pushTag))
        return this
    }

    /**
     *
     * @param pushTag
     * @return
     */
    DockerImage addPushTag(DockerImageTag pushTag) {
        this.pushTags.add(pushTag)
        return this
    }

}
