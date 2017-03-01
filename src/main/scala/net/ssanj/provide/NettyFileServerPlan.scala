package net.ssanj.provide

import unfiltered.request._
import unfiltered.netty._
import scala.concurrent.{ExecutionContext,Future}

@io.netty.channel.ChannelHandler.Sharable
object NettyFileServerPlan extends future.Plan with ServerErrorResponse with FileServerPlanSupport {

  implicit def executionContext = ExecutionContext.Implicits.global

  def intent = {
    case GET(Path(xs)) => Future { serveFile(xs) }
  }
}
