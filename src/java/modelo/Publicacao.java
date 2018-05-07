package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "publicacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicacao.findAll", query = "SELECT a FROM Publicacao a"),
    @NamedQuery(name = "Publicacao.findByIdPublicacao", query = "SELECT a FROM Publicacao a WHERE a.idPublicacao = :idPublicacao"),
    @NamedQuery(name = "Publicacao.findByAno", query = "SELECT a FROM Publicacao a WHERE a.ano = :ano"),
    @NamedQuery(name = "Publicacao.findByDataHora", query = "SELECT a FROM Publicacao a WHERE a.dataHora = :dataHora"),
    @NamedQuery(name = "Publicacao.findByArquivo", query = "SELECT a FROM Publicacao a WHERE a.arquivo = :arquivo"),
    @NamedQuery(name = "Publicacao.findByTitulo", query = "SELECT a FROM Publicacao a WHERE a.titulo = :titulo"),
    @NamedQuery(name = "Publicacao.findByIdCategoria", query = "SELECT a FROM Publicacao a WHERE a.idCategoria = :idCategoria"),
    @NamedQuery(name = "Publicacao.findByIdAutor", query = "SELECT a FROM Publicacao a WHERE a.idAutor = :idAutor"),
    @NamedQuery(name = "Publicacao.findByIdArea", query = "SELECT a FROM Publicacao a WHERE a.idArea = :idArea"),
    @NamedQuery(name = "Publicacao.findByIdPeriodico", query = "SELECT a FROM Publicacao a WHERE a.idPeriodico = :idPeriodico")})
public class Publicacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idPublicacao")
    private Integer idPublicacao;
    @Lob
    @Size(max = 65535)
    @Column(name = "titulo")
    private String titulo;
    @Lob
    @Column(name = "dataHora")
    private Date dataHora;
    @Lob
    @Size(max = 65535)
    @Column(name = "arquivo")
    private String arquivo;
    @Lob
    @Size(max = 65535)
    @Column(name = "ano")
    private String ano;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idArea")
    private int idArea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPeriodico")
    private int idPeriodico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAutor")
    private int idAutor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCategoria")
    private int idCategoria;
    @JoinColumn(name = "idPeriodico", referencedColumnName = "idPeriodico", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Periodico periodico;
    @JoinColumn(name = "idAutor", referencedColumnName = "idAutor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Autor autor;
    @JoinColumn(name = "idArea", referencedColumnName = "idArea", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Area area;
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Categoria categoria;

     public Publicacao() {
    }

    public Publicacao(Integer idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public Publicacao(Integer idPublicacao, String titulo, String arquivo, String ano, Date dataHora, Periodico idPeriodico, Area idArea, Autor idAutor, Categoria idCategoria) {
        this.idPublicacao = idPublicacao;
        this.titulo = titulo;
        this.arquivo = arquivo;
        this.ano = ano;
        this.dataHora = dataHora;
        this.periodico = idPeriodico;
        this.area = idArea;
        this.autor = idAutor;
        this.categoria = idCategoria;  
    }

    public Integer getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(Integer idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Periodico getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPublicacao != null ? idPublicacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Publicacao)) {
            return false;
        }
        Publicacao other = (Publicacao) object;
        if ((this.idPublicacao == null && other.idPublicacao != null) || (this.idPublicacao != null && !this.idPublicacao.equals(other.idPublicacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Publicacao[ " + idPublicacao + " ]";
    }

    
}
