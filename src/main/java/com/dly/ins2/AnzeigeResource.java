package com.dly.ins2;

import entities.Anzeige;
import impl.AnzeigeImpl;
import request.AnzeigeRequest;
import utils.AnzeigeDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("anzeige")
public class AnzeigeResource {

    AnzeigeDao repo = new AnzeigeImpl();

    @GET
    @Path("/anzeiges")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAnzeige() throws IOException { return Response.ok().entity(repo.getAllAnzeige()).build(); }

    @GET
    @Path("/searchbytitel/{titel}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByTitel(@PathParam("titel") String titel) throws IOException { return Response.ok().entity(repo.searchByTitel(titel)).build(); }

    @GET
    @Path("/getAnzeigeMitKommentare/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnzeigeMitKommentare(@PathParam("id") int id) throws IOException { return Response.ok().entity(repo.getAnzeigeMitKommentare(id)).build(); }

    @POST
    @Path("/addanzeige")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAnzeige(AnzeigeRequest anzeige) {
        return Response.ok().entity(repo.addAnzeige(anzeige)).build();
    }

    @POST
    @Path("/kaufen")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response kaufAnzeige(Anzeige anzeige) { return Response.ok().entity(repo.kaufAnzeige(anzeige)).build(); }

    @POST
    @Path("/deletebyid")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAnzeigeById(Anzeige anzeige) { return Response.ok().entity(repo.deleteAnzeigeById(anzeige)).build(); }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnzeige(Anzeige anzeige) { return Response.ok().entity(repo.updateAnzeige(anzeige)).build(); }

}
