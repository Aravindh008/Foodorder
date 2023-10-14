import React, { useState, useEffect } from 'react';
import axios from 'axios';

function CustomerList() {
    const [customers, setCustomers] = useState([]);
    const [currentCustomer, setCurrentCustomer] = useState({ id: null, name: '', email: '', address: '' });
    const [editing, setEditing] = useState(false);

    useEffect(() => {
        fetchCustomers();
    }, []);

    const fetchCustomers = async () => {
        try {
            const response = await axios.get('http://localhost:8086/api/customers');
            setCustomers(response.data);
        } catch (error) {
            console.error('Error fetching customers', error);
        }
    };

    const handleInputChange = event => {
        const { name, value } = event.target;
        setCurrentCustomer({ ...currentCustomer, [name]: value });
    };

    const addCustomer = async () => {
        if (!currentCustomer.name || !currentCustomer.email || !currentCustomer.address) return;
        try {
            const response = await axios.post('http://localhost:8086/api/customers', currentCustomer);
            setCustomers([...customers, response.data]);
            setCurrentCustomer({ id: null, name: '', email: '', address: '' });
        } catch (error) {
            console.error('Error adding customer', error);
        }
    };

    const deleteCustomer = async id => {
        try {
            await axios.delete(`http://localhost:8086/api/customers/${id}`);
            setCustomers(customers.filter(customer => customer.id !== id));
        } catch (error) {
            console.error('Error deleting customer', error);
        }
    };

    const editCustomer = customer => {
        setEditing(true);
        setCurrentCustomer(customer);
    };

    const updateCustomer = async () => {
        try {
            await axios.put(`http://localhost:8086/api/customers/${currentCustomer.id}`, currentCustomer);
            fetchCustomers();
            setEditing(false);
            setCurrentCustomer({ id: null, name: '', email: '', address: '' });
        } catch (error) {
            console.error('Error updating customer', error);
        }
    };

    return (
        <div className="list-container">
            <h2>Customers</h2>

            {editing ? (
                <div>
                    <h3>Edit Customer</h3>
                    <button onClick={() => setEditing(false)}>Cancel</button>
                </div>
            ) : (
                <h3>Add New Customer</h3>
            )}

            <input name="name" value={currentCustomer.name} onChange={handleInputChange} placeholder="Name" />
            <input name="email" value={currentCustomer.email} onChange={handleInputChange} placeholder="Email" />
            <input name="address" value={currentCustomer.address} onChange={handleInputChange} placeholder="Address" />
            {editing ? (
                <button onClick={updateCustomer}>Update</button>
            ) : (
                <button onClick={addCustomer}>Add Customer</button>
            )}

            <ul>
                {customers.map(customer => (
                    <li key={customer.id}>
                        {customer.name} - {customer.email}
                        <button onClick={() => editCustomer(customer)}>Edit</button>
                        <button onClick={() => deleteCustomer(customer.id)}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default CustomerList;
