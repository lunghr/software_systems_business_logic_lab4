package com.example.tmp


import com.example.model.Product
import com.example.repos.ProductRepository
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
//            ?: throw NoParentCategoriesFoundException()
            ?: throw RuntimeException("No parent categories found")
    }

    fun getChildrenOfCategory(parentName: String): List<Category> =
        categoryRepository.findByKeyName(parentName)
            ?.let { categoryRepository.findByParentName(parentName) }
//            ?: throw CategoryNotFoundException(parentName)
            ?: throw RuntimeException("Category not found")

    fun createPaternalCategory(name: String): Category {
        val key = categoryRepository.findByKeyName(name)?.key

        if (key != null) {
//            throw CategoryAlreadyExistsException(name)
            throw RuntimeException("Category already exists")
        }

        val category = Category(key = CategoryKey(name, UUID.randomUUID()))
        val insertOptions = InsertOptions.builder().withIfNotExists().build()
        val result = cassandraTemplate.insert(category, insertOptions)

        if (!result.wasApplied()) {
//            throw CategoryAlreadyExistsException(name)
            throw RuntimeException("Category already exists")
        }

        return category
    }

    fun createChildCategory(parentName: String, name: String): Category {
        val parent = categoryRepository.findByKeyName(parentName)
//            ?: throw CategoryNotFoundException(parentName)
            ?: throw RuntimeException("Category not found")
        if (!parent.isParent) {
//            throw CategoryIsNotParentException(parentName)
            throw RuntimeException("Category is not parent")
        }

        val key = categoryRepository.findByKeyName(name)?.key
        if (key != null) {
//            throw CategoryAlreadyExistsException(name)
            throw RuntimeException("Category already exists")
        }

        val category = Category(key = CategoryKey(name, UUID.randomUUID()), parentName = parentName)
        val insertOptions = InsertOptions.builder()
            .withIfNotExists()
            .build()
        val result = cassandraTemplate.insert(category, insertOptions)
        if (!result.wasApplied()) {
//            throw CategoryAlreadyExistsException(name)
            throw RuntimeException("Category already exists")
        }
        return category
    }


    fun getProductsByCategory(categoryName: String): List<Product> =
        categoryRepository.findByKeyName(categoryName)?.let { category ->
            category.takeIf { !it.isParent }
                ?.let { productRepository.findProductsByKeyCategoryId(category.key.id) }
//                ?: throw CategoryIsParentException(categoryName)
//        } ?: throw CategoryNotFoundException(categoryName)
        } ?: throw RuntimeException("Category not found")
            ?: throw RuntimeException("Category is parent")
    //TODO exception with incorrect id

    fun getCategoryById(id: UUID): Category =
        categoryRepository.findCategoryByKeyId(id)
//            ?: throw CategoryNotFoundException(id.toString())
            ?: throw RuntimeException("Category not found")

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
