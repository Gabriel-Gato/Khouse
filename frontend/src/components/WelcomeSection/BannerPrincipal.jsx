import React from 'react';
import './BannerPrincipal.css';

const BannerPrincipal = () => {
  return (
    <section className="banner-principal">
      <div className="fundo-banner">
        <img
          src="Rectangle 2.png"
          alt="Gatos no abrigo"
          className="imagem-banner"
        />
      </div>

      <div className="conteudo-banner">
        <h1 className="titulo-banner">
          Venha conhecer<br/>
          nossos mais<br/>
          queridos<br/>
          companheiros<br/>
          nesse abrigo<br/>
          de gatos!
        </h1>

        <button className="botao-cta">
          VEJA MAIS
        </button>
      </div>
    </section>
  );
};

export default BannerPrincipal;