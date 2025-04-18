package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.models.*
import com.example.software_systems_business_logic_lab1.application.models.key_classes.CategoryKey
import com.example.software_systems_business_logic_lab1.application.repos.CategoryRepository
import com.example.software_systems_business_logic_lab1.application.repos.ProductRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository
) {

    fun getParentCategories(): List<Category> {
        return categoryRepository.findAll().filter { it.parentName == null }

            .takeIf { it.isNotEmpty() }
            ?: throw NoParentCategoriesFoundException()
    }

    fun getChildrenOfCategory(parentName: String): List<Category> =
        categoryRepository.findByKeyName(parentName)
            ?.let { categoryRepository.findByParentName(parentName) }
            ?: throw CategoryNotFoundException(parentName)

    fun createPaternalCategory(name: String): Category = Category(key = CategoryKey(name))
        .takeIf {
            val res = categoryRepository.saveIfNotExist(it.key.id, it.key.name, it.parentName, it.isParent)
            res.wasApplied()
        }
        ?: throw CategoryAlreadyExistsException(name)

    fun createChildCategory(parentName: String, name: String): Category =
        categoryRepository.findByKeyName(parentName)?.let { parent ->
            parent.isParent.takeIf { it }?.let {
                Category(key = CategoryKey(name), parentName = parentName).takeIf {
                    categoryRepository.saveIfNotExist(it.key.id, it.key.name, it.parentName, it.isParent).wasApplied()
                } ?: throw CategoryAlreadyExistsException(name)
            } ?: throw CategoryIsNotParentException(parentName)
        } ?: throw CategoryNotFoundException(parentName)


    fun getProductsByCategory(categoryName: String): List<Product> =
        categoryRepository.findByKeyName(categoryName)?.let { category ->
            category.takeIf { !it.isParent }
                ?.let { productRepository.findProductsByKeyCategoryId(category.key.id) }
                ?: throw CategoryIsParentException(categoryName)
        } ?: throw CategoryNotFoundException(categoryName)

    //TODO exception with incorrect id

    fun getCategoryById(id: UUID): Category =
        categoryRepository.findCategoryByKeyId(id) ?: throw CategoryNotFoundException(id.toString())


    fun isAvailableToAddProduct(category: Category): Boolean = when {
        !category.isParent -> true
        getChildrenOfCategory(category.key.name).isEmpty() ->
            categoryRepository.updateIsParentById(category.key.id, category.key.name, false)

        else -> false
    }

}
