/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import gestion.ClientesGestion;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Clientes;

/**
 * REST Web Service
 *
 * @author fabry
 */
@Path("cliente")
@RequestScoped
public class ClienteWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClienteWS
     */
    public ClienteWS() {
    }

     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Clientes> getClientes() {
        return ClientesGestion.getClientes();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Clientes getCliente(@PathParam("id") String id) {
        return ClientesGestion.getCliente(id);
    }
}
