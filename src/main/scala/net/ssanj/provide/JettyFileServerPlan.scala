package net.ssanj.provide

import unfiltered.request._
import java.io.{File => JFile}
import Defaults.ROOT_INDEX

final class JettyFileServerPlan(path: SystemPath) extends unfiltered.filter.Plan with FileServerPlanSupport {

  def intent = {
    case GET(Path("/")) =>
      if (File.isFile(new JFile(path.value, ROOT_INDEX).getCanonicalFile)) serveFile(path, ROOT_INDEX)
      else index(path)

    case GET(Path(xs)) => serveFile(path, xs)
  }
}
