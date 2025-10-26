package com.Khouse.Model;


import com.Khouse.Model.Enum.EscolhaTempo;
import com.Khouse.Model.Enum.GatoGenero;
import jakarta.persistence.*;

@Entity
@Table(name="gatos")
public class Gato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String Nome;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EscolhaTempo tempo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GatoGenero genero;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String imagem;

    public Gato() {
    }

    public Gato(long id, String nome, Integer idade, EscolhaTempo tempo, GatoGenero genero, String descricao, String imagem) {
        this.id = id;
        Nome = nome;
        this.idade = idade;
        this.tempo = tempo;
        this.genero = genero;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "Gato{" +
                "id=" + id +
                ", Nome='" + Nome + '\'' +
                ", idade=" + idade +
                ", tempo=" + tempo +
                ", genero=" + genero +
                ", descricao='" + descricao + '\'' +
                ", imagem='" + imagem + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public EscolhaTempo getTempo() {
        return tempo;
    }

    public void setTempo(EscolhaTempo tempo) {
        this.tempo = tempo;
    }

    public GatoGenero getGenero() {
        return genero;
    }

    public void setGenero(GatoGenero genero) {
        this.genero = genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}



