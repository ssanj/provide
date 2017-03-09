name := "provide"

organization := "net.ssanj"

version := "0.0.1"

scalaVersion := "2.12.1"

val unfilteredVersion = "0.9.0"

libraryDependencies ++= Seq(
  "ws.unfiltered"           %% "unfiltered-directives"   % unfilteredVersion,
  "ws.unfiltered"           %% "unfiltered-filter"       % unfilteredVersion,
  "ws.unfiltered"           %% "unfiltered-jetty"        % unfilteredVersion,
  "ws.unfiltered"           %% "unfiltered-netty-server" % unfilteredVersion,
  "net.databinder.dispatch" %% "dispatch-core"           % "0.12.0",
  "com.github.scopt"        %% "scopt"                   % "3.5.0",
  "ws.unfiltered"           %% "unfiltered-scalatest"    % unfilteredVersion % "test",
  "org.scalatest"           %% "scalatest"               % "3.0.1"           % "test",
  "org.scalacheck"          %% "scalacheck"              % "1.13.4"          % "test"
)

scalacOptions ++= Seq(
                      "-unchecked",
                      "-deprecation",
                      "-feature",
                      "-Xfatal-warnings",
                      "-Xlint:_",
                      "-Ywarn-dead-code",
                      "-Ywarn-inaccessible",
                      "-Ywarn-unused-import",
                      "-Ywarn-infer-any",
                      "-Ywarn-nullary-override",
                      "-Ywarn-nullary-unit"
                     )

