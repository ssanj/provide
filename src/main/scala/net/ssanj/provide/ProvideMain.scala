package net.ssanj.provide

import org.slf4j.LoggerFactory

object ProvideMain extends App {
  val logger = LoggerFactory.getLogger("ProvideMain")
  logger.info("host: localhost")
  logger.info("port: 8080")
  logger.info(s"serving: ${new java.io.File(".").getAbsolutePath}")
  logger.info("server type: netty")
  unfiltered.netty.Server.local(8080).plan(NettyFileServerPlan).run
  dispatch.Http.shutdown()
}