document.addEventListener('DOMContentLoaded', function () {
    // Fetch products from the API
    fetch('https://mywebsite.com:8080/api/product')
      .then(response => response.json())
      .then(data => {
        if (data.status === 'Success' && data.tdata) {
          displayProducts(data.tdata);
        } else {
          console.error('Error fetching products:', data.msg);
        }
      })
      .catch(error => console.error('Error:', error));
    
    // Function to display products
    function displayProducts(products) {
      const productListContainer = document.getElementById('product-list');
      
      products.forEach(product => {
        const productCard = document.createElement('div');
        productCard.classList.add('product-card');
        
        // Product brand and model
        const productTitle = document.createElement('h2');
        productTitle.textContent = `${product.id} - ${product.title}`;
        
        // Product price
        const productPrice = document.createElement('p');
        productPrice.classList.add('price');
        productPrice.textContent = `$${product.price}`;
        
        // Product colors
        const productColors = document.createElement('p');
        productColors.textContent = 'Available Colors:';
        const colorContainer = document.createElement('div');
        
        product.colorDtos.forEach(color => {
          const colorBox = document.createElement('span');
          colorBox.classList.add('color-box');
          colorBox.style.backgroundColor = color.hex;
          colorContainer.appendChild(colorBox);
        });
        
        // Append elements to the product card
        productCard.appendChild(productTitle);
        productCard.appendChild(productPrice);
        productCard.appendChild(productColors);
        productCard.appendChild(colorContainer);
        
        // Append product card to the product list
        productListContainer.appendChild(productCard);
      });
    }
  });
  