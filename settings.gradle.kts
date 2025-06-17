pluginManagement {
    plugins {
        kotlin("jvm") version "2.1.0"
    }
}
rootProject.name = "software_systems_business_logic_lab1"
include("product_service")
include("common")
include("auth_service")
include("cart_service")
include("order_service")
include("payment_service")
include("bank_service")
include("email_service")
include("kafka_service")
