# asaStarter

This repository contains empty project structure to get you started in building secured crud with spring and angularjs, using the power of [Angular Spring Api](https://github.com/ArslanAnjum/angularSpringApi)

## Example:

Lets build a web application which is movies directory. So we would have following entities:

1. **Movie**     (Movie Name, Airing Date, Category, Tags, Actors, Country, Was It A Success)
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

### 3. Model Classes
Lets define model classes. 
1. For this create a package com.arslan.asaStarter.module.core
2. Inside core package define 2 more packages i.e., model and repo
3. Inside model define a package projection
4. We would define our model classes in model package, Paginated Repositories in repo and projections in model.projection

Well a good paractice is to define model classes from least dependant to most dependant. This way wouldn't have to toggle between classes add associations later on. In our case the list would be Tag, Category, Country, Actor, Movie

Following are classes

1. **Tag.java**

        @Entity
        @Table(name="tag",schema="public")
        @Data
        @QueryEntity
        public class Tag {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name="tag_id")
            Integer tagId;

            @Column(name="tag_name")
            String tagName;
        }

2. **Category.java**

        @Entity
        @Table(name="category",schema="public")
        @Data
        @QueryEntity
        public class Category {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name="category_id")
            Integer categoryId;

            @Column(name="category_name")
            String categoryName;
        }
        
3. **Country**

        @Entity
        @Table(name="tag",schema="public")
        @Data
        @QueryEntity
        public class Tag {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name="tag_id")
            Integer tagId;

            @Column(name="tag_name")
            String tagName;
        }

4. **Actor**

        @Entity
        @Table(name="actor",schema="public")
        @Data
        @QueryEntity
        public class Actor {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name="actor_id")
            Integer actorId;

            @Column(name="actor_name")
            String actorName;

            @Column(name="age")
            Integer age;

            @ManyToOne
            @JoinColumn(name="country_id")
            Country country;
        }

5. **Movie**

        @Entity
        @Table(name="movie",schema="public")
        @Data
        @QueryEntity
        public class Movie {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name="movie_id")
            Integer movieId;

            @Column(name="movie_name")
            String movieName;

            @Column(name="airing_date")
            Date airingDate;

            @ManyToOne
            @JoinColumn(name="category_id")
            Category category;

            @ManyToMany
            @JoinTable(
                    name="movie_tags",
                    joinColumns = {@JoinColumn(name="movie_id",nullable = false,updatable = false)},
                    inverseJoinColumns = {@JoinColumn(name="tag_id",nullable = false,updatable = false)}
            )
            Set<Tag> tags = new HashSet<>(0);

            @ManyToMany
            @JoinTable(
                    name="movie_actors",
                    joinColumns = {@JoinColumn(name="movie_id",nullable = false,updatable = false)},
                    inverseJoinColumns = {@JoinColumn(name="actor_id",nullable = false,updatable = false)}
            )
            Set<Actor> actors = new HashSet<>(0);

            @ManyToOne
            @JoinColumn(name="country_id")
            Country country;

            @Column(name="success")
            Boolean success;
        }
        
### 4. Q Entities
QueryDsl requires the so called q entities created. For that open terminal and run mvn install. Once this command is completed you would see generate Q classes for each entity class that was annotated with @QueryEntity in targe/generatedsources

### 5. Defining Repositories
As Q Entities have been created lets create repos for each entity in the repo package (module/core/repo). If you are using intellij you might need to add target folder as source folder. For this go to File > Project Structure > Modules. Select generated sources folder in target and make it a source folder

1. **TagRepo.java**

        @Transactional
        public interface TagRepo
            extends
                PaginatedQueryDslRepository<Tag,Integer,QTag>{
        }
        
2. **CategoryRepo.java**

        @Transactional
        public interface CategoryRepo
            extends
                PaginatedQueryDslRepository<Category,Integer,QCategory>{
        }

3. **CountryRepo.java**

        @Transactional
        public interface CountryRepo
                extends
                PaginatedQueryDslRepository<Country,Integer,QCountry>{
        }
        
4. **ActorRepo.java**

        @Transactional
        public interface ActorRepo
            extends
                PaginatedQueryDslRepository<Actor,Integer,QActor>{
        }
        
5. **MovieRepo.java**

        @Transactional
        public interface MovieRepo
            extends
                PaginatedQueryDslRepository<Movie,Integer,QMovie>{
        }
        
### 6. Expose Ids for All Entities
We need to expose Ids (PK) for all entities that would be served by spring data rest. For this specify all classes in [CustomRestConfiguration.java](src/main/java/com/arslan/asaStarter/configuration/CustomRestConfiguration.java) as follows:

        @Configuration
        public class CustomRestConfiguration extends RepositoryRestConfigurerAdapter {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

                /**
                 * Uncomment and add all entities here. We need Ids to be exposed for proper functionality
                 *
                 * Example:
                 *
                 * config.exposeIdsFor(Person.class , Book.class , Library.class);
                 */
                config.exposeIdsFor(
                        Tag.class,
                        Category.class,
                        Country.class,
                        Actor.class,
                        Movie.class
                );
            }
        }

### 7. Create Projections Where Needed
We need to create projections so that we can display associations also on the CRUD page.</br>
We know that we want Movie and Actor crud pages to display associations like country, tags etc to be displayed.</br>
So we would create projections for these two entities in core/model/projection and we must name it 'detail'

1. **ActorDetailProjection**

        @Projection(name="detail",types={Actor.class})
        public interface ActorDetailProjection {

            Integer getActorId();
            String getActorName();
            Integer getAge();
            Country getCountry();
        }
        
2. **MovieDetailProjection**

        @Projection(name="detail",types = {Movie.class})
        public interface MovieDetailProjection {

            Integer getMovieId();
            String getMovieName();
            Date getAiringDate();
            Category getCategory();
            Set<Tag> getTags();
            Set<Actor> getActors();
            Country getCountry();
            Boolean getSuccess();
        }

### 8. Create Angular Controllers
For each entity we need to create a controller which would follow some set of rules for its creation. We need to create controllers in src/main/resources/static/app/controllers/. If this directory is not present create it.</br>
1. Naming convention for the controllers is entity name + Controller, where entity name is the name as used by spring data rest to expose different tables. For Example, as we have a class Tag.java, then SDR would expose it as /api/tags. We would use this entity name.
2. We need to specify all angular controllers in [controllersList.jsp](src/main/webapp/WEB-INF/views/main/includeFiles/controllersList.jsp)
3. We would define two variables in each angular controller which would configure the angularSpringApi for this page:
    * dataTableMetaData
        This variable would define all the elements and their properties that would be displayed as a table on the page and while editing an element.
    * createFormMetaData
        This variable would define all the elements that would be displayed on the form while creating a new element

Following would be the controllers</br>

1. **tagsController.js**

        'use strict';

        app.controller('tagsController',
                ['$scope','apiControllerTemplate',
                    function($scope,apiControllerTemplate){

                    $scope.init = function(csrfParamName, csrfToken, csrfHeaderName,server,entity){
                        var dataTableMetadata =
                        {
                            tagName 		:{iType:'input',required:true,editable:true,searchable:true,inGridVisible:true},
                        };

                        var createFormMetadata =
                        {
                            tagName 		:{iType:'input',required:true},
                        };

                        apiControllerTemplate
                        .buildControllerTemplate(
                                csrfParamName,
                                csrfToken,
                                csrfHeaderName,
                                server,
                                entity,
                                $scope,
                                dataTableMetadata,
                                createFormMetadata
                        );
                    }
                }]);
