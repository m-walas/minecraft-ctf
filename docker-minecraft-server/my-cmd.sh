cd docker-minecraft-server
docker build . -t mc:master &&          \
    docker run                          \
        -e EULA=TRUE                    \
        -e TYPE=PAPER                   \
        -e VERSION=1.14                 \
        -e SKIP_LOG4J_PATCHER=true      \
                                        \
        -p 25565:25565                  \
        -p 25575:25575 -ti mc:master    \



