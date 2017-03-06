package net.ssanj.provide

object Banner {
  lazy val banner = scala.io.Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("banner.txt")).getLines.mkString("\n")
}