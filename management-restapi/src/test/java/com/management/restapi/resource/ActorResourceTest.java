package com.management.restapi.resource;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.management.entities.Actor;
import java.io.IOException;
import java.util.Date;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

public class ActorResourceTest {

    @Test
    public void getAllActors() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/s1/actor");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void getActorById_SC_OK() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/s1/actor/1");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void getActorById_SC_NOT_FOUND() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/s1/actor/1111");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void addActor_actor() {
        Actor actor = setActor();
        String baseurl = "http://localhost:8080/s1/actor/create";
        Client httpClient = ClientBuilder.newClient();
        WebTarget webTarget = httpClient.target(baseurl);
        Actor createdActor = webTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(actor, MediaType.APPLICATION_JSON), Actor.class);
        assertEquals(createdActor.getFirstname() + createdActor.getLastname(),
                actor.getFirstname() + actor.getLastname());
    }

    @Test
    public void addActor_status_SC_CREATED() {
        Actor actor = setActor();
        String baseurl = "http://localhost:8080/s1/actor/create";
        Client httpClient = ClientBuilder.newClient();
        WebTarget webTarget = httpClient.target(baseurl);
        Response response = webTarget.request().post(Entity.entity(actor, MediaType.APPLICATION_JSON));
        assertEquals(response.getStatus(), HttpStatus.SC_CREATED);
    }

    @Test
    public void updateActor() {
        Actor actor = setActor();
        String id = "1";
        String baseurl = "http://localhost:8080/s1/actor/update/";
        Client httpClient = ClientBuilder.newClient();
        WebTarget webTarget = httpClient.target(baseurl).path(id);
        Response response = webTarget.request().put(Entity.entity(actor, MediaType.APPLICATION_JSON));
        assertEquals(response.getStatus(), HttpStatus.SC_CREATED);
    }

    @Test
    public void deleteActor_SC_NO_CONTENT() {
        String id = "1";
        String baseurl = "http://localhost:8080/s1/actor/delete/";
        Client httpClient = ClientBuilder.newClient();
        WebTarget webTarget = httpClient.target(baseurl).path(id);
        Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).delete();
        assertEquals(response.getStatus(), HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void deleteActor_SC_NOT_FOUND() {
        String id = "11111";
        String baseurl = "http://localhost:8080/s1/actor/delete/";
        Client httpClient = ClientBuilder.newClient();
        WebTarget webTarget = httpClient.target(baseurl).path(id);
        Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).delete();
        assertEquals(response.getStatus(), HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void searchActorByName_OK() throws IOException{
        String param = "mateja";
        HttpUriRequest request = new HttpGet("http://localhost:8080/s1/actor/search?firstname=" + param);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void searchActorByName_NOT_FOUND() throws IOException{
        String param = "monika";
        HttpUriRequest request = new HttpGet("http://localhost:8080/s1/actor/search?firstname=" + param);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    private Actor setActor(){
        Actor actor = new Actor();
        actor.setBirthDate(new Date());
        String description = "description";
        actor.setDescription(description);
        String firstName = "firstname";
        actor.setFirstname(firstName);
        String lastname = "lastname";
        actor.setLastname(lastname);
        return actor;
    }
}