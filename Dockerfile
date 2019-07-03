FROM ubuntu:18.04

RUN apt-get update
RUN apt-get install python2.7 -y
RUN apt-get update
RUN apt-get install openjdk-8-jdk -y
RUN apt-get update
RUN apt-get install maven -y
RUN apt-get update
RUN apt install gcc -y

RUN apt-get install curl -y

RUN VERSION=0.16.0; \
curl -sSL "https://github.com/facebook/infer/releases/download/v$VERSION/infer-linux64-v$VERSION.tar.xz" \
| tar -C /opt -xJ && \
ln -s "/opt/infer-linux64-v$VERSION/bin/infer" /usr/local/bin/infer
WORKDIR /usr/src/app
COPY . .
RUN mvn install
ENTRYPOINT ["java", "-jar",  "target/code-analyzer-0.1.0.jar", "> logs.out &"]