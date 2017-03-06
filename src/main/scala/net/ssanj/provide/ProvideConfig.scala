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


final case class ProvideConfig(port: Option[SystemPort], location: Option[SystemPath])