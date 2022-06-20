/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.query;

import com.entity.Account;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Long
 */
public class DataQuery {

    EntityManagerFactory emf;
    EntityManager em;

    public DataQuery() {
        emf = Persistence.createEntityManagerFactory("logintest");
        em = emf.createEntityManager();

        em.getTransaction().begin();
    }

    public boolean loginControl(String username) {
        try {
            Account a = em.createNamedQuery("Account.control", Account.class).setParameter("username", username).getSingleResult();
            if (a != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean findDuplicate(String username) {
        try {
            Account a = em.createNamedQuery("Account.findDuplicate", Account.class).setParameter("username", username).getSingleResult();
            if (a != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
        }
        return false;
    }

    public String findHashpwd(String username) {
        try {
            Query a = em.createNamedQuery("Account.findHashpwd").setParameter("username", username);
            if (a != null) {
                return (String) a.getSingleResult();
            }
            return "";
        } catch (Exception e) {
        }
        return "";
    }

}
