package gormExample

import grails.persistence.Entity
import grails.test.hibernate.HibernateSpec

class MultiRelationSpec extends HibernateSpec {

    @Override
    List<Class> getDomainClasses() {
        [Pet, Person]
    }

    def setup() {
    }

    def cleanup() {
    }

    void "Test setup"() {
        expect: "I can create my local entities"
        new Person(name: "name").save(flush: true, failOnError: true)
        new Pet(name: "name").save(flush: true, failOnError: true)
    }

    void "Test adding a relationship"() {
        expect:
        Person p = new Person(name: "person").save()
        Pet friendly = new Pet(name: "friendly").save()
        Pet mean = new Pet(name: "mean")

        p.addToPetsILike(friendly)
        p.save(flush:true)

    }
}


@Entity
class Pet {
    String name
}

@Entity
class Person {
    String name

    static hasMany = [
            petsILike: Pet,
            petsIHate: Pet
    ]
}