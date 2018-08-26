FROM openjdk:10
COPY ./Users/alanterriaga/IdeaProjects/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "AmazonDynamoDBLambda"]
