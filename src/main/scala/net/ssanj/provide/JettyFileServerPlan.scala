package net.ssanj.provide

import unfiltered.request._

object JettyFileServerPlan extends unfiltered.filter.Plan with FileServerPlanSupport {

  def intent = {
    case GET(Path(xs)) => serveFile(xs)
  }
}
