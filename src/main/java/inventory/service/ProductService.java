package inventory.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import inventory.dao.CategoryDAO;
import inventory.dao.ProductInfoDAO;
import inventory.model.Category;
import inventory.model.Pagging;
import inventory.model.ProductInfo;
import inventory.util.ConfigLoader;

@Service
public class ProductService {
	
	@Autowired
	private CategoryDAO<Category> categoryDAO;
	
	@Autowired
	private ProductInfoDAO<ProductInfo> productInfoDAO;
	
	private static final Logger log = Logger.getLogger(ProductService.class);
	
	public void saveCategory(Category category) throws Exception {
		log.info("Insert category "+category.toString());
		category.setActiveFlag(1);
		category.setCreateddate(new Date());
		category.setUpdateddate(new Date());
		categoryDAO.save(category);
	}
	public void updateCategory(Category category) throws Exception  {
		log.info("Update category "+category.toString());
		category.setUpdateddate(new Date());
		categoryDAO.update(category);
	}
	public void deleteCategory(Category category)  throws Exception {
		category.setActiveFlag(0);
		category.setUpdateddate(new Date());
		log.info("Delete category "+category.toString());
		categoryDAO.update(category);
	}
	public List<Category> findCategory(String property , Object value){
		log.info("=====Find by property category start====");
		log.info("property ="+property +" value"+ value.toString());
		return categoryDAO.findByProperty(property, value);
	}
	
	public List<Category> getAllCategory(Category category, Pagging pagging){
		log.info("show all category");
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap();
		if(category!=null) {
			if(category.getId()!=null && category.getId()!=0) {
				queryStr.append(" and model.id=:id");
				mapParams.put("id", category.getId());
			}
			if(category.getCode()!=null && !StringUtils.isEmpty(category.getCode())) {
				queryStr.append(" and model.code=:code");
				mapParams.put("code", category.getCode());
			}
			if(category.getName()!=null && !StringUtils.isEmpty(category.getName()) ) {
				queryStr.append(" and model.name like :name");
				mapParams.put("name", "%"+category.getName()+"%");
			}
		}
		return categoryDAO.findAll(queryStr.toString(), mapParams,pagging);
	}
	
	public Category	 findByIdCategory(int id) {
		
		log.info("Find by Category ID");
		return categoryDAO.findById(Category.class, id);
	}
	
////////////////////////	ProductInfo     //////////////////////////////
	public void saveProductInfo(ProductInfo productInfo) throws Exception {
		log.info("Insert productInfo "+productInfo.toString());
		productInfo.setActiveFlag(1);
		productInfo.setCreateddate(new Date());
		productInfo.setUpdateddate(new Date());
		String fileName = productInfo.getMultipartFile().getOriginalFilename();
		processUploadFile(productInfo.getMultipartFile(),fileName);
		productInfo.setImageUrl("/upload/"+ fileName);
		productInfoDAO.save(productInfo);
	}
	public void updateProductInfo(ProductInfo productInfo) throws Exception {
		log.info("Update productInfo "+productInfo.toString());
		
		
		if(!productInfo.getMultipartFile().getOriginalFilename().isEmpty()) {
			
			String fileName =  System.currentTimeMillis()+"_"+productInfo.getMultipartFile().getOriginalFilename();
			processUploadFile(productInfo.getMultipartFile(), fileName);
			productInfo.setImageUrl("/upload/"+fileName);
		}
		productInfo.setUpdateddate(new Date());
		productInfoDAO.update(productInfo);
	}
	public void deleteProductInfo(ProductInfo productInfo)  throws Exception {
		productInfo.setActiveFlag(0);
		
		productInfo.setUpdateddate(new Date());
		log.info("Delete productInfo "+productInfo.toString());
		productInfoDAO.update(productInfo);
	}
	public List<ProductInfo> findProductInfo(String property , Object value){
		log.info("=====Find by property productInfo start====");
		log.info("property ="+property +" value"+ value.toString());
		return productInfoDAO.findByProperty(property, value);
	}
	
	public List<ProductInfo> getAllProductInfo(ProductInfo productInfo, Pagging pagging){
		log.info("show all productInfo");
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap();
		if(productInfo!=null) {
			if(productInfo.getId()!=null && productInfo.getId()!=0) {
				queryStr.append(" and model.id=:id");
				mapParams.put("id", productInfo.getId());
			}
			if(productInfo.getCode()!=null && !StringUtils.isEmpty(productInfo.getCode())) {
				queryStr.append(" and model.code=:code");
				mapParams.put("code", productInfo.getCode());
			}
			if(productInfo.getName()!=null && !StringUtils.isEmpty(productInfo.getName()) ) {
				queryStr.append(" and model.name like :name");
				mapParams.put("name", "%"+productInfo.getName()+"%");
			}
		}
		return productInfoDAO.findAll(queryStr.toString(), mapParams,pagging);
	}
	
	public ProductInfo	 findByIdProductInfo(int id) {
		
		log.info("Find by ProductInfo ID");
		return productInfoDAO.findById(ProductInfo.class, id);
	}
	
	private void processUploadFile(MultipartFile multipartFile,String fileName) throws IllegalStateException, IOException {
		if(!multipartFile.getOriginalFilename().isEmpty()) {
			//Trả về đường dẫn tới file
			File dir = new File(ConfigLoader.getInstance().getValue("upload.location"));	//		Config.properties	
			if(!dir.exists()) {
				//Chua tạo thì tạo
				dir.mkdirs();
			}
			//Rename filename 
			File file = new File(ConfigLoader.getInstance().getValue("upload.location"),fileName);
			//Coppy multipartFile ==> file
			multipartFile.transferTo(file);
		}
	}
}
