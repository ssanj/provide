package net.ssanj.provide

import java.io.{File => JFile}
import java.nio.file.Files
import unfiltered.request._
import unfiltered.response._
import unfiltered.netty._
import scala.concurrent.{ExecutionContext,Future}

@io.netty.channel.ChannelHandler.Sharable
object NettyFileServerPlan extends future.Plan with ServerErrorResponse {

  implicit def executionContext = ExecutionContext.Implicits.global
  import File._

  def intent = {
    case GET(Path(xs)) =>
      Future {
        val requestedFile = new JFile(".", xs).getAbsoluteFile

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
}
