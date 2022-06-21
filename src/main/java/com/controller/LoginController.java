/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.entity.Account;
import com.query.DataQuery;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ManagedBean(name = "login")
@SessionScoped
public class LoginController implements Serializable {

    private String username;
    private String password;
    private DataQuery query = new DataQuery();

    private Account currentAccount;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    public static final String HOME_PAGE_REDIRECT = "/home.xhtml?faces-redirect=true";

    public static final String LOGOUT_PAGE_REDIRECT = "/logout.xhtml?faces-redirect=true";

    public String loginControl() {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean res = passwordEncoder.matches(password, query.findHashpwd(username));

        System.out.println(query.findHashpwd(username));
        if (query.loginControl(username) && res) {

            LOGGER.info("dang nhap thanh cong cho '{}'", username);
            return HOME_PAGE_REDIRECT;
        } else {
            LOGGER.info("dang nhap that bai '{}'", username);
//            PrimeFaces.current().executeScript("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Tai Khoan Va Mat Khau Khong Dung!"));
        }

        return "";
    }

    public String logoutControl() {
        String identifier = username;

        LOGGER.debug("invalidating session for '{}'", identifier);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        LOGGER.info("logout successfully for '{}'", identifier);
        return LOGOUT_PAGE_REDIRECT;
    }

    public boolean isLoggedIn() {
        return currentAccount != null;
    }

    public String isLoggedInForwardHome() {
        if (isLoggedIn()) {
            return HOME_PAGE_REDIRECT;
        }
        return null;
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

    public Account getCurrentAccount() {
        return currentAccount;
    }

}
