import { Container, Typography } from '@mui/material';
import OrderForm from './components/OrderForm';
import OrderList from './components/OrderList';

function App() {

  const handleOrderCreated = () => {
    // Refresh the order list
    window.location.reload();
  };

  return (
    <Container maxWidth="lg" sx={{ py: 4 }}>
      <Typography variant="h3" component="h1" gutterBottom>
        Order Management System
      </Typography>
      <OrderForm onOrderCreated={handleOrderCreated} />
      <OrderList />
    </Container>
  );
}

export default App;
