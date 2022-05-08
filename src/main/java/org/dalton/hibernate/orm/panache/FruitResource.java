package org.dalton.hibernate.orm.panache;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;

@Path("/fruits")
@ApplicationScoped
public class FruitResource {
    
    @Inject
    FruitRepository fruitRepository;

    @GET
    public Uni<List<Fruit>> get() {
        return fruitRepository.listAll(Sort.by("name"));
    }


    @GET
    @Path("/{id}")
    public Uni<Fruit> getSingle(Long id) {
        return fruitRepository.findById(id);
    }
    
    @POST
    public Uni<Response> create(Fruit fruit) {
        return fruitRepository.persistAndFlush(fruit)
            .onItem()
            .transform(inserted -> Response.created(URI.create("/fruits/" + inserted.id))
            .build());
    }
}
