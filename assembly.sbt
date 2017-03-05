assemblyJarName in assembly := s"${name.value}-${version.value}"

test in assembly := {}

mainClass in assembly := Some("net.ssanj.provide.ProvideMain")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties")  => MergeStrategy.discard
  case PathList("META-INF", "INDEX.LIST")  => MergeStrategy.discard
  case PathList("META-INF", "MANIFEST.MF")  => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

assemblyOption in assembly := {
  (assemblyOption in assembly).value.copy(prependShellScript =
    Some(sbtassembly.AssemblyPlugin.defaultShellScript))
}