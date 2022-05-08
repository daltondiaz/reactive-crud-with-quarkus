package org.dalton.hibernate.orm.panache;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

@ApplicationScoped
public class FruitRepository implements PanacheRepository<Fruit> {

}
