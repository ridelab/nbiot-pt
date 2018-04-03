package io.es

import io.es.listener.NaiveObservationListener
import io.es.listener.NaiveRegistrationListener
import mu.KotlinLogging
import org.eclipse.leshan.server.LwM2mServer
import org.eclipse.leshan.server.californium.LeshanServerBuilder

fun main(args: Array<String>) {
  val options = options(args)
  create().register(options).observe().start()
}

fun options(args: Array<String>): Map<String, String> {
  val options = mutableMapOf<String, String>()
  args.indexOf("--paths").takeIf { it != -1 }?.let { args[it + 1] }?.also { options["paths"] = it }
  logger.info { "[Options]" }
  logger.info { options }
  return options
}

fun create(): LwM2mServer {
  val builder = LeshanServerBuilder()
  return builder.build()
}

fun LwM2mServer.register(options: Map<String, String>): LwM2mServer {
  val paths = options["paths"]?.split(",") ?: emptyList()
  registrationService.addListener(NaiveRegistrationListener(this, paths))
  return this
}

fun LwM2mServer.observe(): LwM2mServer {
  observationService.addListener(NaiveObservationListener())
  return this
}

private val logger = KotlinLogging.logger {}
