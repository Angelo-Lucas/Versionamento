/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Login;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author angelo.lucas
 */
public class LoginDao {
    public void update(Login log) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho_VersionamentoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(log);
        em.getTransaction().commit();
    }

    public void remove(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho_VersionamentoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(em.getReference(Login.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public List<Login> list() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho_VersionamentoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Login> list = em.createQuery("Select a From versionamento a", Login.class).getResultList();
        return list;
    }
    
    public void persist(Login log) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho_VersionamentoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(log);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
