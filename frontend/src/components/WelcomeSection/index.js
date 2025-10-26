import React from 'react';
import Cabecalho from './Cabecalho';
import BannerPrincipal from './BannerPrincipal';
import ConteudoPrincipal from './ConteudoPrincipal';
import Rodape from './Rodape';
import './WelcomeSection.css';

const WelcomeSection = () => {
  return (
    <div className="welcome-section">
      <Cabecalho />
      <BannerPrincipal />
      <ConteudoPrincipal />
      <Rodape />
    </div>
  );
};

export default WelcomeSection;