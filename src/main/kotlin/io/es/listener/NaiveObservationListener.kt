package io.es.listener

import mu.KotlinLogging
import org.eclipse.leshan.core.observation.Observation
import org.eclipse.leshan.core.response.ObserveResponse
import org.eclipse.leshan.server.observation.ObservationListener
import org.eclipse.leshan.server.registration.Registration
import java.lang.Exception

class NaiveObservationListener : ObservationListener {

  override fun onResponse(observation: Observation, registration: Registration, response: ObserveResponse) {
    logger.info { "[ObservationOnResponse]" }
    logger.info { observation }
    logger.info { response }
  }

  override fun onError(observation: Observation, registration: Registration, exception: Exception) {
    logger.warn { "[ObservationOnError]" }
    logger.warn { observation }
    logger.warn { exception }
  }

  override fun newObservation(observation: Observation, registration: Registration) {
    logger.info { "[ObservationNewObservation]" }
    logger.info { observation }
  }

  override fun cancelled(observation: Observation) {
    logger.info { "[ObservationCancelled]" }
    logger.info { observation }
  }

}

private val logger = KotlinLogging.logger {}
