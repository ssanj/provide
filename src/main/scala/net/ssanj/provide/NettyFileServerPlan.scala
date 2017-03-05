package net.ssanj.provide

import unfiltered.request._
import unfiltered.netty._
import scala.concurrent.{ExecutionContext,Future}
import java.io.{File => JFile}
import File.isFile

@io.netty.channel.ChannelHandler.Sharable
object NettyFileServerPlan extends future.Plan with ServerErrorResponse with FileServerPlanSupport {

  implicit def executionContext = ExecutionContext.Implicits.global

  lazy val rootIndex = "/index.html"

  def intent = {
    case GET(Path("/")) => Future {
      if (isFile(new JFile(".", rootIndex).getCanonicalFile)) serveFile(rootIndex)
      else index()
    }
    case GET(Path(xs)) => Future { serveFile(xs) }
  }
}
