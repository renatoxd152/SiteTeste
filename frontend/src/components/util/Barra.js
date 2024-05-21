import 'bootstrap/dist/css/bootstrap.min.css';
import React, { useState } from "react";
import { Link } from 'react-router-dom';

const Barra = () => {
  const [ submenuAberto, setSubmenuAberto ] = useState(null);

  function toggleSubmenu(grupo) {
    setSubmenuAberto((prevState) => (prevState === grupo ? null : grupo));
  };

  function handleClick(grupo) {
    return (e) => {
      e.preventDefault();
      toggleSubmenu(grupo);
    }
  };

  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <ul className="navbar-nav">
          <li className="nav-item dropdown">
            <a
              className="nav-link dropdown-toggle"
              href="/"
              id="sorvetesDropdown"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded={submenuAberto === 'sorvetes'}
              onClick={handleClick('sorvetes')}
            >
              Sorvetes
            </a>
            <div
              className={`dropdown-menu ${submenuAberto === 'sorvetes' ? 'show' : ''}`}
              aria-labelledby="sorvetesDropdown"
            >
              <Link className="dropdown-item" to="/sorvete">
                Cadastrar Sorvetes
              </Link>
              <Link className="dropdown-item" to="/">
                Listar Sorvetes
              </Link>
            </div>
          </li>
        </ul>
      </nav>
    </div>
  );
};

export default Barra;