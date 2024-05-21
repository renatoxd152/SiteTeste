import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';
import { Sorvetes } from './components/Sorvete/CadastrarSorvete';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/sorvetes" element={<Sorvetes />} />
      </Routes>
    </Router>
  );
}

export default App;
