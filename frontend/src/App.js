import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';
import { Sorvetes } from './components/Sorvete/CadastrarSorvete';
import Listar from "./components/Sorvete/ListarSorvete";
import EditarSorvete from './components/Sorvete/EditarSorvetes';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Listar />} />
        <Route path="/sorvete" element={<Sorvetes />} />
        <Route path="/sorvete/:id" element={<EditarSorvete />} />
      </Routes>
    </Router>
  );
}

export default App;
