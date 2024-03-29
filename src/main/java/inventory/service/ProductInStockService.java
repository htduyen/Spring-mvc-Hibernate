package inventory.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import inventory.model.ProductInStock;
import inventory.model.ProductInfo;
import inventory.dao.ProductInStockDAO;
import inventory.model.Invoice;
import inventory.model.Pagging;


@Service
public class ProductInStockService {
	
	 @Autowired
	private ProductInStockDAO<ProductInStock> productInStockDAO;
	
	private static final Logger log = Logger.getLogger(ProductInStockService.class);
	
	public List<ProductInStock> getAll(ProductInStock productInStock,Pagging paging){
		log.info("show all productInStock");
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();

		if(productInStock!=null && productInStock.getProductInfo()!=null) {
			if(!StringUtils.isEmpty(productInStock.getProductInfo().getCategory().getName())) {
				queryStr.append(" and model.productInfo.category.name like :cateName");
				mapParams.put("cateName","%"+productInStock.getProductInfo().getCategory().getName()+"%");
			}
			if(!StringUtils.isEmpty(productInStock.getProductInfo().getCode())) {
				queryStr.append(" and model.productInfo.code=:code");
				mapParams.put("code", productInStock.getProductInfo().getCode());
			}
			if( !StringUtils.isEmpty(productInStock.getProductInfo().getName()) ) {
				queryStr.append(" and model.productInfo.name like :name");
				mapParams.put("name", "%"+productInStock.getProductInfo().getName()+"%");
			}
		}
		return productInStockDAO.findAll(queryStr.toString(), mapParams,paging);
	}
	
	//Nhập hoá đơn, cập nhật SL, đơn giá nếu chưa có thì thêm mới 
	public void saveOrUpdate(Invoice invoice) throws Exception{
		log.info("product in stock ");
		if(invoice.getProductInfo()!=null) {
			int id = invoice.getProductInfo().getId();
			List<ProductInStock>  products= productInStockDAO.findByProperty("productInfo.id", id);
			ProductInStock product=null;
			if(products!=null && !products.isEmpty()) {
				product = products.get(0);
				log.info("update qty="+invoice.getQty()+" and price="+invoice.getPrice());
				if(invoice.getType()==2) {
					product.setQty(product.getQty()-invoice.getQty());//10 - 5 = 5 , 5-(-3) = 8
				}else {
					product.setQty(product.getQty()+invoice.getQty());
				}
			
				// type =1 receipt , type =2 issues
				if(invoice.getType()==1) {
					product.setPrice(invoice.getPrice());
				}
				product.setUpdateddate(new Date());
				productInStockDAO.update(product);
			
			}else if(invoice.getType()==1){
				log.info("insert to stock qty="+invoice.getQty()+" and price="+invoice.getPrice());
				product = new ProductInStock();
				ProductInfo productInfo = new ProductInfo();
				productInfo.setId(invoice.getProductInfo().getId());
				product.setProductInfo(productInfo);
				product.setActiveFlag(1);
				product.setCreateddate(new Date());
				product.setUpdateddate(new Date());
				product.setQty(invoice.getQty());
				product.setPrice(invoice.getPrice());
				productInStockDAO.save(product);	
			}
		}
	}

}
