package net.ssanj.provide

import unfiltered.request._
import unfiltered.response._

object Echo extends unfiltered.filter.Plan {
  def intent = {
    case Path(Seg(p :: Nil)) => ResponseString(s"${p} -> Hello World")
  }
}

object HelloWorld {
  def main(args: Array[String]) {
    unfiltered.jetty.Server.anylocal.plan(Echo).run
  }
}

