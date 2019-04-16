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
    public Response getAllAnzeige() throws IOException {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(repo.getAllAnzeige()).build();
    }


    @GET
    @Path("/searchbytitel/{titel}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByTitel(@PathParam("titel") String titel) throws IOException { return Response.ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Headers",
                    "origin, content-type, accept, authorization")
            .header("Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .entity(repo.searchByTitel(titel)).build(); }


    @GET
    @Path("/getAnzeigeMitKommentare/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnzeigeMitKommentare(@PathParam("id") int id) throws IOException { return Response.ok().entity(repo.getAnzeigeMitKommentare(id)).build(); }

    @GET
    @Path("/addanzeige/{titel}/{text}/{preis}/{ersteller}/{kategorie}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAnzeige(@PathParam("titel") String titel,
                               @PathParam("text") String text,
                               @PathParam("preis") Double preis,
                               @PathParam("ersteller") String ersteller,
                               @PathParam("kategorie") String kategorie) {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(repo.addAnzeige(titel, text, preis, ersteller, kategorie)).build();
    }

    @GET
    @Path("/kaufen/{id}/{benutzername}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response kaufAnzeige(@PathParam("id") int id, @PathParam("benutzername") String benutzername) { return Response.ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Headers",
                    "origin, content-type, accept, authorization")
            .header("Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .entity(repo.kaufAnzeige(id, benutzername)).build(); }

    @GET
    @Path("/deletebyid/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAnzeigeById(@PathParam("id") int id) { return Response.ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Headers",
                    "origin, content-type, accept, authorization")
            .header("Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .entity(repo.deleteAnzeigeById(id)).build(); }

    @GET
    @Path("/update/{id}/{titel}/{text}/{preis}/{kategorie}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnzeige(@PathParam("id") int id,
                                  @PathParam("titel") String titel,
                                  @PathParam("text") String text,
                                  @PathParam("preis") Double preis,
                                  @PathParam("kategorie") String kategorie) { return Response.ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Headers",
                    "origin, content-type, accept, authorization")
            .header("Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .entity(repo.updateAnzeige(id, titel, text, preis, kategorie)).build(); }

    @GET
    @Path("/getAnzeigeById/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnzeigeById(@PathParam("id") int id) {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(repo.getAnzeigeById(id)).build();
    }

    @GET
    @Path("/getAnzeigeKommentare/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnzeigeKommentare(@PathParam("id") int id) throws IOException {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(repo.getAnzeigeKommentare(id)).build(); }
}
