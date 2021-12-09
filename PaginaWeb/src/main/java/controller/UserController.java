//package controller;
//
//import gestion.UserGestion;
//import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
//import java.io.Serializable;
//import java.util.List;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import model.user;
//
//@Named(value = "userController")
//@SessionScoped
//
//public class UserController extends user implements Serializable {
//
//    /**
//     * Creates a new instance of userController
//     */
//    public UserController() {
//    }
//    
//        //Lista con Roles Disponibles en DB
//    
//    public List<String> getRol() {
//        return UserGestion.getRol();
//    }
//    private List<String> rol = UserGestion.getRol();
//
//    public List<user> getUsers() {
//        return UserGestion.getUsers();
//    }
//    public String editUser(String idUsuario) {
//        user aUser = UserGestion.getUser(idUsuario);
//        if (aUser != null) {
//
//            this.setIdUsuario(aUser.getIdUsuario());
//            this.setIdRol(aUser.getIdRol());
//            this.setNombreUsuario(aUser.getNombreUsuario());
//
//            return "editar?faces-redirect=true";
//
//        } else {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existe la entrada.");
//            FacesContext.getCurrentInstance().addMessage("editUserForm:usuario", msg);
//
//            return "usuarios?faces-redirect=true";
//        }
//
//    }
//
//    public String insertUser() {
//        if (UserGestion.insertUser(this)) {
//
//            return "usuarios?faces-redirect=true";
//
//        } else {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al ingresar un registro.");
//            FacesContext.getCurrentInstance().addMessage("addUserForm:usuario", msg);
//
//            return "añadir.xhtml";
//        }
//
//    }
//
//    public String updateUser() {
//        if (UserGestion.updateUser(this)) {
//
//            return "usuarios?faces-redirect=true";
//
//        } else {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al modificar el registro.");
//            FacesContext.getCurrentInstance().addMessage("editUserForm:usuario", msg);
//
//            return "editar.xhtml";
//        }
//
//    }
//
//    public String deleteUser() {
//        if (UserGestion.deleteUser(this)) {
//
//            return "usuarios?faces-redirect=true";
//
//        } else {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al eliminar el registro.");
//            FacesContext.getCurrentInstance().addMessage("editUserForm:usuario", msg);
//
//            return "usuarios.xhtml";
//        }
//
//    }
//
//}
