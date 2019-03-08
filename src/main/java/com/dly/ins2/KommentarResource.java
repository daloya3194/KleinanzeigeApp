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

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addKommentar(KommentReqest kommentar) throws IOException {
        String resp = repo.addKommentar(kommentar);
        return Response.ok().entity(resp).build();
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteKommentar(Kommentar kommentar) throws IOException { return Response.ok().entity(repo.deleteKommentar(kommentar)).build(); }
}
