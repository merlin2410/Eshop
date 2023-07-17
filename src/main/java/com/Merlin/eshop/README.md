# Eshop - _An Ecommerce Platform_
***
_A Spring Boot application for maintaining an ecommerce platform
containing more than 22 APIs which is used to add, get, delete, update Customer, Seller, Product, Card etc_

## Models
***
1. Customer
2. Seller
3. Product
4. Item
5. Cart
6. Card
7. Ordered

## APIs
***
### Customer (/customer)
***
1. /add
   * Add customer. If mobile number or email already registered, throw an exception
2. /view-all
   * Returns a list of customers with name, email and mobile number
3. /get
   * Returns customer using email id or mobile number. If customer not present, it throws an exception
4. /get-greater-than-age
    * Get list of all customers less than the given age
5. /card/{cardType}
   * Get list of all customers with a given card type
6. /update
   * Update existing customer. If customer does not exist throw an exception
7. /delete
   * Delete a customer. If customer doesn't exist do nothing. No need for exceptions

### Seller (/seller)
***
1. /add
   * Add seller to the database. If email or mobile already registered, then throw exception
2. /get-by-mail
   * Get seller for given email or throw an exception
3. /get-by-mobile
   * Get seller by mobile or throw exception
4. /get-by-id
   * Get seller by id or throw exception
5. /view-all
   * Get all sellers
6. /update-by-mail
   * Update seller information using email. If user not present, it throws exception
7. /delete-by-id
   * Delete seller by id

### Product (/product)
***
1. /add
   * Add product into database
2. /{category}
   * Return a list of products coming under a particular category
3. /category-price-less-than
   * Return list of products having a given category with price less than the given price
4. /get-all-by-seller-mail
   * Return list of all products by a given seller using seller email

### Order (/order)
***
1. /place
   * Place order directly without adding to cart

### Cart (/cart)
***
1. /add
   * Add items to the cart. Throws exception if product or customer not valid
2. /checkout
   * Checkout from cart. Throws exceptions for invalid customer, out of stock products, empty cart, invalid card details

### Card (/card)
***
1. /add
   * Add card details of a customer. If customer not found it throws an exception 
   * If card is already added, an exception is thrown