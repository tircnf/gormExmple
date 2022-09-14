## Gorm Multi relationship error.

With the following entities:


    @Entity
    class Pet {
        String name
    }

and

    @Entity
    class Person {
    String name
    
        static hasMany = [
                petsILike: Pet,
                petsIHate: Pet
        ]
    }

The following Join table is created:

`create table person_pet (person_petsilike_id bigint not null, pet_id bigint, person_petsihate_id bigint not null)`

That table has not-null constraints on both the `person_petsilike_id` and the `person_petsihate_id` column.

So when trying to insert a `Pet I like`, a not-null constraint fires on the `Pet I hate` column.


You can work around the issue by defining a new join table for each relationship.



