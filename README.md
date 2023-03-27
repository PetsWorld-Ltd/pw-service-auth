# pw-service-auth


## Data scheme

[Схема данных](https://miro.com/app/board/uXjVMZQ_Gwk=/)

## Development

For the development it will be more convenient to have server auto-reload on any change.

To launch this from IDEA just run `start-dev` configuration.

To have same thing from command line launch to gradle task simultaneously:

```shell
./gradlew :run --args="-config=config/dev-application.yaml"
./gradlew build -t -x test -i # -x test to disable test task 
```

### Environment variable configuration for server

| Parameter       |  Default  | Description                 |
|:----------------|:---------:|:----------------------------|
| MONGODB_USER    |   root    | User name to access mongodb |
| MONGODB_PASS    |  example  | Password to access mongodb  |
| MONGODB_ADDRESS | localhost | Address of mongodb instance |
| MONGODB_PORT    |   27017   | Port of mongodb instance    |