package com.example.gestionpedidoscondao.domain.producto;

import com.example.gestionpedidoscondao.domain.DAO;
import com.example.gestionpedidoscondao.domain.ObjectDBUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductoDAO implements DAO<Producto> {

    @Override
    public List<Producto> getAll() {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        List<Producto> productos;
        try {
            TypedQuery<Producto> query = em.createQuery("FROM Producto", Producto.class);
            productos = query.getResultList();
        } finally {
            em.close();
        }
        return productos;
    }

    public void saveAll(List<Producto> productos) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            for (Producto producto : productos) {
                em.persist(producto);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Producto get(Long id) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Producto producto = null;
        try {
            producto = em.find(Producto.class, id);
        } finally {
            em.close();
        }
        return producto;
    }

    @Override
    public Producto save(Producto data) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(data);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return data;
    }

    @Override
    public void update(Producto data) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(data);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Producto data) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.contains(data) ? data : em.merge(data));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Producto findByName(String nombre) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Producto producto = null;
        try {
            TypedQuery<Producto> query = em.createQuery("FROM Producto WHERE nombre = :nombre", Producto.class);
            query.setParameter("nombre", nombre);
            producto = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return producto;
    }

    @Override
    public boolean remove(Producto o) {
        // Implementar según sea necesario
        return false;
    }
}
