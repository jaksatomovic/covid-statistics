# COVID STATISTICS APP

Get statistics for all countries about COVID-19
Fetch them by:
- DateRange [Date From - Date To] (required fields)
- Country (required field)

## API

In order to test application easy we have swagger-ui and postman collections (one for rapid api and one for application itself.

| File                   | PATH                                                                 |
|------------------------|----------------------------------------------------------------------|
| Rapid Collection       | [postman/COVID-19-API.json][PlRApi]                                  |
| Application Collection | [postman/COVID-19-APLICATION.json][PlApp]                            |
| Swagger UI             | [swagger-ui](http://localhost:9017/covid-statistics/swagger-ui.html) |

## Tech

Technologies used while developing are

- Spring Boot running on Java 8
- Postgres
- Liquibase
- Git

And of course Application itself is open source with a [public repository][git-repo-url]
on GitLab.

## Installation

Application requires Postgres Database and Java 8 to run.

Enter the postgres shell and create database using following commands.

```sh
psql
create database covid_statistics;
create user covid_statistics with login encrypted password 'covid_statistics';
grant all privileges on database covid_statistics to covid_statistics;
```

Application will take care of the rest once it is started. 
Just open app in preferred IDE and start it


## Development

Application is multimodule project which consists of
- covid-statistics-api
- covid-statistics-commons
- covid-statistics-web
- covid-statistics-core

All business logic is located inside covid-statistics-core module.
Idea behind structure inside core module is package-by-feature.

Each operation goes through series of steps:
1. Request validation - check if all required fields are set
2. Context creation - creation of context that contain all the needed data for next phases of the flow
3. Preconditions validation - each operation has a certain preconditions that need to be valid
4. Resource provisioning - changing the state of the resource
5. Response generation - generation of the response based on provided context

## TODO

- [ ] Add JWT
- [x] RapidApi Properties
- [x] Implement Table Mapping
- [x] Documentation
- [ ] Increase Test Coverage
- [ ] Add H2 for tests
- [ ] Extract RapidApiClient into separate module
- [ ] Cleanup & Refactor


## License

MIT

**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[git-repo-url]: <https://github.com/joemccann/dillinger.git>

[PlRApi]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
[PlApp]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
