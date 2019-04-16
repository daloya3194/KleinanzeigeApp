package com.dly.ins2;

import entities.Benutzer;
import impl.BenutzerImpl;
import request.LoginRequest;
import request.RegisterRequest;
import utils.BenutzerDao;
import utils.DBUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Path("benutzer")
public class BenutzerResource {

    BenutzerImpl repo = new BenutzerImpl();

    @GET
    @Path("/check")
    @Produces({MediaType.TEXT_PLAIN})
    public String connectionResult(){
        String db = "insdb4";
        String res = null;
        res = DBUtil.checkDatabaseExists(db) == true ? "db ist connected" : "db ist NOT connected";
        return res + "\n";
    }

    @GET
    @Path("/benutzers")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllBenutzer() throws IOException{
        return  Response.ok().entity(repo.getAllBenutzer()).build();
    }

    @GET
    @Path("/getBenutzerKommentare/{benutzername}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBenutzerKommentare(@PathParam("benutzername") String benutzername) throws IOException { return Response.ok().entity(repo.getBenutzerKommentare(benutzername)).build(); }

    @GET
    @Path("/login/{benutzername}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("benutzername") String benutzername,
                          @PathParam("password") String password) { return Response.ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Headers",
                    "origin, content-type, accept, authorization")
            .header("Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .entity(repo.login(benutzername, password)).build(); }

    @GET
    @Path("/addbenutzer/{benutzername}/{name}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBenutzer(@PathParam("benutzername") String benutzername,
                                @PathParam("name") String name,
                                @PathParam("password") String password) { return Response.ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Headers",
                    "origin, content-type, accept, authorization")
            .header("Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .entity(repo.addBenutzer(benutzername, name, password)).build(); }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBenutzer(Benutzer benutzer) { return Response.ok().entity(repo.deleteBenutzer(benutzer)).build(); }

    @GET
    @Path("/getBenutzerAnzeige/{benutzername}/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBenutzerAnzeige(@PathParam("benutzername") String benutzername, @PathParam("token") String token) {
//        String bntz = DBUtil.getUsername(token);
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(repo.getBenutzerAnzeige(benutzername)).build();
    }

    @GET
    @Path("/getBenutzerByBenutzername/{benutzername}/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBenutzerByBenutzername(@PathParam("benutzername") String benutzername, @PathParam("token") String token) {
       return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(repo.getBenutzerByBenutzername(benutzername)).build();
    }

}
