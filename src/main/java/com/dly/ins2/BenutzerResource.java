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

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest benutzer) { return Response.ok().entity(repo.login(benutzer)).build(); }

    @POST
    @Path("/addbenutzer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBenutzer(RegisterRequest benutzer) { return Response.ok().entity(repo.addBenutzer(benutzer)).build(); }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBenutzer(Benutzer benutzer) { return Response.ok().entity(repo.deleteBenutzer(benutzer)).build(); }

}
