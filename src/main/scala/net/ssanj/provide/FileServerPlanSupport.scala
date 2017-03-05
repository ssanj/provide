package net.ssanj.provide

import unfiltered.response._
import java.io.{File => JFile}
import java.nio.file.Files
import File.{isDir, isFile, mimeType}

trait FileServerPlanSupport {

  def serveFile(file: String): unfiltered.response.ResponseFunction[Any] = {
    val requestedFile = new JFile(".", file).getCanonicalFile

    if (isFile(requestedFile)) {
      val contentType = mimeType(requestedFile)
      //TODO: We should stream this to the client
      val byteArray = Files.readAllBytes(requestedFile.toPath)
      ContentType(contentType) ~> ResponseBytes(byteArray)
    } else if (isDir(requestedFile)) BadRequest ~> ResponseString(s"Directory browsing is disabled")
    else BadRequest ~> ResponseString(s"Unknown file: $requestedFile")
  }

  lazy val location = new java.io.File(".").getCanonicalPath

  def index() = Html {
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Provide</title>
      </head>
      <body>
        <div style="font-size: 1000%;text-align: center;font-family: monospace">Pro<span style="color: lightsalmon">v</span>ide</div>
          <div style="font-size: 120%;text-align: center;font-family: sans-serif;font-weight:lighter">{location}</div>
      </body>
    </html>
  }
}