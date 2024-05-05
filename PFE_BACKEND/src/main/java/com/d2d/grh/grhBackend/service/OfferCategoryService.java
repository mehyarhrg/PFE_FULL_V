package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.entity.OfferCategory;
import com.d2d.grh.grhBackend.repository.OfferCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferCategoryService {

    private final OfferCategoryRepository offerCategoryRepository;

    @Autowired
    public OfferCategoryService(OfferCategoryRepository offerCategoryRepository) {
        this.offerCategoryRepository = offerCategoryRepository;
    }

    public OfferCategory saveCategory(OfferCategory offerCategory){
        return this.offerCategoryRepository.save(offerCategory);
    }

    public void deleteCategory(long id){
        this.offerCategoryRepository.deleteById(id);
    }

}
