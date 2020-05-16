package com.otaliastudios.tools.publisher

import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.Project
import org.gradle.api.plugins.BasePluginConvention
import org.gradle.api.plugins.PluginContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.delegateClosureOf
import java.util.*

class LocalPublisherPlugin : PublisherPlugin<LocalPublisherExtension>("local") {

    override val modelClass = LocalPublisherExtension::class
    override val uniqueExtensionName = "localPublisher"

    override fun checkModel(target: Project, model: LocalPublisherExtension) {
        super.checkModel(target, model)
        checkModelField(target, model.directory, "directory", false)
    }

    override fun createPublication(target: Project, model: LocalPublisherExtension) {
        super.createPublication(target, model)
        val publishing = target.extensions.getByType(PublishingExtension::class.java)
        publishing.repositories {
            if (model.directory != null) {
                maven {
                    this.setUrl(model.directory!!)
                    this.name = "local"
                }
            } else {
                mavenLocal {
                    this.name = "local"
                }
            }
        }
    }

    override fun createPublishingTasks(target: Project, model: LocalPublisherExtension) {
        val publication = model.publication!!.capitalize()
        val repository = "local".capitalize()
        target.tasks.register("publishTo$publication") {
            dependsOn("publish${publication}PublicationTo${repository}Repository")
        }
    }
}