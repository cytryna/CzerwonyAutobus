package com.diligentia.CzerwonyAutobus.service;

import com.diligentia.CzerwonyAutobus.exception.ShopNotFound;
import com.diligentia.CzerwonyAutobus.model.Shop;
import com.diligentia.CzerwonyAutobus.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Resource
	private ShopRepository shopRepository;

	@Override
	@Transactional
	public Shop create(Shop shop) {
		Shop createdShop = shop;
		return shopRepository.save(createdShop);
	}
	
	@Override
	@Transactional
	public Shop findById(int id) {
		return shopRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=ShopNotFound.class)
	public Shop delete(int id) throws ShopNotFound {
		Shop deletedShop = shopRepository.findOne(id);
		
		if (deletedShop == null)
			throw new ShopNotFound();
		
		shopRepository.delete(deletedShop);
		return deletedShop;
	}

	@Override
	@Transactional
	public List<Shop> findAll() {
		return shopRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor=ShopNotFound.class)
	public Shop update(Shop shop) throws ShopNotFound {
		Shop updatedShop = shopRepository.findOne(shop.getId());
		
		if (updatedShop == null)
			throw new ShopNotFound();
		
		updatedShop.setName(shop.getName());
		updatedShop.setEmplNumber(shop.getEmplNumber());
		return updatedShop;
	}

}
