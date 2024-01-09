FROM maven:3.6.2-jdk-8 AS builder

WORKDIR /build

COPY ./Log4jRCE.java ./Log4jRCE.java

RUN javac Log4jRCE.java

FROM busybox:1.35

# Create a non-root user to own the files and run our server
RUN adduser -D static
USER static
WORKDIR /home/static

COPY --from=builder /build/Log4jRCE.class /home/static/

# Run BusyBox httpd
CMD ["busybox", "httpd", "-f", "-v", "-p", "8888"]
