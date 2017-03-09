package net.ssanj.provide

object ProvideMain extends App {
  println(Banner.banner)

  ArgParser.parser.parse(args, ProvideConfig(None, None, None)) match {
    case Some(conf) =>
      val port = conf.port.map(_.value).getOrElse(Defaults.PORT)
      val location = conf.location.getOrElse(SystemPath.currentDir)
      val server = conf.serverType.map(_.name).getOrElse(Defaults.SERVER_TYPE.name)
      println(s"server: ${server}")
      println("host  : localhost")
      println(s"port  : ${port}")
      println(s"root  : ${location.path}")
      println
      println("add an index.html to the root dir to customise")
      unfiltered.netty.Server.local(port).plan(new NettyFileServerPlan(location)).run
      dispatch.Http.shutdown()

    case None => println("Could not parse arguments. Please use -h or --help to view arguments.")
  }
}