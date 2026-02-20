import React, { useState } from 'react';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

function Cart({ cart, onClose, onUpdateQuantity, onRemoveItem, onClearCart, totalAmount, onCheckoutComplete }) {
  const [showCheckout, setShowCheckout] = useState(false);
  const [customerInfo, setCustomerInfo] = useState({
    customerName: '',
    customerEmail: '',
    customerAddress: '',
    customerPhone: ''
  });
  const [orderSuccess, setOrderSuccess] = useState(false);
  const [orderError, setOrderError] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleInputChange = (e) => {
    setCustomerInfo({
      ...customerInfo,
      [e.target.name]: e.target.value
    });
  };

  const handleCheckout = async (e) => {
    e.preventDefault();
    
    if (cart.length === 0) {
      setOrderError('Your cart is empty');
      return;
    }

    setIsSubmitting(true);
    setOrderError('');

    const orderData = {
      ...customerInfo,
      items: cart.map(item => ({
        productId: item.id,
        quantity: item.quantity
      }))
    };

    try {
      const response = await axios.post(`${API_BASE_URL}/orders`, orderData);
      setOrderSuccess(true);
      setShowCheckout(false);
      onClearCart();
      
      // Refresh products to update stock
      if (onCheckoutComplete) {
        onCheckoutComplete();
      }

      setTimeout(() => {
        setOrderSuccess(false);
        onClose();
      }, 3000);
    } catch (error) {
      console.error('Error creating order:', error);
      setOrderError(error.response?.data || 'Failed to create order. Please try again.');
    } finally {
      setIsSubmitting(false);
    }
  };

  const isFormValid = () => {
    return customerInfo.customerName && 
           customerInfo.customerEmail && 
           customerInfo.customerAddress && 
           customerInfo.customerPhone;
  };

  return (
    <div className="cart-modal" onClick={onClose}>
      <div className="cart-content" onClick={(e) => e.stopPropagation()}>
        <div className="cart-header">
          <h2>Shopping Cart</h2>
          <button className="close-btn" onClick={onClose}>×</button>
        </div>

        {orderSuccess && (
          <div className="success-message">
            <h3>Order Placed Successfully! 🎉</h3>
            <p>Thank you for your purchase. Your order is being processed.</p>
          </div>
        )}

        {orderError && (
          <div className="error-message">
            {orderError}
          </div>
        )}

        {cart.length === 0 ? (
          <div className="empty-cart">
            <p>Your cart is empty</p>
            <p>Add some delicious bakery items!</p>
          </div>
        ) : (
          <>
            <div className="cart-items">
              {cart.map(item => (
                <div key={item.id} className="cart-item">
                  <img
                    src={item.imageUrl}
                    alt={item.name}
                    className="cart-item-image"
                    onError={(e) => {
                      e.target.src = 'https://via.placeholder.com/80?text=Product';
                    }}
                  />
                  <div className="cart-item-info">
                    <h4 className="cart-item-name">{item.name}</h4>
                    <p className="cart-item-price">${item.price.toFixed(2)} each</p>
                    <div className="cart-item-controls">
                      <button
                        className="quantity-btn"
                        onClick={() => onUpdateQuantity(item.id, item.quantity - 1)}
                      >
                        -
                      </button>
                      <span className="quantity-display">{item.quantity}</span>
                      <button
                        className="quantity-btn"
                        onClick={() => onUpdateQuantity(item.id, item.quantity + 1)}
                      >
                        +
                      </button>
                      <button
                        className="remove-btn"
                        onClick={() => onRemoveItem(item.id)}
                      >
                        Remove
                      </button>
                    </div>
                  </div>
                </div>
              ))}
            </div>

            <div className="cart-total">
              <h3>Total</h3>
              <div className="total-amount">${totalAmount}</div>
            </div>

            {!showCheckout ? (
              <button
                className="checkout-btn"
                onClick={() => setShowCheckout(true)}
              >
                Proceed to Checkout
              </button>
            ) : (
              <form className="checkout-form" onSubmit={handleCheckout}>
                <h3>Customer Information</h3>
                <div className="form-group">
                  <label htmlFor="customerName">Full Name *</label>
                  <input
                    type="text"
                    id="customerName"
                    name="customerName"
                    value={customerInfo.customerName}
                    onChange={handleInputChange}
                    required
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="customerEmail">Email *</label>
                  <input
                    type="email"
                    id="customerEmail"
                    name="customerEmail"
                    value={customerInfo.customerEmail}
                    onChange={handleInputChange}
                    required
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="customerAddress">Delivery Address *</label>
                  <input
                    type="text"
                    id="customerAddress"
                    name="customerAddress"
                    value={customerInfo.customerAddress}
                    onChange={handleInputChange}
                    required
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="customerPhone">Phone Number *</label>
                  <input
                    type="tel"
                    id="customerPhone"
                    name="customerPhone"
                    value={customerInfo.customerPhone}
                    onChange={handleInputChange}
                    required
                  />
                </div>
                <button
                  type="submit"
                  className="checkout-btn"
                  disabled={!isFormValid() || isSubmitting}
                >
                  {isSubmitting ? 'Placing Order...' : 'Place Order'}
                </button>
                <button
                  type="button"
                  className="checkout-btn"
                  onClick={() => setShowCheckout(false)}
                  style={{ backgroundColor: '#666' }}
                >
                  Back to Cart
                </button>
              </form>
            )}
          </>
        )}
      </div>
    </div>
  );
}

export default Cart;
