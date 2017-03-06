package net.ssanj.provide

object ArgParser {

  import Defaults._

  private def toggle(name: String, shortName: Option[String] = None)(f: ProvideConfig => Unit)(op: scopt.OptionParser[ProvideConfig]): Unit = {
    val opDef = op.opt[Unit](name)
    shortName.map(opDef.abbr).getOrElse(opDef) action { (_, c) =>
      f(c)
      op.terminate(Right(()))
      c
    }
  }

  private def setPort(op: scopt.OptionParser[ProvideConfig]): Unit = {
    op.arg[Int]("<port>").
      optional().
      action { (port, config) => config.copy(port  = SystemPort.open(port)) }.
      text(s"""The port to run Provide on. Defaults to ${PORT}""")
  }

  private def setLocation(op: scopt.OptionParser[ProvideConfig]): Unit = {
    val currentDir = File.getCanonicalDirPath(LOCATION).getOrElse("<could not be determined>")
    op.arg[String]("<location>").
      optional().
      action { (location, config) => config.copy(location  = SystemPath.use(location)) }.
      text(s"""The directory from which to run Provide on. Defaults to ${currentDir}""")
  }

  lazy val parser = new scopt.OptionParser[ProvideConfig]("Provide") {
    head(s"$TITLE")
    toggle("help", Option("h"))(_ => showUsage)(this)
    toggle("version", Option("v"))(_ => println(s"$TITLE"))(this)
    setPort(this)
    setLocation(this)
    showUsageOnError
    note(s"${NEWLINE}Please see https://github.com/ssanj/provide for more examples.")
  }
}