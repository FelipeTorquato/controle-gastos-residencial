import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import User from './pages/User';
import Transaction from './pages/Transaction';
import Summary from './pages/Summary';
import Navbar from './components/Navbar';
import './App.css';

const App: React.FC = () => {
  return (
    <Router>
      <div className='app-container'>
        <Navbar />
        <div className="container mt-4">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/users" element={<User />} />
            <Route path="/transactions" element={<Transaction />} />
            <Route path="/summary" element={<Summary />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
};

export default App;