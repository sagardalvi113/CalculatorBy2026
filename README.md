# Online Bakery Application

A fully functional online bakery store application with Spring Boot backend and React frontend.

## Features

### Backend (Spring Boot)
- RESTful API for products and orders
- Product search and filtering by category
- Order management with customer information
- H2 in-memory database
- Stock management
- CORS enabled for frontend integration

### Frontend (React)
- Product listing with images
- Search functionality
- Category filtering
- Shopping cart
- Checkout process
- Responsive design

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Node.js 16+ and npm
- Git

## Running the Application

### Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Build the project:
```bash
mvn clean install
```

3. Run the Spring Boot application:
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the React development server:
```bash
npm start
```

The frontend will start on `http://localhost:3000`

## API Endpoints

### Products
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `GET /api/products/search?query={query}` - Search products
- `GET /api/products/category/{category}` - Get products by category
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Orders
- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `GET /api/orders/customer/{email}` - Get orders by customer email
- `POST /api/orders` - Create new order
- `PUT /api/orders/{id}/status` - Update order status

## Database

The application uses H2 in-memory database. You can access the H2 console at:
`http://localhost:8080/h2-console`

**Connection details:**
- JDBC URL: `jdbc:h2:mem:bakerydb`
- Username: `sa`
- Password: (leave empty)

## Sample Products

The application comes pre-loaded with sample bakery products:
- Chocolate Cake
- Vanilla Cupcake
- Croissant
- Sourdough Bread
- Blueberry Muffin
- Red Velvet Cake
- Cinnamon Roll
- Apple Pie
- Chocolate Chip Cookie
- Baguette

## Technology Stack

### Backend
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database
- Maven

### Frontend
- React 18.2.0
- Axios for API calls
- CSS3 for styling

## Project Structure

```
CalculatorBy2026/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/bakery/
│   │   │   │   ├── model/
│   │   │   │   ├── repository/
│   │   │   │   ├── controller/
│   │   │   │   ├── config/
│   │   │   │   └── BakeryApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── pom.xml
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   │   ├── ProductList.js
│   │   │   └── Cart.js
│   │   ├── App.js
│   │   ├── index.js
│   │   └── index.css
│   └── package.json
└── README.md
```

## Usage

1. Start both backend and frontend servers
2. Open `http://localhost:3000` in your browser
3. Browse products, use search and category filters
4. Add items to cart
5. Click the cart icon to view your cart
6. Proceed to checkout and fill in customer information
7. Place your order

## Features Demo

- **Search**: Type in the search bar to find products by name or description
- **Category Filter**: Click category buttons to filter products
- **Add to Cart**: Click "Add to Cart" button on any product
- **Cart Management**: Adjust quantities or remove items
- **Checkout**: Complete customer information and place order
- **Stock Management**: Stock automatically updates after order placement

## Future Enhancements

- User authentication and authorization
- Payment gateway integration
- Order tracking
- Product reviews and ratings
- Admin panel for product management
- Email notifications
- Order history
- Persistent database (PostgreSQL/MySQL)

## License

MIT License
