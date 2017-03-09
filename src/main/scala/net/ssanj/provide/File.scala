package net.ssanj.provide

import java.io.{File => JFile}
import unfiltered.request.Mime

object File {
  object Exists {
    def unapply(file: JFile): Option[Unit] =
      if (exists(file)) Option({}) else None
  }

  def exists(file: JFile): Boolean = file.exists

  def isFile(file: JFile): Boolean = exists(file) && file.isFile

  def isDir(file: JFile): Boolean = exists(file) && file.isDirectory

  def getCanonicalDir(path: String): Option[JFile] = {
    val dir = new JFile(path).getCanonicalFile
    if (isDir(dir)) Option(dir) else None
  }

  def getCanonicalDirPath(path: String): Option[String] = getCanonicalDir(path).map(_.getCanonicalPath)

  object MimeType {
   def unapply(file: JFile): Option[String] = Mime.unapply(file.getAbsolutePath)
  }

  def mimeType(file: JFile): String = MimeType.unapply(file).getOrElse("text/plain")

  def mimeType(ext: String): Option[String] = Mime.types.get(ext)
}
