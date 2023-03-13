# pw-service-auth

## Development

For the development it will be more convenient to have server auto-reload on any change.

To launch this from IDEA just run `start-dev` configuration.

To have same thing from command line launch to gradle task simultaneously:
```shell
./gradlew :run --args="-config=config/dev-application.yaml"
./gradlew build -t -x test -i # -x test to disable test task 
```

