package com.dly.ins2;

import entities.Kommentar;
import impl.KommentarImpl;
import request.KommentReqest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.IOException;

@Path("kommentar")
public class KommentarResource {

    KommentarImpl repo = new KommentarImpl();

    @GET
    @Path("/kommentars")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllKommentar() throws IOException {
        return Response.ok().entity(repo.getAllKommentar()).build();
    }

    @GET
    @Path("/add/{id}/{benutzername}/{text}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addKommentar(@PathParam("id") int id, @PathParam("benutzername") String benutzername, @PathParam("text") String text) throws IOException {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(repo.addKommentar(id, benutzername, text)).build();
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteKommentar(Kommentar kommentar) throws IOException { return Response.ok().entity(repo.deleteKommentar(kommentar)).build(); }
}
