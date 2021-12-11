package controller;

import gestion.ProductoGestion;
import gestion.UserGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Conexion;
import model.user;

@Named(value = "userController")
@SessionScoped

public class UserController extends user implements Serializable {

    public UserController() {
    }

    public List<user> getUsers() {
        return UserGestion.getUsers();
    }

    public String editUser(String idUsuario) {
        user aUser = UserGestion.getUser(idUsuario);
        if (aUser != null) {
            this.setUSER_AUTH_ID(aUser.getUSER_AUTH_ID());
            this.setUSER_NAME(aUser.getUSER_NAME());
            this.setPASSWORD(aUser.getPASSWORD());
            this.setUSER_ROL(aUser.getUSER_ROL());

            return "editar?faces-redirect=true";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existe la entrada.");
            FacesContext.getCurrentInstance().addMessage("editUserForm:usuario", msg);

            return "usuarios?faces-redirect=true";
        }

    }

    public String insertUser() {
        if (UserGestion.insertUser(this)) {

            return "usuarios?faces-redirect=true";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al ingresar un registro.");
            FacesContext.getCurrentInstance().addMessage("addUserForm:usuario", msg);

            return "añadir.xhtml";
        }

    }

    public String updateUser() {
        if (UserGestion.uptUser(this)) {

            return "usuarios?faces-redirect=true";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al modificar el registro.");
            FacesContext.getCurrentInstance().addMessage("editUserForm:usuario", msg);

            return "editar.xhtml";
        }

    }

    private static final String SQL_DELETE_USERS = "DELETE USER_AUTH WHERE USER_AUTH_ID=?";

    public String deleteUser(int id) {

        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(SQL_DELETE_USERS);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "usuarios.xhtml";
    }

    public List<String> getRol() {
        return UserGestion.getRol();
    }

}
