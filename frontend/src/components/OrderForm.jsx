import { useState } from 'react';
import { 
  Box, TextField, Button, Paper, Typography 
} from '@mui/material';
import axios from 'axios';

export default function OrderForm({ onOrderCreated }) {
  const [formData, setFormData] = useState({
    customerName: '',
    totalAmount: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/orders', {
        ...formData,
        totalAmount: parseFloat(formData.totalAmount)
      });
      setFormData({ customerName: '', totalAmount: '' });
      onOrderCreated && onOrderCreated(response.data);
    } catch (error) {
      console.error('Error creating order:', error);
    }
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  return (
    <Paper sx={{ p: 3, mb: 3 }}>
      <Typography variant="h6" gutterBottom>
        Create New Orders
      </Typography>
      <Box component="form" onSubmit={handleSubmit}>
        <TextField
          fullWidth
          margin="normal"
          label="Customer Name"
          name="customerName"
          value={formData.customerName}
          onChange={handleChange}
          required
        />
        <TextField
          fullWidth
          margin="normal"
          label="Total Amount"
          name="totalAmount"
          type="number"
          step="0.01"
          value={formData.totalAmount}
          onChange={handleChange}
          required
        />
        <Button 
          type="submit" 
          variant="contained" 
          color="primary"
          sx={{ mt: 2 }}
        >
          Create Order
        </Button>
      </Box>
    </Paper>
  );
}
