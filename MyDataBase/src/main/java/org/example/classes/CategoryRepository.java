package org.example.classes;

import org.example.entities.CategoryEntity;
import org.example.utils.HibernateHelper;
import org.hibernate.Session;
import org.hibernate.sql.ast.tree.expression.JsonExistsErrorBehavior;

import java.util.List;

public class CategoryRepository {
    Session session = null;
    public CategoryRepository() {
    }

    public void addCategory(String name){
        try{
            session = HibernateHelper.getSession();
            session.beginTransaction();

            var newCategory = new CategoryEntity(name);
            session.persist(newCategory);
            session.getTransaction().commit();
        }
        catch (Exception ex)
        {
            System.out.println("Exception: " + ex);
        }
        finally {
            if (session != null){
                session.close();
                session = null;
            }
        }
    }

    public List<CategoryEntity> getCategories(){
        try{
            session = HibernateHelper.getSession();
            return session.createQuery("FROM CategoryEntity", CategoryEntity.class).getResultList();
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex);
        }
        finally {
            session.close();
            session = null;
        }
        return null;
    }

    public void removeCategory(int id){
        try{
            session = HibernateHelper.getSession();
            session.beginTransaction();
            var category = session.find(CategoryEntity.class, id);
            if (category != null) {
                session.remove(category);
            }
            session.getTransaction().commit();
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex);
        }
        finally {
            if (session != null){
                session.close();
                session = null;
            }
        }
    }

    public void updateCategory(int id,String name){
        try {
            session = HibernateHelper.getSession();
            session.beginTransaction();
            var category = session.find(CategoryEntity.class,id);
            if (category != null){
                category.setName(name);
            }
            session.getTransaction().commit();
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex);
        }
        finally {
            if (session != null){
                session.close();
                session = null;
            }
        }
    }


}
