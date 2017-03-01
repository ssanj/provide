package net.ssanj.provide

import unfiltered.response._
import java.io.{File => JFile}
import java.nio.file.Files
import File.{isDir, isFile, mimeType}

trait FileServerPlanSupport {

  def serveFile(file: String): unfiltered.response.ResponseFunction[Any] = {
    val requestedFile = new JFile(".", file).getAbsoluteFile

    if (isFile(requestedFile)) {
      val contentType = mimeType(requestedFile)
      //TODO: We should stream this to the client
      val byteArray = Files.readAllBytes(requestedFile.toPath)
      ContentType(contentType) ~> ResponseBytes(byteArray)
    } else if (isDir(requestedFile)) BadRequest ~> ResponseString(s"Directory browsing is disabled")
    else BadRequest ~> ResponseString(s"Unknown file: $requestedFile")
  }
}