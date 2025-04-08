package com.example.software_systems_business_logic_lab1.application.models

class CategoryNotFoundException(name: String) :
    RuntimeException("Category with name '$name' not found or has no children")

class NoParentCategoriesFoundException :
    RuntimeException("No parent categories found")

class CategoryAlreadyExistsException(name: String) :
    RuntimeException("Category with name '$name' already exists")

class CategoryIsNotParentException(name: String) :
    RuntimeException("Category with name '$name' is not a parent category")

class CategoryIsParentException(name: String) :
    RuntimeException("Category with name '$name' is a parent category and cannot be made ultimate")

