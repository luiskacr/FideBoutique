package controller;

import gestion.LoginGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.user;

@Named(value = "loginController")
@SessionScoped

public class LoginController extends user implements Serializable {

    /**
     * Creates a new instance of UsuarioController
     */
    public LoginController() {
    }//constructor

    private String selectedTemplate = null;
    private String selectedTemplatePrincipal = null;

    public String valida() {

        user usuario = LoginGestion.valida(this.getUSER_NAME(), this.getPASSWORD());

        if (usuario != null) {

            this.setUSER_NAME(usuario.getUSER_NAME());
            this.setUSER_ROL(usuario.getUSER_ROL());
            selectedTemplate = "../resources/templates/plantillaADMIN.xhtml";
            selectedTemplatePrincipal = "./resources/templates/plantillaADMIN.xhtml";
//            //Selecciona la plantilla según el rol para todos los demás XHTML
//            if (usuario.getUSER_ROL().equals("Admin")) {
//                selectedTemplate = "../resources/templates/plantillaADMIN.xhtml"; //webapp
//            } else {
//                if (usuario.getUSER_ROL().equals("Supervisor")) {
//                    selectedTemplate = "../resources/templates/plantillaVEND.xhtml";
//                } else {
//                    if (usuario.getUSER_ROL().equals("dependent")) {
//                        selectedTemplate = "../resources/templates/plantillaRESP.xhtml";
//                    } else {
//                        if (usuario.getUSER_ROL() == null) {
//                            selectedTemplate = "../resources/templates/plantillaADMIN.xhtml";
//                        }
//                    }
//                }
//            }
//
//            //Selecciona la plantilla según el rol para 'principal.xhtml'
//            if (usuario.getUSER_ROL().equals("Admin")) {
//                selectedTemplatePrincipal = "./resources/templates/plantillaADMIN.xhtml"; //webapp
//            } else {
//                if (usuario.getUSER_ROL().equals("Supervisor")) {
//                    selectedTemplatePrincipal = "./resources/templates/plantillaVEND.xhtml";
//                } else {
//                    if (usuario.getUSER_ROL().equals("dependent")) {
//                        selectedTemplatePrincipal = "./resources/templates/plantillaRESP.xhtml";
//                    } else {
//                        if (usuario.getUSER_ROL() == null) {
//                            selectedTemplate = "../resources/templates/plantillaADMIN.xhtml";
//                        }
//                    }
//                }
//            }

            //retorna a la página principal
            return "principal?faces-redirect=true";

        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", "Usuario ó Contraseña Inválido");
            FacesContext.getCurrentInstance().addMessage("loginForm:clave", mensaje);
            return "index.xhtml";
        }
    }

    public String getSelectedTemplate() {
        return selectedTemplate;
    }

    public void setSelectedTemplate(String selectedTemplate) {
        this.selectedTemplate = selectedTemplate;
    }

    public String getSelectedTemplatePrincipal() {
        return selectedTemplatePrincipal;
    }

    public void setSelectedTemplatePrincipal(String selectedTemplatePrincipal) {
        this.selectedTemplatePrincipal = selectedTemplatePrincipal;
    }
}
