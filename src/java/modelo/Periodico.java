package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "periodico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodico.findAll", query = "SELECT p FROM Periodico p"),
    @NamedQuery(name = "Periodico.findByIdPeriodico", query = "SELECT p FROM Periodico p WHERE p.idPeriodico = :idPeriodico"),
    @NamedQuery(name = "Periodico.findByTitulo", query = "SELECT p FROM Periodico p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Periodico.findByData", query = "SELECT p FROM Periodico p WHERE p.data = :data"),
    @NamedQuery(name = "Periodico.findByDescricao", query = "SELECT p FROM Periodico p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Periodico.findByIssn", query = "SELECT p FROM Periodico p WHERE p.issn = :issn"),
    @NamedQuery(name = "Periodico.findByNacionalidade", query = "SELECT p FROM Periodico p WHERE p.nacionalidade = :nacionalidade"),
    @NamedQuery(name = "Periodico.findByEndFoto", query = "SELECT p FROM Periodico p WHERE p.endFoto = :endFoto")})
public class Periodico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPeriodico")
    private Integer idPeriodico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Size(max = 60)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 10)
    @Column(name = "issn")
    private String issn;
    @Size(max = 60)
    @Column(name = "nacionalidade")
    private String nacionalidade;
    @Size(max = 60)
    @Column(name = "endFoto")
    private String endFoto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodico")
    private List<Publicacao> publicacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodico")
    private List<Qualis> qualisList;

    public Periodico() {
    }

    public Periodico(Integer idPeriodico) {
        this.idPeriodico = idPeriodico;
    }

    public Periodico(Integer idPeriodico, String titulo, Date data) {
        this.idPeriodico = idPeriodico;
        this.titulo = titulo;
        this.data = data;
    }

    public Integer getIdPeriodico() {
        return idPeriodico;
    }

    public void setIdPeriodico(Integer idPeriodico) {
        this.idPeriodico = idPeriodico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getEndFoto() {
        return endFoto;
    }

    public void setEndFoto(String endFoto) {
        this.endFoto = endFoto;
    }

    @XmlTransient
    public List<Publicacao> getPublicacaoList() {
        return publicacaoList;
    }

    public void setPublicacaoList(List<Publicacao> publicacaoList) {
        this.publicacaoList = publicacaoList;
    }
    
    
    @XmlTransient
    public List<Qualis> getQualisList() {
        return qualisList;
    }

    public void setQualisList(List<Qualis> qualisList) {
        this.qualisList = qualisList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodico != null ? idPeriodico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Periodico)) {
            return false;
        }
        Periodico other = (Periodico) object;
        if ((this.idPeriodico == null && other.idPeriodico != null) || (this.idPeriodico != null && !this.idPeriodico.equals(other.idPeriodico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Periodico[ idPeriodico=" + idPeriodico + " ]";
    }
    
}
