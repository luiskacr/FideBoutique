/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author fabry
 */
@Named(value = "clientesControllerWS")
@RequestScoped
public class ClientesControllerWS {

    private String id;
    private String json = "xxxx";
    private String salida;

    private final String URI = "http://localhost/PaginaWeb-1.0-SNAPSHOT/resources/cliente";

    /**
     * Creates a new instance of ClientesControllerWS
     */
    public ClientesControllerWS() {
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    //getAll para el WS
    public void getAll() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URI);
        JsonArray response = target.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
        salida = response.toString();
    }

    //getCliente para el WS
    public void getCliente() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URI + "/" + id);
        JsonObject response = target.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        salida = response.asJsonObject().toString();
    }

}
