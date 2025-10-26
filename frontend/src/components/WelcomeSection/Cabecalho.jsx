import React from 'react';
import './Cabecalho.css';

const Cabecalho = () => {
  return (
    <header className="cabecalho">
      <div className="logo">
        <span className="logo-k">K</span>
        <span className="logo-house">House</span>
      </div>

      <nav className="navegacao">
        <a href="#adocao" className="link-navegacao">Gatos Para Adoção</a>
        <a href="#doacoes" className="link-navegacao">Doações</a>
        <a href="#castracao" className="link-navegacao">Castração</a>
        <a href="#contato" className="link-navegacao">Contato</a>
      </nav>

      <img
        src="icons8-cat-67 1.png"
        alt="Ícone de gato"
        className="icone-gato-cabecalho"
      />
    </header>
  );
};

export default Cabecalho;