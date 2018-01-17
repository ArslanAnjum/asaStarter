# asaStarter

This repository contains empty project structure to get you started in building secured crud with spring and angularjs, using the power of [Angular Spring Api](https://github.com/ArslanAnjum/angularSpringApi)

## Example:

Lets build a web application which is movies directory. So we would have following entities:

1. **Movie**     (Name, Airing Date, Category, Tags, Actors, Country, Was It A Success)
2. **Category**  (Category Name)
3. **Tag**       (Tag Name)
4. **Actor**     (Actor Name, Age, Country)
5. **Country**   (Country Name)



We would follow folling steps to make this web application:

### 1. Clone asaStarter
    git clone https://github.com/ArslanAnjum/asaStarter.git
    
### 2. Edit Datasource
1. We are using postgres for this example. If you haven't installed it, please install it first!
2. Datasource is defined in [application.properties](src/main/resources/application.properties)
3. Define a new database 'moviecatalog' in postgres and specify the same as datasource. i.e., spring.datasource.url=jdbc:postgresql://localhost:5432/moviecatalog


