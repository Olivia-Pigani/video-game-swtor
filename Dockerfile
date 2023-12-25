FROM ubuntu:latest
LABEL authors="olivi"

ENTRYPOINT ["top", "-b"]