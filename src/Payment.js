import React, { useState, useEffect } from 'react';
import axios from 'axios';

function PaymentList() {
    const [payments, setPayments] = useState([]);
    const [newPayment, setNewPayment] = useState({ transactionId: '', amount: 0, status: '' });

    useEffect(() => {
        async function fetchData() {
            try {
                const response = await axios.get('http://localhost:8086/api/payments');
                setPayments(response.data);
            } catch (error) {
                console.error('Error fetching payments', error);
            }
        }
        fetchData();
    }, []);

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setNewPayment(prevState => ({ ...prevState, [name]: value }));
    }

    const handleSubmit = async () => {
        try {
            const response = await axios.post('http://localhost:8086/api/payments', newPayment);
            setPayments([...payments, response.data]);
            setNewPayment({ transactionId: '', amount: 0, status: '' });
        } catch (error) {
            console.error('Error adding payment', error);
        }
    }

    return (
        <div className="list-container">
            <h2>Payments</h2>
            <ul>
                {payments.map(payment => (
                    <li key={payment.id}>Transaction ID: {payment.transactionId} - Amount: ${payment.amount} - Status: {payment.status}</li>
                ))}
            </ul>

            <div>
                <h3>Add New Payment</h3>
                <input
                    name="transactionId"
                    value={newPayment.transactionId}
                    onChange={handleInputChange}
                    placeholder="Transaction ID"
                />
                <input
                    name="amount"
                    type="number"
                    value={newPayment.amount}
                    onChange={handleInputChange}
                    placeholder="Amount"
                />
                <input
                    name="status"
                    value={newPayment.status}
                    onChange={handleInputChange}
                    placeholder="Status"
                />
                <button onClick={handleSubmit}>Add Payment</button>
            </div>
        </div>
    );
}

export default PaymentList;