package net.ssanj.provide

import java.io.{File => JFile}

sealed abstract case class SystemPort(value: Int)

object SystemPort {
  def open(port: Int): Option[SystemPort] = {
    if (port > 0) Option(new SystemPort(port) {}) else None
  }
}

sealed abstract case class SystemPath(value: JFile) {
  val file = value.getCanonicalFile
  val path = value.getCanonicalPath
}

object SystemPath {

  val currentDir = new SystemPath(new JFile(".")) {}

  def use(path: String): Option[SystemPath] = {
    val file = new JFile(path)
    if (File.isDir(file)) Option(new SystemPath(file) {}) else None
  }
}

sealed trait ServerType { val name: String }
object ServerType {
  def get(name: String): ServerType =
    if (JettyType.name == name.toLowerCase) JettyType else NettyType
}

case object NettyType extends ServerType { val name = "netty" }
case object JettyType extends ServerType { val name = "jetty" }


final case class ProvideConfig(serverType: Option[ServerType], port: Option[SystemPort], location: Option[SystemPath])