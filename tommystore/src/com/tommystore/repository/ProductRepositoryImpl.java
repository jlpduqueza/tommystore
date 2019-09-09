package com.tommystore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tommystore.domain.InventoryItem;
import com.tommystore.domain.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Product findProductById(Integer id) {
		return em.find(Product.class, id);
	}

	@Override
	public Product saveProduct(Product product) {
        if (product.getId() == null) {

        	InventoryItem inventoryItem = new InventoryItem();
        	inventoryItem.setQuantity(0);
        	inventoryItem.setProduct(product);
        	product.setInventoryItem(inventoryItem);
        	em.persist(product);
        	
	        product.setProductId("PROD-"+String.format("%04d", product.getId()));
        	em.merge(product);
        	
        } else {
            product = em.merge(product);
        }
        return product;
	}

	@Override
	public List<Product> getProductList() {
		TypedQuery<Product> query =  em.createQuery("From Product", Product.class);
		return query.getResultList();
	}

	@Override
	public void generateProductId(Product product) {
		TypedQuery<Product> query =  em.createQuery("From Product order by id DESC", Product.class);
		query.getFirstResult();
	}

	@Override
	public void deleteProductById(Integer id) {
		em.remove(em.find(Product.class, id).getInventoryItem());
		em.remove(em.find(Product.class, id));
	}

	@Override
	public Boolean isProductExistByNameAndCategoryId(String name, Integer id) {
		TypedQuery<Product> query =  em.createQuery("From Product where name = :name AND category.id = :id", Product.class);
		query.setParameter("name", name);
		query.setParameter("id", id);
		
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	@Override
	public List<Product> findProductByCategory(Integer id) {
		TypedQuery<Product> query =  em.createQuery("From Product where category.id = :id", Product.class);
		query.setParameter("id", id);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Boolean isNameValid(String name, Integer id) {
		TypedQuery<Product> query =  em.createQuery("From Product where name = :name AND id != :id", Product.class);
		query.setParameter("name", name);
		query.setParameter("id", id);
		
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	@Override
	public List<Product> getPopularProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		TypedQuery<Product> query =  em.createQuery("from Product where name like :keyword or category.name like :keyword", Product.class);
		query.setParameter("keyword", "%"+keyword+"%");
		return query.getResultList();
	}


}
