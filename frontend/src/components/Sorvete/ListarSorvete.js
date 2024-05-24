import React, { useEffect, useState } from "react";
import { Link } from 'react-router-dom';
import Barra from "../util/Barra";

const Listar = () => {
  const [ sorvetes, setSorvetes ] = useState([]);
  const [ mensagem, setMensagem ] = useState('');

  useEffect(() => {
    // Recuperar os sorvetes do localStorage
    const sorvetesLocalStorage = JSON.parse(localStorage.getItem('sorvetes')) || [];
    setSorvetes(sorvetesLocalStorage);
  }, []);

  const handleExcluirSorvete = (sorveteIndex) => {
    // Remover o sorvete do array e atualizar o localStorage
    const updatedSorvetes = sorvetes.filter((_, index) => index !== sorveteIndex);
    localStorage.setItem('sorvetes', JSON.stringify(updatedSorvetes));
    setSorvetes(updatedSorvetes);
    setMensagem("Sorvete excluído com sucesso!");
  };

  return (
    <div>
      <Barra />
      <h1>Lista de Sorvetes</h1>
      <div className={`alert ${mensagem ? 'alert-success' : 'd-none'}`} role="alert">
        {mensagem}
      </div>
      <table className="table">
        <thead className="thead-dark">
          <tr>
            <th>Nome</th>
            <th>Quantidade</th>
            <th>Preço</th>
            <th>Excluir Sorvete</th>
            <th>Atualizar Sorvete</th>
          </tr>
        </thead>
        <tbody>
          {sorvetes.map((sorvete,index) => (
            <tr key={sorvete.id}>
              <td>{sorvete.nome}</td>
              <td>{sorvete.quantidade}</td>
              <td>{sorvete.preco}</td>
              <td>
                <button className="btn btn-danger" onClick={() => handleExcluirSorvete(index)}>
                  Excluir
                </button>
              </td>
              <td>
                <Link to={`sorvete/${index}`} className="btn btn-primary">
                  Atualizar
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Listar;