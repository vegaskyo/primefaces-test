/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.query.DataQuery;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ManagedBean(name = "login")
@SessionScoped
public class LoginController implements Serializable {

    private String username;
    private String password;
    private DataQuery query = new DataQuery();

    public String loginControl() {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean res = passwordEncoder.matches(password, query.findHashpwd(username));

        System.out.println(query.findHashpwd(username));
        if (query.loginControl(username) && res) {
            return "home.xhtml?faces-redirect=true";
        }

        PrimeFaces.current().executeScript("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Tai Khoan Va Mat Khau Khong Dung!"));
        return "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
