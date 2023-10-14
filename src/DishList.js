import React, { useState, useEffect } from 'react';
import axios from 'axios';

function DishList() {
    const [dishes, setDishes] = useState([]);
    const [newDish, setNewDish] = useState({ foodName: '', type: '', price: 0 });

    useEffect(() => {
        async function fetchData() {
            try {
                const response = await axios.get('http://localhost:8086/api/orders');
                setDishes(response.data);
            } catch (error) {
                console.error('Error fetching dishes', error);
            }
        }
        fetchData();
    }, []);

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setNewDish(prevState => ({ ...prevState, [name]: value }));
    }

    const handleSubmit = async () => {
        try {
            const response = await axios.post('http://localhost:8086/api/orders', newDish);
            setDishes([...dishes, response.data]);
            setNewDish({ foodName: '', type: '', price: 0 });
        } catch (error) {
            console.error('Error adding dish', error);
        }
    }

    return (
        <div className="list-container">
            <h2>Dishes</h2>
            <ul>
                {dishes.map(dish => (
                    <li key={dish.id}>{dish.foodName} - {dish.type} - {dish.price}</li>
                ))}
            </ul>

            <div>
                <h3>Add New Dish</h3>
                <input
                    name="foodName"
                    value={newDish.foodName}
                    onChange={handleInputChange}
                    placeholder="Food Name"
                />
                <input
                    name="type"
                    value={newDish.type}
                    onChange={handleInputChange}
                    placeholder="Type"
                />
                <input
                    name="price"
                    type="number"
                    value={newDish.price}
                    onChange={handleInputChange}
                    placeholder="Price"
                />
                <button onClick={handleSubmit}>Add Dish</button>
            </div>
        </div>
    );
}

export default DishList;
