# asaStarter

This repository contains empty project structure to get you started in building secured crud with spring and angularjs, using the power of [Angular Spring Api](https://github.com/ArslanAnjum/angularSpringApi)

## Example:

Lets build a web application which is movies directory. So we would have following entities:

**Movie**     (Name, Airing Date, Category, Tags, Actors, Country, Was It A Success)</br>
**Category**  (Category Name)</br>
**Tag**       (Tag Name)</br>
**Actor**     (Actor Name, Age, Country)</br>
**Country**   (Country Name)</br>



We would follow folling steps to make this web application:

### 1- Clone asaStarter
    git clone https://github.com/ArslanAnjum/asaStarter.git
    
### 2- Edit Datasource
We are using postgres for this example. If you haven't installed it, please install it first!</br>
Datasource is defined in [application.properties](src/main/resources/application.properties)</br>
Define a new database 'moviecatalog' in postgres and specify the same as datasource.</br>
    It would look something like this spring.datasource.url=jdbc:postgresql://localhost:5432/moviecatalog
