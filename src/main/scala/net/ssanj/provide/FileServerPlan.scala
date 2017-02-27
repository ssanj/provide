package net.ssanj.provide

import java.io.{File => JFile}
import java.nio.file.Files
import unfiltered.request._
import unfiltered.response._


object File {
  object Exists {
    def unapply(file: JFile): Option[Unit] =
      if (exists(file)) Option({}) else None
  }

  def exists(file: JFile): Boolean = file.exists

  def isFile(file: JFile): Boolean = exists(file) && file.isFile

  def isDir(file: JFile): Boolean = exists(file) && file.isDirectory

  object MimeType {
   def unapply(file: JFile): Option[String] = Mime.unapply(file.getAbsolutePath)
  }

  def mimeType(file: JFile): String = MimeType.unapply(file).getOrElse("text/plain")
}

object FileServerPlan extends unfiltered.filter.Plan {

  import File._

  def intent = {
    case GET(Path(xs)) =>
      val requestedFile = new JFile(".", xs).getAbsoluteFile
      println(s"file: ${requestedFile}")

      val response: unfiltered.response.ResponseFunction[Any] =
        if (isFile(requestedFile)) {
          val contentType = mimeType(requestedFile)
          //TODO: We should stream this to the client
          val byteArray = Files.readAllBytes(requestedFile.toPath)
          ContentType(contentType) ~> ResponseBytes(byteArray)
        } else if (isDir(requestedFile)) BadRequest ~> ResponseString(s"Directory browsing is disabled")
        else BadRequest ~> ResponseString(s"Unknown file: $requestedFile")

      response
    }
}
