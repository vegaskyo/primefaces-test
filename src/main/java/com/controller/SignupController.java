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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Long
 */
@ManagedBean(name = "signup")
@SessionScoped
public class SignupController implements Serializable {

    private String username;
    private String password;
    private String fullname;
    private String address;
    Account account = new Account();

    private DataQuery query = new DataQuery();

    //action signup
    public String signupControl() {

        if (query.findDuplicate(username)) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
        } else if (!username.isEmpty() && !password.isEmpty() && !fullname.isEmpty() && !address.isEmpty()) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("logintest");
            EntityManager em = factory.createEntityManager();

//            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String encodedPwd = passwordEncoder.encode(password);
//            System.err.println(encodedPwd);
            String hash = BCrypt.hashpw(password, BCrypt.gensalt(5));

            account.setUsername(username);
            account.setPassword(password);
            account.setHashpwd(hash);
            account.setFullname(fullname);
            account.setAddress(address);

            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ðang kí thành công! "));
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
