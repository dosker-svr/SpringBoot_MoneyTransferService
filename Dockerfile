FROM openjdk:11
EXPOSE 5500
ADD target/SpringBoot_MoneyTransferService-0.0.1-SNAPSHOT.jar money_transfer.jar
ENTRYPOINT ["java", "-jar", "money_transfer.jar"]