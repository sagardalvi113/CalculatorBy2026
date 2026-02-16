import React from 'react';

function ProductList({ products, onAddToCart }) {
  if (products.length === 0) {
    return (
      <div className="loading">
        <p>No products found. Try adjusting your search or filters.</p>
      </div>
    );
  }

  return (
    <div className="products-grid">
      {products.map(product => (
        <div key={product.id} className="product-card">
          <img
            src={product.imageUrl}
            alt={product.name}
            className="product-image"
            onError={(e) => {
              e.target.src = 'https://via.placeholder.com/400x300?text=Bakery+Product';
            }}
          />
          <div className="product-info">
            <h3 className="product-name">{product.name}</h3>
            <p className="product-description">{product.description}</p>
            <div className="product-footer">
              <span className="product-price">${product.price.toFixed(2)}</span>
              <span className="product-stock">
                {product.stock > 0 ? `${product.stock} in stock` : 'Out of stock'}
              </span>
            </div>
            <button
              className="add-to-cart-btn"
              onClick={() => onAddToCart(product)}
              disabled={product.stock === 0}
            >
              {product.stock > 0 ? 'Add to Cart' : 'Out of Stock'}
            </button>
          </div>
        </div>
      ))}
    </div>
  );
}

export default ProductList;
