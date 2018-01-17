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
