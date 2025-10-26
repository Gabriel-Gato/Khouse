import React from 'react';
import './ConteudoPrincipal.css';

const ConteudoPrincipal = () => {
  return (
    <main className="conteudo-principal">
      {/* Seção Nosso Abrigo */}
      <section className="secao-abrigo">
        <div className="cabecalho-abrigo">
          <h2 className="titulo-abrigo">Nosso Abrigo</h2>
          <p className="localizacao-abrigo">Amparo, Sp</p>
        </div>

        <div className="imagem-abrigo-completa">
          <img src="Rectangle 4.png" alt="Nosso abrigo" />
        </div>
      </section>
      <section className="grid-sobre-missao">
        {/* Seção Sobre Nós */}
        <div className="secao-sobre-completa">
          <div className="secao-texto-sobre">
            <h3 className="titulo-secao">Sobre nós</h3>
            <div className="sublinhado-titulo"></div>
            <p className="texto-conteudo">
              Somos um abrigo que nasceu de um gesto simples: não conseguir passar indiferente por um gatinho precisando de ajuda. Desde então, viramos casa temporária para muitos gatinhos. resgatamos, cuidamos, curamos e procuramos famílias que ofereçam amor e responsabilidade.
              <br/><br/>
              Nosso abrigo é feito por pessoas comuns, movidas por afeto. Voluntários, veterinários parceiros e doadores formam a rede que sustenta cada final feliz. Transparência é prioridade: compartilhamos rotinas, gastos e resultados, porque este projeto é coletivo.
            </p>
          </div>

          <div className="imagem-missao">
            <img src="Rectangle 6.png" alt="Sobre nós" />
          </div>
        </div>

        {/* Seção Missão */}
        <div className="secao-missao-completa">
          <div className="secao-texto-missao">
            <h3 className="titulo-secao">Nossa Missão</h3>
            <div className="sublinhado-titulo"></div>
            <p className="texto-conteudo">
              No nosso abrigo, acreditamos que cada gato merece uma segunda chance, um lar cheio de carinho e um futuro repleto de dignidade. Nossa missão vai além de resgatar buscamos transformar vidas.
              <br/><br/>
              Resgatamos gatinhos que muitas vezes enfrentaram o abandono, a fome e o frio das ruas, oferecendo não apenas abrigo, mas também cuidado, respeito e amor. Cada patinha que chega até nós carrega uma história única, e é com dedicação que trabalhamos para reescrever essas histórias com finais felizes.
            </p>
          </div>

          <div className="imagem-missao">
            <img src="Rectangle 7.png" alt="Nossa missão" />
          </div>
        </div>
      </section>

      {/* Seção Por que Adotar? */}
      <section className="secao-porque-adotar">
        <h2 className="titulo-secao-centralizado">Por que Adotar?</h2>

        <div className="grid-beneficios">
          <div className="cartao-beneficio">
            <img src="icons8-cat-64 (2) 1.png" alt="Amigo fiel" className="icone-beneficio" />
            <p className="texto-beneficio">Ganha um amigo fiel<br/> carinho e companhia todos os dias.</p>
          </div>

          <div className="cartao-beneficio">
            <img src="icons8-cat-64 1.png" alt="Salvar vida" className="icone-beneficio" />
            <p className="texto-beneficio">Salva uma vida <br/>ao adotar, você dá um lar a quem precisa</p>
          </div>

          <div className="cartao-beneficio">
            <img src="icons8-cat-50 1.png" alt="Duas histórias" className="icone-beneficio" />
            <p className="texto-beneficio">Muda duas histórias<br/>a do gato e a sua</p>
          </div>
        </div>
      </section>

      {/* Seção Trabalho Voluntário */}
      <section className="secao-voluntario">
        <h2 className="titulo-secao-centralizado">Trabalho Voluntario</h2>

        <div className="conteudo-voluntario">
          <div className="imagem-voluntario">
            <img src="Rectangle 8.png" alt="Trabalho voluntário" />
          </div>

          <div className="texto-voluntario">
            <p className="pergunta-voluntario">
              Tem interesse em fazer parte do projeto da <span className="destaque-k">K</span>House?
            </p>

            <button className="botao-voluntario">
              Sim! Quero fazer<br/> parte da equipe
            </button>
          </div>
        </div>
      </section>
    </main>
  );
};

export default ConteudoPrincipal;