package io.deepmedia.tools.publisher

import io.deepmedia.tools.publisher.common.Auth
import io.deepmedia.tools.publisher.common.Project
import io.deepmedia.tools.publisher.common.Release
import io.deepmedia.tools.publisher.common.Signing
import org.gradle.api.Named

interface Publication : Named {
    var component: String?
    var publication: String?
    var clonePublication: Boolean
    fun setPublication(publication: String, clone: Boolean) {
        this.publication = publication
        this.clonePublication = clone
    }

    val auth: Auth
    val project: Project
    val release: Release
    val signing: Signing
}