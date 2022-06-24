package io.arcotech.kraken

import io.quarkus.runtime.StartupEvent
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes


@ApplicationScoped
class KrakenStartup {

    fun init(@Observes ev: StartupEvent, vertx: Vertx) {
        val dop = DeploymentOptions()
        vertx.deployVerticle(Launcher(), dop)
    }
}