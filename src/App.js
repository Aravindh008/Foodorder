import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom';
import CustomerList from './Customers';
import DishList from './DishList';
import PaymentList from './Payment';

function App() {
  return (
    <Router>
      <div className="App">
        <nav>
          <Link to="/customers">Customers</Link>
          <Link to="/orders">Dishes</Link>
          <Link to="/payments">Payments</Link>
        </nav>

        <Routes>
          <Route path="/customers" element={<CustomerList />} />
          <Route path="/orders" element={<DishList />} />
          <Route path="/payments" element={<PaymentList />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
