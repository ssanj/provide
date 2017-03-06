package net.ssanj.provide

import unfiltered.response._
import java.io.{File => JFile}
import java.nio.file.Files
import File.{isDir, isFile, mimeType}

trait FileServerPlanSupport {

  def serveFile(sysPath: SystemPath, file: String): unfiltered.response.ResponseFunction[Any] = {
    val requestedFile = new JFile(sysPath.path , file).getCanonicalFile

    if (isFile(requestedFile)) {
      val contentType = mimeType(requestedFile)
      //TODO: We should stream this to the client
      val byteArray = Files.readAllBytes(requestedFile.toPath)
      ContentType(contentType) ~> ResponseBytes(byteArray)
    } else if (isDir(requestedFile)) BadRequest ~> ResponseString(s"Directory browsing is disabled")
    else BadRequest ~> ResponseString(s"Unknown file: $requestedFile")
  }

  def index(sysPath: SystemPath) = Html {
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Provide</title>
      </head>
      <body>
        <div style="font-size: 1000%;text-align: center;font-family: monospace">Pro<span style="color: lightsalmon">v</span>ide</div>
          <div style="font-size: 120%;text-align: center;font-family: sans-serif;font-weight:lighter">{sysPath.path}</div>
      </body>
    </html>
  }

  def faviconBytes: Array[Byte] = {
    import scala.collection.mutable.ListBuffer
    import java.io.InputStream

    var in: InputStream = null
    try {
      in  = Thread.currentThread().getContextClassLoader().getResourceAsStream("favicon.ico")
      val buf = ListBuffer[Byte]()
      var b = in.read()
      while (b != -1) {
        buf.append(b.byteValue)
        b = in.read()
      }
      buf.toArray
    } finally {
      if (in != null) in.close
    }
  }

  def favicon(): unfiltered.response.ResponseFunction[Any] = {
    File.mimeType(".ico").fold(BadRequest ~> ResponseString("Unknown mimetype: .ico")){ iconMime =>
      ContentType(iconMime) ~> ResponseBytes(faviconBytes)
    }

  }
}