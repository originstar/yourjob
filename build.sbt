name := """yourjob"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"


includeFilter in (Assets, LessKeys.less) := "*.less"

excludeFilter in (Assets, LessKeys.less) := "_*.less"

libraryDependencies ++= Seq(
    cache,
    javaCore,
    javaJdbc,
    javaJpa,
    "org.hibernate" % "hibernate-entitymanager" % "4.2.5.Final",
    "com.googlecode.genericdao" % "dao" % "1.2.0",
    "com.googlecode.genericdao" % "search-jpa-hibernate" % "1.2.0",
    "org.im4java" % "im4java" % "1.2.0",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-hibernate4" % "2.2.2",
    "commons-lang" % "commons-lang" % "2.1",
    "commons-io" % "commons-io" % "2.3",
    "org.springframework" % "spring-expression" % "3.2.3.RELEASE",
    "org.springframework" % "spring-aop" % "3.2.3.RELEASE",
    "org.springframework" % "spring-orm" % "3.2.3.RELEASE",
    "mysql" % "mysql-connector-java" % "5.1.19"
)


