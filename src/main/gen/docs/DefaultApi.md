# DefaultApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**bankAccountNumberCreateCardPost**](DefaultApi.md#bankAccountNumberCreateCardPost) | **POST** /bank/{accountNumber}/create/card | Create new card |
| [**bankCreateBankAccountPost**](DefaultApi.md#bankCreateBankAccountPost) | **POST** /bank/create/bank-account | Creates a bank account |
| [**bankProcessTransactionTransactionAmountPost**](DefaultApi.md#bankProcessTransactionTransactionAmountPost) | **POST** /bank/process-transaction/{transactionAmount} | Process a payment transaction |
| [**bankTopUpCardNumberAmountPost**](DefaultApi.md#bankTopUpCardNumberAmountPost) | **POST** /bank/top-up/{cardNumber}/{amount} | Top up account balance |
| [**bankValidateCardNumberCvvPost**](DefaultApi.md#bankValidateCardNumberCvvPost) | **POST** /bank/validate/{cardNumber}/{cvv} | Method to validate card data |
| [**cartAddCartIdProductIdPost**](DefaultApi.md#cartAddCartIdProductIdPost) | **POST** /cart/add/{cartId}/{productId} | Add product to cart |
| [**cartDecrementCartIdProductIdPatch**](DefaultApi.md#cartDecrementCartIdProductIdPatch) | **PATCH** /cart/decrement/{cartId}/{productId} | Decrements product in cart |
| [**cartDeleteCartIdProductIdDelete**](DefaultApi.md#cartDeleteCartIdProductIdDelete) | **DELETE** /cart/delete/{cartId}/{productId} | Delete product from cart |
| [**cartIncrementCartIdProductIdPatch**](DefaultApi.md#cartIncrementCartIdProductIdPatch) | **PATCH** /cart/increment/{cartId}/{productId} | Increments product in cart |
| [**catalogGetCategoryNameProductsGet**](DefaultApi.md#catalogGetCategoryNameProductsGet) | **GET** /catalog/get/{categoryName}/products | Get products in category |
| [**categoryCreateCategoryNamePost**](DefaultApi.md#categoryCreateCategoryNamePost) | **POST** /category/create/{categoryName} | Create a new category |
| [**categoryCreateParentCategoryNameChildCategoryNamePost**](DefaultApi.md#categoryCreateParentCategoryNameChildCategoryNamePost) | **POST** /category/create/{parentCategoryName}/{childCategoryName} | Create a new child category |
| [**categoryGetCategoryNameGet**](DefaultApi.md#categoryGetCategoryNameGet) | **GET** /category/get/{categoryName} | Get all child categories of a category |
| [**categoryGetGet**](DefaultApi.md#categoryGetGet) | **GET** /category/get | Get all parents categories |
| [**getCart**](DefaultApi.md#getCart) | **GET** /cart/get/{cartId} | Get cart by id |
| [**orderCreateCartIdPost**](DefaultApi.md#orderCreateCartIdPost) | **POST** /order/create/{cartId} | Create order |
| [**paymentAddUserIdPost**](DefaultApi.md#paymentAddUserIdPost) | **POST** /payment/add/{userId} | Add a new payment method |
| [**paymentGetAllUserIdGet**](DefaultApi.md#paymentGetAllUserIdGet) | **GET** /payment/get/all/{userId} | Get all available user payment methods |
| [**productCreatePost**](DefaultApi.md#productCreatePost) | **POST** /product/create | Create a new product |
| [**productQuantityProductIdChangeQuantityPost**](DefaultApi.md#productQuantityProductIdChangeQuantityPost) | **POST** /product/quantity/{productId}/change/{quantity} | Change product stock quantity |
| [**productQuantityProductIdReduceQuantityPost**](DefaultApi.md#productQuantityProductIdReduceQuantityPost) | **POST** /product/quantity/{productId}/reduce/{quantity} | Reduce product stock quantity |
| [**transactionProcessOrderIdPaymentMethodIdPost**](DefaultApi.md#transactionProcessOrderIdPaymentMethodIdPost) | **POST** /transaction/process/{orderId}/{paymentMethodId} | Process a payment transaction |
| [**userCreatePost**](DefaultApi.md#userCreatePost) | **POST** /user/create | Create a new user |


<a id="bankAccountNumberCreateCardPost"></a>
# **bankAccountNumberCreateCardPost**
> bankAccountNumberCreateCardPost(accountNumber)

Create new card

Creates a new card with the specified details

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String accountNumber = "1234567890"; // String | 
    try {
      apiInstance.bankAccountNumberCreateCardPost(accountNumber);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#bankAccountNumberCreateCardPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **accountNumber** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Card created in account |  -  |

<a id="bankCreateBankAccountPost"></a>
# **bankCreateBankAccountPost**
> bankCreateBankAccountPost()

Creates a bank account

Creates a new bank account with the specified details

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      apiInstance.bankCreateBankAccountPost();
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#bankCreateBankAccountPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Bank account created |  -  |

<a id="bankProcessTransactionTransactionAmountPost"></a>
# **bankProcessTransactionTransactionAmountPost**
> bankProcessTransactionTransactionAmountPost(transactionAmount, body)

Process a payment transaction

Processes a payment transaction using the provided details

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Double transactionAmount = 100.0D; // Double | 
    Object body = null; // Object | Card data for the transaction
    try {
      apiInstance.bankProcessTransactionTransactionAmountPost(transactionAmount, body);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#bankProcessTransactionTransactionAmountPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **transactionAmount** | **Double**|  | |
| **body** | **Object**| Card data for the transaction | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Transaction processed |  -  |

<a id="bankTopUpCardNumberAmountPost"></a>
# **bankTopUpCardNumberAmountPost**
> bankTopUpCardNumberAmountPost(cardNumber, amount)

Top up account balance

Tops up the balance of the specified bank account by card number

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String cardNumber = "885351475761580881"; // String | 
    Double amount = 100.0D; // Double | 
    try {
      apiInstance.bankTopUpCardNumberAmountPost(cardNumber, amount);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#bankTopUpCardNumberAmountPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **cardNumber** | **String**|  | |
| **amount** | **Double**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Bank account topped up |  -  |

<a id="bankValidateCardNumberCvvPost"></a>
# **bankValidateCardNumberCvvPost**
> bankValidateCardNumberCvvPost(cardNumber, cvv, body)

Method to validate card data

Validates the card data provided by the user from the client

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String cardNumber = "1234567890123456"; // String | 
    String cvv = "123"; // String | 
    String body = "body_example"; // String | Expiration date of the card
    try {
      apiInstance.bankValidateCardNumberCvvPost(cardNumber, cvv, body);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#bankValidateCardNumberCvvPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **cardNumber** | **String**|  | |
| **cvv** | **String**|  | |
| **body** | **String**| Expiration date of the card | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Card data validated |  -  |

<a id="cartAddCartIdProductIdPost"></a>
# **cartAddCartIdProductIdPost**
> cartAddCartIdProductIdPost(cartId, productId)

Add product to cart

Adds product to cart by id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String cartId = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"; // String | 
    String productId = "e7b2f8a0-3c4d-4f5e-8b1c-6d5f8a0e3b4f"; // String | 
    try {
      apiInstance.cartAddCartIdProductIdPost(cartId, productId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#cartAddCartIdProductIdPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **cartId** | **String**|  | |
| **productId** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Product added |  -  |

<a id="cartDecrementCartIdProductIdPatch"></a>
# **cartDecrementCartIdProductIdPatch**
> cartDecrementCartIdProductIdPatch(cartId, productId)

Decrements product in cart

Decrements product in cart by id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String cartId = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"; // String | 
    String productId = "e7b2f8a0-3c4d-4f5e-8b1c-6d5f8a0e3b4f"; // String | 
    try {
      apiInstance.cartDecrementCartIdProductIdPatch(cartId, productId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#cartDecrementCartIdProductIdPatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **cartId** | **String**|  | |
| **productId** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Product incremented |  -  |

<a id="cartDeleteCartIdProductIdDelete"></a>
# **cartDeleteCartIdProductIdDelete**
> cartDeleteCartIdProductIdDelete(cartId, productId)

Delete product from cart

Deletes product from cart by id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String cartId = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"; // String | 
    String productId = "e7b2f8a0-3c4d-4f5e-8b1c-6d5f8a0e3b4f"; // String | 
    try {
      apiInstance.cartDeleteCartIdProductIdDelete(cartId, productId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#cartDeleteCartIdProductIdDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **cartId** | **String**|  | |
| **productId** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Cart deleted |  -  |

<a id="cartIncrementCartIdProductIdPatch"></a>
# **cartIncrementCartIdProductIdPatch**
> cartIncrementCartIdProductIdPatch(cartId, productId)

Increments product in cart

Increments product in cart by id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String cartId = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"; // String | 
    String productId = "e7b2f8a0-3c4d-4f5e-8b1c-6d5f8a0e3b4f"; // String | 
    try {
      apiInstance.cartIncrementCartIdProductIdPatch(cartId, productId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#cartIncrementCartIdProductIdPatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **cartId** | **String**|  | |
| **productId** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Product incremented |  -  |

<a id="catalogGetCategoryNameProductsGet"></a>
# **catalogGetCategoryNameProductsGet**
> catalogGetCategoryNameProductsGet(categoryName)

Get products in category

Returns all products in the specified category

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String categoryName = "clothes"; // String | 
    try {
      apiInstance.catalogGetCategoryNameProductsGet(categoryName);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#catalogGetCategoryNameProductsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **categoryName** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | List of products |  -  |

<a id="categoryCreateCategoryNamePost"></a>
# **categoryCreateCategoryNamePost**
> categoryCreateCategoryNamePost(categoryName)

Create a new category

Creates a new category if it does not already exist

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String categoryName = "clothes"; // String | 
    try {
      apiInstance.categoryCreateCategoryNamePost(categoryName);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#categoryCreateCategoryNamePost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **categoryName** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Category created |  -  |

<a id="categoryCreateParentCategoryNameChildCategoryNamePost"></a>
# **categoryCreateParentCategoryNameChildCategoryNamePost**
> categoryCreateParentCategoryNameChildCategoryNamePost(parentCategoryName, childCategoryName)

Create a new child category

Creates a new child category under the specified parent category

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String parentCategoryName = "clothes"; // String | 
    String childCategoryName = "shoes"; // String | 
    try {
      apiInstance.categoryCreateParentCategoryNameChildCategoryNamePost(parentCategoryName, childCategoryName);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#categoryCreateParentCategoryNameChildCategoryNamePost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **parentCategoryName** | **String**|  | |
| **childCategoryName** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Child category created |  -  |

<a id="categoryGetCategoryNameGet"></a>
# **categoryGetCategoryNameGet**
> categoryGetCategoryNameGet(categoryName)

Get all child categories of a category

Returns all child categories of the specified category

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String categoryName = "clothes"; // String | 
    try {
      apiInstance.categoryGetCategoryNameGet(categoryName);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#categoryGetCategoryNameGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **categoryName** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | List of child categories |  -  |

<a id="categoryGetGet"></a>
# **categoryGetGet**
> categoryGetGet()

Get all parents categories

Returns a list of all parents categories

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      apiInstance.categoryGetGet();
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#categoryGetGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | List of catalogs |  -  |

<a id="getCart"></a>
# **getCart**
> getCart(cartId)

Get cart by id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String cartId = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"; // String | 
    try {
      apiInstance.getCart(cartId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getCart");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **cartId** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Cart found |  -  |

<a id="orderCreateCartIdPost"></a>
# **orderCreateCartIdPost**
> orderCreateCartIdPost(cartId, requestBody)

Create order

Creates order by cart id with chosen products from cart

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String cartId = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"; // String | 
    List<String> requestBody = Arrays.asList(); // List<String> | List of product UUIDs
    try {
      apiInstance.orderCreateCartIdPost(cartId, requestBody);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#orderCreateCartIdPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **cartId** | **String**|  | |
| **requestBody** | [**List&lt;String&gt;**](String.md)| List of product UUIDs | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Order created successfully |  -  |

<a id="paymentAddUserIdPost"></a>
# **paymentAddUserIdPost**
> paymentAddUserIdPost(userId, paymentType, cardNumber, expirationDate, cvv)

Add a new payment method

Adds a payment method (e.g. card) for the specified user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String userId = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"; // String | 
    String paymentType = "CARD"; // String | 
    String cardNumber = "1234567890123456"; // String | 
    String expirationDate = "12/25"; // String | 
    String cvv = "123"; // String | 
    try {
      apiInstance.paymentAddUserIdPost(userId, paymentType, cardNumber, expirationDate, cvv);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#paymentAddUserIdPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userId** | **String**|  | |
| **paymentType** | **String**|  | |
| **cardNumber** | **String**|  | |
| **expirationDate** | **String**|  | |
| **cvv** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Payment method added successfully |  -  |

<a id="paymentGetAllUserIdGet"></a>
# **paymentGetAllUserIdGet**
> paymentGetAllUserIdGet(userId)

Get all available user payment methods

Retrieves all payment methods for a specific user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String userId = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"; // String | 
    try {
      apiInstance.paymentGetAllUserIdGet(userId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#paymentGetAllUserIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userId** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | All available user payment methods |  -  |

<a id="productCreatePost"></a>
# **productCreatePost**
> productCreatePost(productDto)

Create a new product

Creates a new product with the given details

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    ProductDto productDto = new ProductDto(); // ProductDto | 
    try {
      apiInstance.productCreatePost(productDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#productCreatePost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **productDto** | [**ProductDto**](ProductDto.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Product created |  -  |

<a id="productQuantityProductIdChangeQuantityPost"></a>
# **productQuantityProductIdChangeQuantityPost**
> productQuantityProductIdChangeQuantityPost(productId, quantity, body)

Change product stock quantity

Changes the stock quantity of a product by id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String productId = "08150d93-1da7-41e8-b140-2a9341b60f6e"; // String | 
    Integer quantity = 10; // Integer | 
    String body = "body_example"; // String | Name of the subcatalog
    try {
      apiInstance.productQuantityProductIdChangeQuantityPost(productId, quantity, body);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#productQuantityProductIdChangeQuantityPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **productId** | **String**|  | |
| **quantity** | **Integer**|  | |
| **body** | **String**| Name of the subcatalog | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Product stock updated |  -  |

<a id="productQuantityProductIdReduceQuantityPost"></a>
# **productQuantityProductIdReduceQuantityPost**
> productQuantityProductIdReduceQuantityPost(productId, quantity, body)

Reduce product stock quantity

Reduces the stock quantity of a product by id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String productId = "08150d93-1da7-41e8-b140-2a9341b60f6e"; // String | 
    Integer quantity = 5; // Integer | 
    String body = "body_example"; // String | Name of the subcatalog
    try {
      apiInstance.productQuantityProductIdReduceQuantityPost(productId, quantity, body);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#productQuantityProductIdReduceQuantityPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **productId** | **String**|  | |
| **quantity** | **Integer**|  | |
| **body** | **String**| Name of the subcatalog | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Product stock reduced |  -  |

<a id="transactionProcessOrderIdPaymentMethodIdPost"></a>
# **transactionProcessOrderIdPaymentMethodIdPost**
> transactionProcessOrderIdPaymentMethodIdPost(orderId, paymentMethodId)

Process a payment transaction

Processes a payment transaction using the provided details

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String orderId = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"; // String | 
    String paymentMethodId = "f44ae0b6-7d28-4a78-8fc6-9532d96f6ccd"; // String | 
    try {
      apiInstance.transactionProcessOrderIdPaymentMethodIdPost(orderId, paymentMethodId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#transactionProcessOrderIdPaymentMethodIdPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **orderId** | **String**|  | |
| **paymentMethodId** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Transaction processed |  -  |

<a id="userCreatePost"></a>
# **userCreatePost**
> userCreatePost(userDto)

Create a new user

Creates a new user with the given details

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UserDto userDto = new UserDto(); // UserDto | 
    try {
      apiInstance.userCreatePost(userDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#userCreatePost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userDto** | [**UserDto**](UserDto.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | User created |  -  |

