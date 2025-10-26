import React, { useState } from 'react';
import './Rodape.css';

const Rodape = () => {
  const [formData, setFormData] = useState({
    nome: '',
    email: ''
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    console.log('Dados do formulário:', formData);
    alert(`Obrigado ${formData.nome}! Você foi inscrito na newsletter.`);


    setFormData({
      nome: '',
      email: ''
    });
  };

  return (
    <footer className="rodape">
      <div className="conteudo-rodape">
        {/* Contatos */}
        <div className="secao-contato">
          <h3 className="titulo-rodape">Formas De Contato</h3>

          <div className="item-contato">
            <img src="icons8-whatsapp-100 2.png" alt="WhatsApp" />
            <span>(19) 91234-5678</span>
          </div>

          <div className="item-contato">
            <img src="icons8-phone-100 2.png" alt="Telefone" />
            <span>(19) 3456-7890</span>
          </div>

          <div className="item-contato">
            <img src="icons8-email-100 2.png" alt="Email" />
            <span>khouse.gatos@gmail.com</span>
          </div>
        </div>

        {/* Redes Sociais */}
        <div className="secao-social">
          <img src="icons8-instagram-100 2.png" alt="Instagram" />
          <img src="icons8-facebook-100 (1).png" alt="Facebook" />
          <img src="icons8-twitter-circled-100 (1).png" alt="Twitter" />
        </div>

        {/* Newsletter*/}
        <div className="secao-newsletter">
          <h3 className="titulo-newsletter">Receba Nossas Newsletters</h3>

          <form className="form-newsletter" onSubmit={handleSubmit}>
            <div className="grupo-input">
              <label htmlFor="email">E-mail</label>
              <input
                type="email"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                className="input-newsletter"
                placeholder="seu@email.com"
                required
              />
              <div className="linha-input"></div>
            </div>

            <div className="grupo-input">
              <label htmlFor="nome">Nome</label>
              <input
                type="text"
                id="nome"
                name="nome"
                value={formData.nome}
                onChange={handleChange}
                className="input-newsletter"
                placeholder="Seu primeiro nome"
                required
              />
              <div className="linha-input"></div>
            </div>

            <button type="submit" className="botao-newsletter">
              <img src="icons8-arrow-50 2.png" alt="Enviar" />
            </button>
          </form>
        </div>
      </div>
    </footer>
  );
};

export default Rodape;