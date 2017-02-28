package net.ssanj.provide

object ProvideMain extends App {
  // unfiltered.jetty.Server.anylocal.plan(FileServerPlan).run
  unfiltered.netty.Server.anylocal.plan(NettyFileServerPlan).run
  dispatch.Http.shutdown()
}