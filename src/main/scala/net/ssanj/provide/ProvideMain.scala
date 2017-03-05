package net.ssanj.provide

object ProvideMain extends App {
  val banner = scala.io.Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("banner.txt")).getLines.mkString("\n")
  println(banner)
  println("host: localhost")
  println("port: 8080")
  println(s"root: ${new java.io.File(".").getCanonicalPath}")
  println("server type: netty")
  println
  println("add an index.html to the root dir to customise")
  unfiltered.netty.Server.local(8080).plan(NettyFileServerPlan).run
  dispatch.Http.shutdown()
}