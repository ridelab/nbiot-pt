package io.es.listener

import io.es.common.show
import mu.KotlinLogging
import org.eclipse.leshan.core.observation.Observation
import org.eclipse.leshan.core.request.ObserveRequest
import org.eclipse.leshan.server.LwM2mServer
import org.eclipse.leshan.server.registration.Registration
import org.eclipse.leshan.server.registration.RegistrationListener
import org.eclipse.leshan.server.registration.RegistrationUpdate

class NaiveRegistrationListener(
  private val server: LwM2mServer,
  private val pathsToObserve: List<String> = emptyList()
) : RegistrationListener {

  override fun registered(new: Registration, previous: Registration?, observations: Collection<Observation>?) {
    logger.info { "[RegistrationRegistered]" }
    logger.info { new }
    try {
      for (o in pathsToObserve) {
        val request = ObserveRequest(o)
        logger.info { "[ObserveRequest]" }
        logger.info { request }
        val response = server.send(new, request, TIMEOUT)
        logger.info { "[ObserveResponse]" }
        logger.info { response }
      }
    } catch (e: InterruptedException) {
      logger.warn { e }
    }
  }

  override fun updated(update: RegistrationUpdate, updated: Registration, previous: Registration) {
    logger.debug { "[RegistrationUpdated]" }
    logger.debug { updated.brief() }
  }

  override fun unregistered(deleted: Registration, observations: Collection<Observation>, expired: Boolean, new: Registration?) {
    logger.info { "[RegistrationUnregistered]" }
    logger.info { deleted }
  }

  companion object {

    const val TIMEOUT = 5000L // ms

  }

}

fun Registration.brief(): String {
  return "Registration [identity=$identity, endpoint=${endpoint.show()}, id=${id.show()}]"
}

private val logger = KotlinLogging.logger {}
