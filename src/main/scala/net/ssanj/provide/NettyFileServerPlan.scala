package net.ssanj.provide

import unfiltered.request._
import unfiltered.netty._
import scala.concurrent.{ExecutionContext,Future}
import java.io.{File => JFile}
import File.isFile
import Defaults.ROOT_INDEX

@io.netty.channel.ChannelHandler.Sharable
final class NettyFileServerPlan(path: SystemPath) extends future.Plan with ServerErrorResponse with FileServerPlanSupport {

  implicit def executionContext = ExecutionContext.Implicits.global

  def intent = {
    case GET(Path("/")) => Future {
      if (isFile(new JFile(path.value, ROOT_INDEX).getCanonicalFile)) serveFile(path, ROOT_INDEX)
      else index(path)
    }
    case GET(Path(xs)) => Future { serveFile(path, xs) }
  }
}
