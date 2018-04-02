package io.es

import mu.KotlinLogging

fun main(args: Array<String>) {
  logger.info { "It works" }
}

private val logger = KotlinLogging.logger {}
