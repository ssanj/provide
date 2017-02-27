package net.ssanj.provide

object ProvideMain extends App {
  unfiltered.jetty.Server.anylocal.plan(FileServerPlan).run
}