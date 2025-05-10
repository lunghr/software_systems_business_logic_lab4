package com.example.service


import com.example.model.*
import com.example.repos.CategoryRepository
import com.example.repos.ProductRepository
import com.example.model.key_class.CategoryKey
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.data.cassandra.core.InsertOptions
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository,
    private val cassandraTemplate: CassandraTemplate,
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

    fun createPaternalCategory(name: String): Category {
        val key = categoryRepository.findByKeyName(name)?.key

        if (key != null) {
            throw CategoryAlreadyExistsException(name)
        }

        val category = Category(key = CategoryKey(name, UUID.randomUUID()))
        val insertOptions = InsertOptions.builder().withIfNotExists().build()
        val result = cassandraTemplate.insert(category, insertOptions)

        if (!result.wasApplied()) {
            throw CategoryAlreadyExistsException(name)
        }

        return category
    }

    fun createChildCategory(parentName: String, name: String): Category {
        val parent = categoryRepository.findByKeyName(parentName)
            ?: throw CategoryNotFoundException(parentName)
        if (!parent.isParent) {
            throw CategoryIsNotParentException(parentName)
        }

        val key = categoryRepository.findByKeyName(name)?.key
        if (key != null) {
            throw CategoryAlreadyExistsException(name)
        }

        val category = Category(key = CategoryKey(name, UUID.randomUUID()), parentName = parentName)
        val insertOptions = InsertOptions.builder()
            .withIfNotExists()
            .build()
        val result = cassandraTemplate.insert(category, insertOptions)
        if (!result.wasApplied()) {
            throw CategoryAlreadyExistsException(name)
        }
        return category
    }


    fun getProductsByCategory(categoryName: String): List<Product> =
        categoryRepository.findByKeyName(categoryName)?.let { category ->
            category.takeIf { !it.isParent }
                ?.let { productRepository.findProductsByKeyCategoryId(category.key.id) }
                ?: throw CategoryIsParentException(categoryName)
        } ?: throw CategoryNotFoundException(categoryName)

    //TODO exception with incorrect id

    fun getCategoryById(id: UUID): Category =
        categoryRepository.findCategoryByKeyId(id)
            ?: throw CategoryNotFoundException(id.toString())

    fun isAvailableToAddProduct(category: Category): Boolean = when {
        !category.isParent -> true
        getChildrenOfCategory(category.key.name).isEmpty() ->
            categoryRepository.updateIsParentById(category.key.id, category.key.name, false)

        else -> false
    }

    fun delete(category: Category) {
        categoryRepository.delete(category)
    }

}
